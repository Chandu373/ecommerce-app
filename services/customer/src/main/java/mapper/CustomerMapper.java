package mapper;

import domain.Customer;
import org.springframework.stereotype.Service;
import record.CustomerRequest;
import record.CustomerResponse;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(request == null){
            return   null;
        }
       return Customer.builder()
               .id(request.id())
               .name(request.name())
               .email(request.email())
               .mobile(request.mobile())
               .address(request.address())
               .build();
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
