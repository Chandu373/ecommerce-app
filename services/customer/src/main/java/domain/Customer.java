package domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "address")
    private List<Address> address;
}
