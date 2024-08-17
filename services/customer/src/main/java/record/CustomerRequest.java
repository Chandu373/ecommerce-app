package record;

import domain.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record CustomerRequest(

        Integer id,
        @NotNull(message = " Customer name is required")
        String name,
        @NotNull(message = " Customer email is required")
        @Email(message = " Customer email is not a valid email address")
        String email,
        @NotNull(message = " Customer mobile is required")
        String mobile,
        Address address
) {
}
