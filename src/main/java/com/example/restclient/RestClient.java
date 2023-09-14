package com.example.restclient;

import com.example.restclient.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class RestClient {

    private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

    public static void main(String[] args) {
        SpringApplication.run(RestClient.class, args);

        String baseUrl = "http://94.198.50.185:7081/api/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
        String sessionId = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);//session id
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.COOKIE, sessionId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        User James = new User(3L, "James", "Brown", 30);
        HttpEntity<User> request = new HttpEntity<>(James, headers);

        ResponseEntity<String> subsequentResponse = restTemplate.exchange(baseUrl, HttpMethod.POST, request, String.class);
        System.out.println(subsequentResponse.getBody());
        logger.info(subsequentResponse.getBody());
        //первая часть

        User Tomas = new User(3L, "Tomas", "Shelby", 30);
        HttpEntity<User> request2 = new HttpEntity<>(Tomas, headers);
        ResponseEntity<String> subsequentResponse2 = restTemplate.exchange(baseUrl,HttpMethod.PUT, request2, String.class);
        System.out.println(subsequentResponse2.getBody());
        logger.info(subsequentResponse2.getBody());
        //Вторая часть

        HttpEntity<String> request3 = new HttpEntity<>(null, headers);
        ResponseEntity<String> subsequentResponse3 = restTemplate.exchange(baseUrl+"/3",HttpMethod.DELETE, request3, String.class);
        System.out.println(subsequentResponse3.getBody());
        logger.info(subsequentResponse3.getBody());
        //Третья часть
    }
}
