package petcare.adopt.notification.queueHandlers.messageObjects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SendEmail {

    private String title;

    private String email;

    private String body;

    private boolean isHtml;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }
}
