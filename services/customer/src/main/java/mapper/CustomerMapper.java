package mapper;

import domain.Customer;
import org.springframework.stereotype.Component;
import record.CustomerRequest;

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
}
