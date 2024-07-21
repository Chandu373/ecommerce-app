package record;

import domain.Address;

import java.util.List;


public record CustomerRequest(

        int id,
        String name,
        String email,
        String mobile,
        List<Address> address
) {
}
