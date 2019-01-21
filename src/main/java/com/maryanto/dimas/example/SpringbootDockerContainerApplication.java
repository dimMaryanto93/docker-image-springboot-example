package com.maryanto.dimas.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class SpringbootDockerContainerApplication {

    @GetMapping("/halo")
    public Map<String, Object> haloWorld() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Hello world!");
        map.put("time", Timestamp.valueOf(LocalDateTime.now()));
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDockerContainerApplication.class, args);
    }

}

