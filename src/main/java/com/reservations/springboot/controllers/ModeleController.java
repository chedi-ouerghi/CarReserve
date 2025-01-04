package com.reservations.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reservations.springboot.models.Marque;
import com.reservations.springboot.models.Modele;
import com.reservations.springboot.repository.MarqueRepository;
import com.reservations.springboot.services.ModeleService;

@RestController
@RequestMapping("/api/modeles")
public class ModeleController {

    @Autowired
    private ModeleService modeleService;

    @Autowired
    private MarqueRepository marqueRepository;

    // Récupérer tous les modèles
    // valider
    @GetMapping
    public List<Modele> getAllModeles() {
        return modeleService.getAllModeles();
    }

    // Récupérer un modèle par ID
    // valider
    @GetMapping("/{id}")
    public Optional<Modele> getModeleById(@PathVariable Integer id) {
        return modeleService.getModeleById(id);
    }

    // Créer un modèle
    // valider
    @PostMapping
    public ResponseEntity<Modele> createModele(@RequestBody ModeleDto modeleDto) {
        try {
            if (modeleDto.getMarqueId() == null) {
                return ResponseEntity.badRequest().body(null); 
            }

            Marque marque = marqueRepository.findById(modeleDto.getMarqueId())
                    .orElseThrow(() -> new IllegalArgumentException("La marque n'existe pas"));

            Modele modele = new Modele();
            modele.setNom(modeleDto.getNom());
            modele.setMarque(marque);

            Modele createdModele = modeleService.createModele(modele);
            return ResponseEntity.status(201).body(createdModele); 
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); 
        }
    }

    // valider
    @PutMapping("/{id}")
    public ResponseEntity<Modele> updateModele(@PathVariable Integer id, @RequestBody ModeleDto modeleDto) {
        try {
            
            if (modeleDto.getMarqueId() == null) {
                return ResponseEntity.badRequest().body(null); 
            }

            
            Modele modele = modeleService.getModeleById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Le modèle avec l'ID " + id + " n'existe pas"));

            
            Marque marque = marqueRepository.findById(modeleDto.getMarqueId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "La marque avec l'ID " + modeleDto.getMarqueId() + " n'existe pas"));

            
            modele.setNom(modeleDto.getNom());
            modele.setMarque(marque);

            
            Modele updatedModele = modeleService.updateModele(modele);

            
            return ResponseEntity.ok(updatedModele);

        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Supprimer un modèle
    @DeleteMapping("/{id}")
    public void deleteModele(@PathVariable Integer id) {
        modeleService.deleteModele(id);
    }

    // Classe interne ModeleDto
    public static class ModeleDto {
        private String nom;
        private Integer marqueId;

        // Getters et Setters
        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public Integer getMarqueId() {
            return marqueId;
        }

        public void setMarqueId(Integer marqueId) {
            this.marqueId = marqueId;
        }
    }
}
