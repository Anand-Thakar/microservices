package com.example.paymentservice.repo;

import com.example.paymentservice.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

    Optional<PaymentDetails> findByOrderId(Long id);

}