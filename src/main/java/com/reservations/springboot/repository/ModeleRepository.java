package com.reservations.springboot.repository;

import com.reservations.springboot.models.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeleRepository extends JpaRepository<Modele, Integer> {
    // Méthode pour trouver un modèle par son nom
    Modele findByNom(String nom);
}