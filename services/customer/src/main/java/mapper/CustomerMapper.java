package mapper;

import domain.Customer;
import org.springframework.stereotype.Component;
import record.CustomerRequest;
import record.CustomerResponse;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        return new Customer(
                request.id(),
                request.name(),
                request.email(),
                request.mobile(),
                request.address()
        );
    }


    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getMobile(),
                customer.getAddress()
        );
    }
}
