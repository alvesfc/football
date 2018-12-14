package br.com.football.transfers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.WebClient.create;

@SpringBootApplication
@EnableWebFlux
public class FootballTransfersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballTransfersApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return create();
    }
}
