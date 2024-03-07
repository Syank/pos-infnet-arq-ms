package petcare.adopt.adoption.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petcare.adopt.adoption.domain.AdoptionRequest;
import petcare.adopt.adoption.services.AdoptionService;

@RestController
@RequestMapping("/petcare/adoption")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @DeleteMapping("/cancelAdoption")
    public ResponseEntity<Void> cancelAdoption(@RequestParam Long adoptionId) {
        adoptionService.cancelAdoption(adoptionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/requestAdoption")
    public ResponseEntity<AdoptionRequest> requestAdoption(@RequestParam Long petId, @RequestParam Long userId) throws JsonProcessingException {
        AdoptionRequest adoptionRequested = adoptionService.requestAdoption(petId, userId);

        if (adoptionRequested == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(adoptionRequested, HttpStatus.OK);
    }

}
