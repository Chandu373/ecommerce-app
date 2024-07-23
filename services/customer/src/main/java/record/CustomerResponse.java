package record;

import domain.Address;

public record CustomerResponse(
        Integer id,
        String name,
        String email,
        String mobile,
        Address address
) {
}
