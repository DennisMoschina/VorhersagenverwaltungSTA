package edu.kit.VorhersagenverwaltungSTA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VorhersagenverwaltungStaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VorhersagenverwaltungStaApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(VorhersagenverwaltungStaApplication.class);
	}
}