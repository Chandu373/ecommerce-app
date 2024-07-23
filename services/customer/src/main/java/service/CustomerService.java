package service;

import domain.Customer;
import mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import record.CustomerRequest;
import record.CustomerResponse;
import repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper mapper;

    public String create(CustomerRequest request) {

        Customer customer = mapper.toCustomer(request);
        customerRepository.save(customer);
        // need  to save  the  customer
        return customer.getName();
    }

    public Customer findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public void deleteCustomerById(Integer customerId) {
        customerRepository.deleteById(customerId);
    }
}
