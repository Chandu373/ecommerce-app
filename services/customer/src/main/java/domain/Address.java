package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
}
