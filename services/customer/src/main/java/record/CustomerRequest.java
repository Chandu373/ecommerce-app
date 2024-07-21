package record;

import domain.Address;

import java.util.List;


public record CustomerRequest(

        Integer id,
        String name,
        String email,
        String mobile,
        List<Address> address
) {
}
