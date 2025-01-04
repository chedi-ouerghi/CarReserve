package com.reservations.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.springboot.services.ReservationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

	    @Autowired
		private ReservationService reservationService;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "user content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	   @GetMapping("/verifierNotifications")
		@PreAuthorize("hasRole('ADMIN')")
	    public ResponseEntity<String> verifierNotifications() {
        try {
            reservationService.verifierNotifications();
            return ResponseEntity.ok("Notifications vérifiées.");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Erreur lors de la vérification des notifications : " + e.getMessage());
        }
    }
}
