package com.reservations.springboot.services;

import com.reservations.springboot.models.Categorie;
import com.reservations.springboot.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    // Récupérer toutes les catégories
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    // Récupérer une catégorie par ID
    public Optional<Categorie> getCategorieById(Integer id) {
        return categorieRepository.findById(id);
    }

    // Ajouter une nouvelle catégorie
    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Mettre à jour une catégorie existante
    public Categorie updateCategorie(Integer id, Categorie categorieDetails) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        if (categorie.isPresent()) {
            Categorie updatedCategorie = categorie.get();
            updatedCategorie.setNom(categorieDetails.getNom());
            return categorieRepository.save(updatedCategorie);
        } else {
            return null; // Retourne null si la catégorie n'existe pas
        }
    }

    // Supprimer une catégorie
    public void deleteCategorie(Integer id) {
        categorieRepository.deleteById(id);
    }
}
