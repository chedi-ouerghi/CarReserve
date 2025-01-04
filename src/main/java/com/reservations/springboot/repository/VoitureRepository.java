package com.reservations.springboot.repository;

import com.reservations.springboot.models.StatutDisponibilite;
import com.reservations.springboot.models.Voiture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    List<Voiture> findByStatutDisponibilite(StatutDisponibilite statutDisponibilite);

}
