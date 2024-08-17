package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {

    @GeneratedValue
    @Id
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String mobile;

    private Address address;
}
