package controller;

import domain.Customer;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import record.CustomerRequest;
import record.CustomerResponse;
import service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody @Valid CustomerRequest request) {
        if (request == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.create(request));
    }

    public ResponseEntity<Void>  update(@RequestBody @Valid CustomerRequest request){
        customerService.updateCustomer(request);
        return  ResponseEntity.accepted().build();
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable(value = "customer-id") Integer customerId) {
        if (customerId == null) {
            log.error("please provide customer ID");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.findCustomerById(customerId));
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @DeleteMapping("/delete/{customer-id}")
    public ResponseEntity<Void> delete(@PathVariable("customer-id") Integer customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();

    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("success");
    }


}
