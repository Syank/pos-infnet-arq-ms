package petcare.adopt.pet.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcare.adopt.pet.domain.Pet;
import petcare.adopt.pet.services.PetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;

    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAll() {
        List<Pet> items = petService.getAllPets();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> findById(@PathVariable Long id) {
        Optional<Pet> pet = petService.getPet(id);

        if (pet.isPresent()) {
            return new ResponseEntity<>(pet.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        Pet savedPet = petService.savePet(pet);

        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pet> update(@RequestBody Pet pet) {
        petService.updatePet(pet);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.deletePet(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/petsForAdoption")
    public ResponseEntity<List<Pet>> getPetsForAdoption() {
        List<Pet> items = petService.getPetsForAdoption();

        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/canBeAdopted")
    public ResponseEntity<Boolean> getPetsCanBeAdopted(@RequestParam Long petId) {
        boolean canBeAdopted = petService.canBeAdopted(petId);

        return new ResponseEntity<>(canBeAdopted, HttpStatus.OK);
    }

}
