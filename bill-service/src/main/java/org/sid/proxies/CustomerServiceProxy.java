package org.sid.proxies;

import org.sid.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerServiceProxy {
    @GetMapping("/customers/{id}?projection=fullCustomer")
    Customer getCustomer(@PathVariable("id") Long id);
}
