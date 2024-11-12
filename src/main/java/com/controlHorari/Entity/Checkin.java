package com.controlHorari.Entity;
import com.controlHorari.Enum.CheckinType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "checkins")
public class Checkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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

    public Checkin() {
        this.timestamp = LocalDateTime.now();
    }
}
