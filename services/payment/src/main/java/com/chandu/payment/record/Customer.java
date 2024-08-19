package com.chandu.payment.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        String id,
        @NotNull(message = "name is required")
        String name,
        @NotNull(message = "email is required")
        @Email(message = "email should be valid")
        String email
) {
}
