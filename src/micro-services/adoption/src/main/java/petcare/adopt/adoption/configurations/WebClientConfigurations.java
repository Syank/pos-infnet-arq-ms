package petcare.adopt.adoption.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class WebClientConfigurations {

    public static final String USER_WEB_CLIENT = "userWebClient";
    public static final String PET_WEB_CLIENT = "petWebClient";


    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${pet.service.url}")
    private String petServiceUrl;



    @Bean(USER_WEB_CLIENT)
    public WebClient userWebClient(Builder builder) {
        WebClient userWebClient = builder.baseUrl(userServiceUrl).build();

        return userWebClient;
    }

    @Bean(PET_WEB_CLIENT)
    public WebClient petWebClient(Builder builder) {
        WebClient petWebClient = builder.baseUrl(petServiceUrl).build();

        return petWebClient;
    }


}
