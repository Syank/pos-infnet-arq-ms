package petcare.adopt.pet.queueHandlers.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import petcare.adopt.pet.queueHandlers.messageObjects.ChangePetSituation;
import petcare.adopt.pet.services.PetService;

@Component
public class PetAdoptionQueue {

    private ObjectMapper objectMapper;
    private PetService petService;

    public PetAdoptionQueue(ObjectMapper objectMapper, PetService petService) {
        this.objectMapper = objectMapper;
        this.petService = petService;

    }

    @RabbitListener(queues = "pet_situation")
    public void updatePetSituation(String message) throws Exception {
        ChangePetSituation changePetSituation = objectMapper.readValue(message, ChangePetSituation.class);

        petService.updatePetSituation(changePetSituation.getPetId(), changePetSituation.getSituation());

    }

}
