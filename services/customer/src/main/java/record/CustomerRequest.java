package record;

import domain.Address;


public record CustomerRequest(

        Integer id,
        String name,
        String email,
        String mobile,
        Address address
) {
}
