package com.reservations.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reservations.springboot.models.Marque;
import com.reservations.springboot.services.MarqueService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marques")
public class MarqueController {

    @Autowired
    private MarqueService marqueService;

    // Get all Marques
    // valider
    @GetMapping
    public ResponseEntity<List<Marque>> getAllMarques() {
        List<Marque> marques = marqueService.getAllMarques();
        return ResponseEntity.ok(marques);
    }

    // Get Marque by ID
    // valider
    @GetMapping("/{id}")
    public ResponseEntity<Marque> getMarqueById(@PathVariable Integer id) {
        Optional<Marque> marque = marqueService.getMarqueById(id);
        return marque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Marque
    // valider
    @PostMapping
    public ResponseEntity<Marque> createMarque(@RequestBody Marque marque) {
        Marque savedMarque = marqueService.createMarque(marque);
        return ResponseEntity.ok(savedMarque);
    }

    // Update an existing Marque
    // valider
    @PutMapping("/{id}")
    public ResponseEntity<Marque> updateMarque(@PathVariable Integer id, @RequestBody Marque updatedMarque) {
        Marque marque = marqueService.updateMarque(id, updatedMarque);
        if (marque != null) {
            return ResponseEntity.ok(marque);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a Marque by ID
    // valider
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarque(@PathVariable Integer id) {
        marqueService.deleteMarque(id);
        return ResponseEntity.noContent().build();
    }
}
