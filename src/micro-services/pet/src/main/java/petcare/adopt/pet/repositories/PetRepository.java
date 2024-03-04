package petcare.adopt.pet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petcare.adopt.pet.domain.Pet;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    public List<Pet> findBySituation(String situation);

}
