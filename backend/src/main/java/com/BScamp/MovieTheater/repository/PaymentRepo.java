package com.BScamp.MovieTheater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BScamp.MovieTheater.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
	
}
