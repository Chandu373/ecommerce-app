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
    @NonNull
    private String name;
    @NotNull
    private String email;
    @NonNull
    private String mobile;
    private Address address;
}
