package controller;

import domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import record.CustomerRequest;
import repository.CustomerRepository;
import service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private  static  final Logger log = LoggerFactory.getLogger(CustomerController.class);

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
    public ResponseEntity<Customer> findById(@PathVariable(value = "customer-id") Integer customerId) {
        if(customerId == null){
            log.error("please provide customer ID");
            return  ResponseEntity.badRequest().build();
        }
       return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }


}
