// Service for Marque
package com.reservations.springboot.services;

import com.reservations.springboot.models.Marque;
import com.reservations.springboot.repository.MarqueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {

    @Autowired
    private MarqueRepository marqueRepository;

    // Get all Marques
    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    // Get Marque by ID
    public Optional<Marque> getMarqueById(Integer id) {
        return marqueRepository.findById(id);
    }

    // Create a new Marque
    public Marque createMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    // Update an existing Marque
    public Marque updateMarque(Integer id, Marque updatedMarque) {
        Optional<Marque> existingMarque = marqueRepository.findById(id);
        if (existingMarque.isPresent()) {
            Marque marque = existingMarque.get();
            marque.setNom(updatedMarque.getNom());
            return marqueRepository.save(marque);
        }
        return null;
    }

    // Delete a Marque by ID
    public void deleteMarque(Integer id) {
        marqueRepository.deleteById(id);
    }
}