package com.reservations.springboot.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reservations.springboot.models.ERole;
import com.reservations.springboot.models.Role;
import com.reservations.springboot.models.User;
import com.reservations.springboot.payload.request.LoginRequest;
import com.reservations.springboot.payload.request.SignupRequest;
import com.reservations.springboot.payload.response.JwtResponse;
import com.reservations.springboot.payload.response.MessageResponse;
import com.reservations.springboot.repository.RoleRepository;
import com.reservations.springboot.repository.UserRepository;
import com.reservations.springboot.security.jwt.JwtUtils;
import com.reservations.springboot.security.services.UserDetailsImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	// Méthode de connexion (signin)
	// valider
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							loginRequest.getUsername(),
							loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream()
					.map(item -> item.getAuthority())
					.collect(Collectors.toList());

			logger.info("Connexion réussie pour l'utilisateur : {}", userDetails.getUsername());

			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
					userDetails.getUsername(), userDetails.getEmail(), roles));

		} catch (Exception e) {
			logger.error("Erreur de connexion : {}", e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse("Identifiants incorrects."));
		}
	}

	// Méthode d'inscription (signup)
	// valider
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		// Vérifications de l'existence
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Erreur : Le nom d'utilisateur est déjà pris !"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur : L'email est déjà utilisé !"));
		}

		// Création du nouvel utilisateur
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		// Gestion des rôles
		Set<Role> roles = assignRoles(signUpRequest.getRole());

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succès !"));
	}

	// Méthode privée pour assigner les rôles
	// valider
	private Set<Role> assignRoles(Set<String> strRoles) {
		Set<Role> roles = new HashSet<>();

		if (strRoles == null || strRoles.isEmpty()) {
			roles.add(getRole(ERole.ROLE_USER));
		} else {
			strRoles.forEach(role -> {
				switch (role.toLowerCase()) {
					case "admin":
						roles.add(getRole(ERole.ROLE_ADMIN));
						break;
					case "mod":
						roles.add(getRole(ERole.ROLE_MODERATOR));
						break;
					default:
						roles.add(getRole(ERole.ROLE_USER));
				}
			});
		}
		return roles;
	}

	// Méthode privée pour récupérer un rôle
	// valider
	private Role getRole(ERole role) {
		return roleRepository.findByName(role).orElseThrow(() -> new RuntimeException("Erreur : Rôle introuvable."));
	}

	  // Méthode pour récupérer les détails de l'utilisateur par le token
    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Vérifier si le token est présent dans le header
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String jwt = authorizationHeader.substring(7);
                
                // Vérifier et valider le token
                if (jwtUtils.validateJwtToken(jwt)) {
                    String username = jwtUtils.getUserNameFromJwtToken(jwt);
                    User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec le nom : " + username));
                    
                    // Retourner les détails de l'utilisateur
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalide.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token manquant.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des informations : " + e.getMessage());
        }
    }
}
