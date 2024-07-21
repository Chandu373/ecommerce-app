package controller;

import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import record.CustomerRequest;
import repository.CustomerRepository;
import service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CustomerRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.create(request));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<Customer> findById(@PathVariable(value = "customer-id") int customerId) {
       return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }
}
