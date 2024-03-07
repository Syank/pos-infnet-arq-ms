package petcare.adopt.adoption.requesters;

import ch.qos.logback.core.rolling.helper.PeriodicityType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import petcare.adopt.adoption.configurations.WebClientConfigurations;
import petcare.adopt.adoption.requesters.responseBodies.User;

import java.util.Optional;

@Service
public class UserRequester {

    private WebClient userWebClient;

    public UserRequester(@Qualifier(WebClientConfigurations.USER_WEB_CLIENT) WebClient userWebClient) {
        this.userWebClient = userWebClient;

    }

    public Optional<User> findById(Long userId) {
        User user = userWebClient.post()
                .uri("/user/" + userId)
                .exchangeToMono(response -> {
                    if (response.statusCode().value() == HttpStatus.NOT_FOUND.value()) {
                        return response.bodyToMono(User.class).thenReturn(null);
                    } else {
                        return response.bodyToMono(User.class);
                    }
                }).block();

        if (user == null) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

}
