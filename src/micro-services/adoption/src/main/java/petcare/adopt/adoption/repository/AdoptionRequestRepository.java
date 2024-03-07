package petcare.adopt.adoption.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petcare.adopt.adoption.domain.AdoptionRequest;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {

}
