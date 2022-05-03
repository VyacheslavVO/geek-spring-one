package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.dto.PaginatedResponse;
import ru.geekbrains.dto.UserDto;


@SpringBootApplication
public class Less10RestClientApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(Less10RestClientApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder(Less10RestClientApplication.class)
                .web(WebApplicationType.NONE)
                .build();

        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("We are running some code!!!");
        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8091/mvc-app/rest/v1/user/all";
        ResponseEntity<PaginatedResponse<UserDto>> response =
                template.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {});
        logger.info("Response: {}", response.getBody().getContent());
    }
}
