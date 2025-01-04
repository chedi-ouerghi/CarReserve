package com.reservations.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reservations.springboot.models.Voiture;
import com.reservations.springboot.payload.request.VoitureDTO;
import com.reservations.springboot.payload.request.VoitureRequest;
import com.reservations.springboot.services.VoitureService;

import java.util.List;

@RestController
@RequestMapping("/api/voitures")
public class VoitureController {

    @Autowired
    private VoitureService voitureService;
    // valider

    @GetMapping("/voitures")
    public List<VoitureDTO> getAllVoitures() {
        return voitureService.getAllVoitures();
    }
    // valider

    // Get voiture by ID
    @GetMapping("/{id}")
    public ResponseEntity<Voiture> getVoitureById(@PathVariable Long id) {
        Voiture voiture = voitureService.getVoitureById(id);
        return voiture != null ? ResponseEntity.ok(voiture) : ResponseEntity.notFound().build();
    }
    // valider

    // Get available voitures
    @GetMapping("/disponibles")
    public List<Voiture> getVoitureDisponible() {
        return voitureService.getVoitureDisponible();
    }
    // valider

    // Add a new voiture (POST)
    @PostMapping("/voitures")
    public ResponseEntity<VoitureDTO> createVoiture(@RequestBody VoitureRequest voitureRequest) {
        VoitureDTO newVoiture = voitureService.createVoiture(voitureRequest);
        return ResponseEntity.ok(newVoiture);
    }
    // valider

    // Update a voiture
    @PutMapping("/{id}")
    public ResponseEntity<VoitureDTO> updateVoiture(@PathVariable Long id, @RequestBody VoitureRequest voitureRequest) {
        VoitureDTO updatedVoiture = voitureService.updateVoiture(id, voitureRequest);
        return updatedVoiture != null ? ResponseEntity.ok(updatedVoiture) : ResponseEntity.notFound().build();
    }
    // Delete a voiture by ID
    @DeleteMapping("/voitures/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        voitureService.deleteVoiture(id);
        return ResponseEntity.noContent().build();
    }
}
