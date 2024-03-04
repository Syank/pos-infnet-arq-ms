package petcare.adopt.pet.queueHandlers.messageObjects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangePetSituation {

    private Long petId;

    private String situation;

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

}
