package com.reservations.springboot.repository;

import com.reservations.springboot.models.Marque;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Integer> {

    // Optional<Marque> findByNom(String nom);
}