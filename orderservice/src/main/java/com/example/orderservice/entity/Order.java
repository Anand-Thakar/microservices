package com.example.orderservice.entity;
import com.example.orderservice.model.PaymentMode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_db")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Column
    private Long productId;
    @Column
    private String quantity;
    @Column
    private Double amount;
    @Column
    private Instant orderDate;
    @Column
    private String orderStatus;
    @Column
    private PaymentMode paymentMode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getOrderId() != null && Objects.equals(getOrderId(), order.getOrderId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

