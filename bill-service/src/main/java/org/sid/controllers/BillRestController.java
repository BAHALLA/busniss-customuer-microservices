package org.sid.controllers;

import org.sid.dao.BillRepository;
import org.sid.dao.ProductItemRepository;
import org.sid.entities.Bill;
import org.sid.proxies.CustomerServiceProxy;
import org.sid.proxies.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {
    @Autowired private BillRepository billRepository;
    @Autowired private ProductItemRepository productItemRepository;
    @Autowired private CustomerServiceProxy customerServiceProxy;
    @Autowired private ProductServiceProxy productServiceProxy;

    @GetMapping("/bills/full/{id}")
    Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill=billRepository.findById(id).get();
        bill.setCustomer(customerServiceProxy.getCustomer(bill.getCustomerID()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(pi->{
            pi.setProduct(productServiceProxy.getProduct(pi.getProductID()));
        });
        return bill;
    }
}
