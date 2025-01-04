package com.reservations.springboot.services;

import com.reservations.springboot.models.Categorie;
import com.reservations.springboot.models.Modele;
import com.reservations.springboot.models.StatutDisponibilite;
import com.reservations.springboot.models.Voiture;
import com.reservations.springboot.payload.request.VoitureDTO;
import com.reservations.springboot.payload.request.VoitureRequest;
import com.reservations.springboot.repository.CategorieRepository;
import com.reservations.springboot.repository.ModeleRepository;
import com.reservations.springboot.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    public List<VoitureDTO> getAllVoitures() {
        List<Voiture> voitures = voitureRepository.findAll();
        return voitures.stream()
                .map(voiture -> new VoitureDTO(
                        voiture.getModele().getNom(),
                        voiture.getModele().getMarque().getNom(),
                        voiture.getAnnee(),
                        voiture.getPrixParJour(),
                        voiture.getCategorie().getNom(),
                        voiture.getStatutDisponibilite().name()))
                .collect(Collectors.toList());
    }

    // Get voiture by ID
    public Voiture getVoitureById(Long id) {
        return voitureRepository.findById(id)
                .orElse(null); // Retourne null si la voiture n'est pas trouvée
    }

    // Get available voitures
    public List<Voiture> getVoitureDisponible() {
        return voitureRepository.findByStatutDisponibilite(StatutDisponibilite.disponible);
    }

    // Add a new voiture
    // Add a new voiture (POST)
    // Add a new voiture (POST)
    public VoitureDTO createVoiture(VoitureRequest voitureRequest) {
        Voiture voiture = new Voiture();

        // Récupérer le modèle et la catégorie depuis la base de données
        Modele modele = modeleRepository.findByNom(voitureRequest.getModele());
        Categorie categorie = categorieRepository.findByNom(voitureRequest.getCategorie());

        if (modele != null && categorie != null) {
            // Set values from voitureRequest to voiture
            voiture.setModele(modele);
            voiture.setCategorie(categorie);
            voiture.setAnnee(voitureRequest.getAnnee());
            voiture.setPrixParJour(voitureRequest.getPrixParJour());
            voiture.setStatutDisponibilite(StatutDisponibilite.valueOf(voitureRequest.getStatutDisponibilite()));

            voiture = voitureRepository.save(voiture);

            return new VoitureDTO(
                    voiture.getModele().getNom(),
                    voiture.getModele().getMarque().getNom(),
                    voiture.getAnnee(),
                    voiture.getPrixParJour(),
                    voiture.getCategorie().getNom(),
                    voiture.getStatutDisponibilite().name());
        }

        throw new EntityNotFoundException("Modèle ou catégorie non trouvé");
    }

    // Update voiture (PUT)
    public VoitureDTO updateVoiture(Long id, VoitureRequest voitureRequest) {
        Optional<Voiture> voitureOptional = voitureRepository.findById(id);

        if (voitureOptional.isPresent()) {
            Voiture voiture = voitureOptional.get();

            // Récupérer le modèle et la catégorie depuis la base de données
            Modele modele = modeleRepository.findByNom(voitureRequest.getModele());
            Categorie categorie = categorieRepository.findByNom(voitureRequest.getCategorie());

            if (modele != null && categorie != null) {
                // Update voiture attributes
                voiture.setModele(modele);
                voiture.setCategorie(categorie);
                voiture.setAnnee(voitureRequest.getAnnee());
                voiture.setPrixParJour(voitureRequest.getPrixParJour());
                voiture.setStatutDisponibilite(StatutDisponibilite.valueOf(voitureRequest.getStatutDisponibilite()));

                voiture = voitureRepository.save(voiture);

                return new VoitureDTO(
                        voiture.getModele().getNom(),
                        voiture.getModele().getMarque().getNom(),
                        voiture.getAnnee(),
                        voiture.getPrixParJour(),
                        voiture.getCategorie().getNom(),
                        voiture.getStatutDisponibilite().name());
            }

            throw new EntityNotFoundException("Modèle ou catégorie non trouvé");
        }

        return null; // Ou éventuellement lancer une exception
    }

    // Delete a voiture by ID
    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }
}
