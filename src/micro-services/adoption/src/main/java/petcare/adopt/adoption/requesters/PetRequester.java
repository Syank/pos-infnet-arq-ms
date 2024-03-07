package petcare.adopt.adoption.requesters;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import petcare.adopt.adoption.configurations.WebClientConfigurations;
import petcare.adopt.adoption.requesters.responseBodies.Pet;

import java.util.Optional;

@Service
public class PetRequester {

    private WebClient petWebClient;

    public PetRequester(@Qualifier(WebClientConfigurations.PET_WEB_CLIENT) WebClient petWebClient) {
        this.petWebClient = petWebClient;

    }

    public Optional<Pet> findPetById(Long petId) {
        Pet pet = petWebClient.get()
                .uri("/pet/" + petId)
                .exchangeToMono(response -> {
                    if (response.statusCode().value() == HttpStatus.NOT_FOUND.value()) {
                        return response.bodyToMono(Pet.class).thenReturn(null);
                    } else {
                        return response.bodyToMono(Pet.class);
                    }
                }).block();

        if (pet == null) {
            return Optional.empty();
        }

        return Optional.of(pet);
    }

}
