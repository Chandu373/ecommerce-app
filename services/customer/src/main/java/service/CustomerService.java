package service;

import domain.Customer;
import mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import record.CustomerRequest;
import repository.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper mapper;

    public String create(CustomerRequest request) {

        Customer customer = mapper.toCustomer(request);
        // need  to save  the  customer
        return customer.getName();
    }

    public Customer findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
