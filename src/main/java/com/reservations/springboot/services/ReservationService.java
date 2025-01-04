package com.reservations.springboot.services;

import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservations.springboot.models.Voiture;
import com.reservations.springboot.security.services.UserDetailsImpl;

@Service
public class ReservationService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

  // Méthode pour obtenir l'ID utilisateur à partir du token JWT
private Long getUtilisateurIdFromContext() {
    // Récupérer l'utilisateur authentifié depuis le contexte de sécurité
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (userDetails != null) {
        // Retourner l'ID de l'utilisateur à partir de UserDetailsImpl
        return userDetails.getId();
    }

    // Si l'utilisateur n'est pas authentifié ou s'il y a un problème, vous pouvez
    // lancer une exception ou retourner null
    throw new IllegalStateException("Utilisateur non authentifié.");
}


    @Transactional
    public void reserverVoiture(Long voitureId, String dateDebut, String dateFin) {
        Long utilisateurId = getUtilisateurIdFromContext();
        if (utilisateurId == null) {
            throw new IllegalArgumentException("Utilisateur non trouvé dans le contexte.");
        }

        String sql = "{CALL reserver_voiture(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, utilisateurId, voitureId, dateDebut, dateFin);
    }

    @Transactional
    public void effectuerPaiement(Long reservationId) {
        Long utilisateurId = getUtilisateurIdFromContext();
        if (utilisateurId == null) {
            throw new IllegalArgumentException("Utilisateur non trouvé dans le contexte.");
        }

        String sql = "{CALL effectuer_paiement(?, ?)}";
        jdbcTemplate.update(sql, reservationId, utilisateurId);
    }

    public List<Voiture> getVoituresDisponibles(String dateDebut, String dateFin) {
        String sql = "SELECT * FROM voitures v " +
                "WHERE v.statut_disponibilite = 'DISPONIBLE' " +
                "AND NOT EXISTS ( " +
                "    SELECT 1 FROM reservations r " +
                "    WHERE r.voiture_id = v.id " +
                "    AND (r.date_debut < ? AND r.date_fin > ?) " +
                ")";

        RowMapper<Voiture> rowMapper = new BeanPropertyRowMapper<>(Voiture.class);
        return jdbcTemplate.query(sql, rowMapper, dateFin, dateDebut);
    }

    // Vérifier les notifications à envoyer
    public void verifierNotifications() {
        String sql = "SELECT user_id, message FROM notifications " +
                "WHERE statut = 'EN_ATTENTE' AND date_envoi <= NOW()";

        List<Map<String, Object>> notifications = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> notification : notifications) {
            Long userId = (Long) notification.get("user_id");
            String message = (String) notification.get("message");
            envoyerNotification(userId, message);
        }
    }

    // Simuler l'envoi de notification
    private void envoyerNotification(Long userId, String message) {
        System.out.println("Notification envoyée à l'utilisateur " + userId + ": " + message);
    }
}
