package com.chandu.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue
    private  Integer id;
    private  String name ;
    private  String description;
    @OneToMany(mappedBy = "category" ,cascade = CascadeType.REMOVE )
    private List<Product> productList;
}
