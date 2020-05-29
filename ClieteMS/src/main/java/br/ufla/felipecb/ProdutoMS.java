package br.ufla.felipecb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableJpaRepositories(basePackages = "br.ufla.felipecb.dao")
@SpringBootApplication
public class ProdutoMS {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoMS.class, args);
	}

}
