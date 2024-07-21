package service;

import domain.Customer;
import mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import record.CustomerRequest;
import repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper mapper;

    public String create(CustomerRequest request) {

        Customer customer = mapper.toCustomer(request);
        return customer.getName();
    }
}
