package com.FT.app.Repo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

public class Test {

    @Value("${kakao.client.id}")
    private String kakaoClientId;

    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("kakao.client.id: " + kakaoClientId);
        };
    }
}
