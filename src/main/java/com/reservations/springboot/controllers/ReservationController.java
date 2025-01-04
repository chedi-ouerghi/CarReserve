package com.reservations.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.reservations.springboot.models.Voiture;
import com.reservations.springboot.services.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/testReservation")
    @PreAuthorize("hasRole('USER')")
    public String testReservationAccess() {
        return "Reservation Access.";
    }

    // valider
    @PostMapping("/reserver")
    public ResponseEntity<String> reserverVoiture(
            @RequestParam Long voitureId,
            @RequestParam String dateDebut,
            @RequestParam String dateFin) {

        try {
            reservationService.reserverVoiture(voitureId, dateDebut, dateFin);
            return ResponseEntity.ok("Réservation réussie !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la réservation : " + e.getMessage());
        }
    }

    // valider
    @PostMapping("/effectuer")
    public ResponseEntity<String> effectuerPaiement(@RequestParam Long reservationId) {
        try {
            reservationService.effectuerPaiement(reservationId);
            return ResponseEntity.ok("Paiement effectué avec succès !");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors du paiement : " + e.getMessage());
        }
    }

    // valider
    @GetMapping("/voituresDisponibles")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Voiture>> getVoituresDisponibles(
            @RequestParam String dateDebut,
            @RequestParam String dateFin) {
        try {
            List<Voiture> voituresDisponibles = reservationService.getVoituresDisponibles(dateDebut, dateFin);
            if (voituresDisponibles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(voituresDisponibles);
            }
            return ResponseEntity.ok(voituresDisponibles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // @GetMapping("/verifierNotifications")
    // @PreAuthorize("hasRole('ADMIN')")
    // public ResponseEntity<String> verifierNotifications() {
    // try {
    // reservationService.verifierNotifications();
    // return ResponseEntity.ok("Notifications vérifiées.");
    // } catch (Exception e) {
    // return ResponseEntity.badRequest()
    // .body("Erreur lors de la vérification des notifications : " +
    // e.getMessage());
    // }
    // }

}
