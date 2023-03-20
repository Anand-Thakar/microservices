package com.example.paymentservice.entity;

import com.example.paymentservice.dto.PaymentMode;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;
    @Column
    private Long orderId;
    @Column
    private String paymentStatus;
    @Column
    private PaymentMode paymentMode;
    @Column
    private Long paymentAmount;
    @Column
    private Instant paymentDate;
    @Column
    private String referenceNumber;

}
