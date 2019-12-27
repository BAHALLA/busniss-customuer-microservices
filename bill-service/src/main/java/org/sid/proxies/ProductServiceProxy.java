package org.sid.proxies;

import org.sid.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductServiceProxy {
    @GetMapping("/products/{id}?projection=fullProduct")
    Product getProduct(@PathVariable("id") Long id);

    @GetMapping("/products?projection=fullProduct")
    PagedModel<Product> findProducts();
}
