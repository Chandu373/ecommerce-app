package domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address {

    @GeneratedValue
    @Id
    private Integer id;
    private String streetNumber;
    private String zipCode;
    private String state;
    private String country;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    Customer customers;

}
