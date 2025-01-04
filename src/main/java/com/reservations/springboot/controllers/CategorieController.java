package com.reservations.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.reservations.springboot.models.Categorie;
import com.reservations.springboot.services.CategorieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    // Récupérer toutes les catégories
    // valider
    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    // Récupérer une catégorie par ID
    // valider
    @GetMapping("/{id}")
    public Optional<Categorie> getCategorieById(@PathVariable Integer id) {
        return categorieService.getCategorieById(id);
    }

    // Créer une nouvelle catégorie
    // valider
    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return categorieService.createCategorie(categorie);
    }

    // Mettre à jour une catégorie existante
    // valider
    @PutMapping("/{id}")
    public Categorie updateCategorie(@PathVariable Integer id, @RequestBody Categorie categorieDetails) {
        return categorieService.updateCategorie(id, categorieDetails);
    }

    // Supprimer une catégorie
    // valider
    @DeleteMapping("/{id}")
    public void deleteCategorie(@PathVariable Integer id) {
        categorieService.deleteCategorie(id);
    }
}
