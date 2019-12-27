package org.sid;

import org.sid.dao.BillRepository;
import org.sid.dao.ProductItemRepository;
import org.sid.entities.Bill;
import org.sid.entities.Customer;
import org.sid.entities.ProductItem;
import org.sid.proxies.CustomerServiceProxy;
import org.sid.proxies.ProductServiceProxy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
							CustomerServiceProxy customerServiceProxy, ProductServiceProxy productServiceProxy){
		return args -> {
			Bill bill=new Bill();
			bill.setBillDate(new Date());
			Customer customer= customerServiceProxy.getCustomer(1L);
			bill.setCustomer(customer);
			bill.setCustomerID(customer.getId());
			billRepository.save(bill);
			productServiceProxy.findProducts().getContent().forEach(p ->{
				productItemRepository.save(new ProductItem(null,null, p.getId(),p.getPrice(), p.getQuantity(),bill ));
			});
		};
	}
}
