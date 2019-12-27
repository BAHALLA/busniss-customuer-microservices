package org.sid;

import org.sid.dao.ProductRepository;
import org.sid.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(ProductRepository productRepository) {
		return args -> {
			productRepository.save(new Product(null, "XPS 2017", 15000.25, 10));
			productRepository.save(new Product(null, "HP 2018", 12000.25, 18));
			productRepository.save(new Product(null, "MacBook Air", 14000.29, 12));

			productRepository.findAll().forEach(System.out::println);
		};
	}
}
