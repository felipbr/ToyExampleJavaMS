package br.ufla.felipecb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@EnableJpaRepositories(basePackages = "br.ufla.felipecb.dao")
@SpringBootApplication
public class FuncionarioMS {

	public static void main(String[] args) {
		SpringApplication.run(FuncionarioMS.class, args);
	}
	 
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
