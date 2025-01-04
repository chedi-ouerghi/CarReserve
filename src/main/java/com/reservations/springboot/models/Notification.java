package com.reservations.springboot.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Pas de relation directe avec User, utiliser uniquement l'ID

    @Column(name = "reservation_id")
    private Long reservationId; // Relation avec la réservation via son ID

    @Column(name = "message", nullable = false)
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_envoi", nullable = false)
    private Date dateEnvoi;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_expiration", nullable = false)
    private Date dateExpiration;

    @Column(name = "statut", nullable = false)
    private String statut = "EN_ATTENTE"; // Valeur par défaut

    // Constructeurs
    public Notification() {
    }

    public Notification(Long userId, Long reservationId, String message, Date dateEnvoi, Date dateExpiration,
            String statut) {
        this.userId = userId;
        this.reservationId = reservationId;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.dateExpiration = dateExpiration;
        this.statut = statut;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", userId=" + userId +
                ", reservationId=" + reservationId +
                ", message='" + message + '\'' +
                ", dateEnvoi=" + dateEnvoi +
                ", dateExpiration=" + dateExpiration +
                ", statut='" + statut + '\'' +
                '}';
    }
}
