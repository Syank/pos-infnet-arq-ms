package petcare.adopt.adoption.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import petcare.adopt.adoption.domain.AdoptionRequest;
import petcare.adopt.adoption.repository.AdoptionRequestRepository;
import petcare.adopt.adoption.requesters.PetRequester;
import petcare.adopt.adoption.requesters.UserRequester;
import petcare.adopt.adoption.requesters.responseBodies.Pet;
import petcare.adopt.adoption.requesters.responseBodies.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AdoptionService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private PetRequester petRequester;
    private UserRequester userRequester;
    private AmqpTemplate amqpTemplate;
    private ObjectMapper objectMapper;
    private AdoptionRequestRepository adoptionRequestRepository;

    public AdoptionService(PetRequester petRequester, UserRequester userRequester,
                           AmqpTemplate amqpTemplate, ObjectMapper objectMapper, AdoptionRequestRepository adoptionRequestRepository) {
        this.petRequester = petRequester;
        this.userRequester = userRequester;
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
        this.adoptionRequestRepository = adoptionRequestRepository;

    }

    public void cancelAdoption(Long adoptionId) {
        adoptionRequestRepository.deleteById(adoptionId);

    }

    public AdoptionRequest requestAdoption(Long petId, Long userId) throws JsonProcessingException {
        Optional<Pet> optionalPet = petRequester.findPetById(petId);

        if (!optionalPet.isPresent()) {
            return null;
        }

        Pet pet = optionalPet.get();

        Optional<User> optionalUser = userRequester.findById(userId);

        if (!optionalUser.isPresent()) {
            return null;
        }

        User user = optionalUser.get();

        if (!pet.getSituation().equals("FOR_ADOPTION")) {
            return null;
        }

        Map<String, Object> changePetSituation = new HashMap<>();

        changePetSituation.put("petId", pet.getId());
        changePetSituation.put("situation", "ADOPTED");

        String message = objectMapper.writeValueAsString(changePetSituation);

        AdoptionRequest adoptionRequest = new AdoptionRequest();
        adoptionRequest.setPetId(pet.getId());
        adoptionRequest.setUserId(user.getId());

        AdoptionRequest createdRequest = adoptionRequestRepository.save(adoptionRequest);

        amqpTemplate.convertAndSend(exchange, routingKey, message);

        return createdRequest;
    }

}
