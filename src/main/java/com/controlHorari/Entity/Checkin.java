package com.controlHorari.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkins")
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "checkin_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckinType checkinType;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;

    @Column(name = "device_info", length = 255)
    private String deviceInfo;

    // Constructor para inicializar timestamp automáticamente
    public Checkin() {
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters

    public enum CheckinType {
        ENTRADA, SORTIDA
    }
}
