package com.reservations.springboot.services;

import com.reservations.springboot.models.Modele;
import com.reservations.springboot.repository.MarqueRepository;
import com.reservations.springboot.repository.ModeleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeleService {

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    // Récupérer tous les modèles
    public List<Modele> getAllModeles() {
        return modeleRepository.findAll();
    }

    // Récupérer un modèle par ID
    public Optional<Modele> getModeleById(Integer id) {
        return modeleRepository.findById(id);
    }

    public Modele createModele(Modele modele) {
        // Vérifiez si la marque existe
        if (modele.getMarque() == null || !marqueRepository.existsById(modele.getMarque().getId())) {
            throw new IllegalArgumentException("La marque associée n'existe pas.");
        }
        return modeleRepository.save(modele);
    }

    public Modele updateModele(Modele modele) {
        if (modele == null || modele.getId() == null) {
            throw new IllegalArgumentException("Le modèle à mettre à jour doit avoir un ID valide");
        }
        return modeleRepository.save(modele);
    }

    // Supprimer un modèle
    public void deleteModele(Integer id) {
        modeleRepository.deleteById(id);
    }
}
