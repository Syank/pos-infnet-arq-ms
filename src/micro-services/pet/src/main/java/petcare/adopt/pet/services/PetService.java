package petcare.adopt.pet.services;

import org.springframework.stereotype.Service;
import petcare.adopt.pet.domain.Pet;
import petcare.adopt.pet.repositories.PetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;

    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }

    public void updatePet(Pet pet) {
        petRepository.save(pet);
    }

    public Optional<Pet> getPet(Long id) {
        return petRepository.findById(id);
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsForAdoption() {
        return petRepository.findBySituation("FOR_ADOPTION");
    }

    public void updatePetSituation(Long petId, String situation) throws Exception {
        Optional<Pet> optionalPet = petRepository.findById(petId);

        if (optionalPet.isEmpty()) {
            throw new Exception("Pet not found");
        }

        Pet pet = optionalPet.get();

        pet.setSituation(situation);

        petRepository.save(pet);

    }

    public boolean canBeAdopted(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);

        if (optionalPet.isEmpty()) {
            return false;
        }

        Pet pet = optionalPet.get();

        String situation = pet.getSituation();

        if (situation.equals("FOR_ADOPTION")) {
            return true;
        }

        return false;
    }
}
