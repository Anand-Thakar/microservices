package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;
    @Column
    private String productName;
    @Column
    private Integer quantityInStock;
    @Column
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getProductID() != null && Objects.equals(getProductID(), product.getProductID());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
