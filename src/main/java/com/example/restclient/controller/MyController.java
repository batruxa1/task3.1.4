package com.example.restclient.controller;

import com.example.restclient.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @PostMapping("/processJson")
    public String processJson(@RequestBody String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonString, User.class);
            String name = user.getName();
            int age = user.getAge();
            return "JSON успешно обработан!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при обработке JSON: " + e.getMessage();
        }
    }
}
