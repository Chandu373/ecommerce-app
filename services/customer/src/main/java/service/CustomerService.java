package service;

import domain.Customer;
import exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import mapper.CustomerMapper;
import org.apache.commons.lang.StringUtils;
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
    private  CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper mapper;

    public String create(CustomerRequest request) {

        Customer customer = mapper.toCustomer(request);
        customerRepository.save(customer);
        // need  to save  the  customer
        return  null;
    }

    public CustomerResponse findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(String.format("Customer Not Found ID :: %s",customerId)));
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

    public void updateCustomer(@Valid CustomerRequest request) {
        if(request != null){
            Customer customer = customerRepository.findById(request.id())
                    .orElseThrow(()-> new CustomerNotFoundException(
                            String.format("Customer not found  for this ID :: %s",request.id())
                    ));
            mergerCustomer(customer,request);
            customerRepository.save(customer);
        }
    }

    private void mergerCustomer(Customer customer, @Valid CustomerRequest request) {
        if(StringUtils.isNotBlank(request.name()))
        {
            customer.setName(request.name());
        }
        if(StringUtils.isNotBlank(request.email()))
        {
            customer.setEmail(request.email());
        }
        if(StringUtils.isNotBlank(request.mobile()))
        {
            customer.setMobile(request.mobile());
        }
        if(request.address() != null)
        {
            customer.setAddress(request.address());
        }
    }
}
