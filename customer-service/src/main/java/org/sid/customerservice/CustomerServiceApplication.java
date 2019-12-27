package org.sid.customerservice;

import org.sid.customerservice.dao.CustomerRepository;
import org.sid.customerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(new Customer(null, "BAHALLA", "bahalla@gmail.com"));
			customerRepository.save(new Customer(null, "ABOURI", "abouri@gmail.com"));
			customerRepository.save(new Customer(null, "BARCHANE", "barchane@gmail.com"));

			for (Customer e : customerRepository.findAll()) {
				System.out.println(e);
			}
		};
	}
}
