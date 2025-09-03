package com.alertcare.server.activity.domain;

import com.alertcare.server.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private int activeTime;
    private int sittingTime;
    private int lyingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Activity(User user, LocalDateTime timestamp, int activeTime, int sittingTime, int lyingTime) {
        this.user = user;
        this.timestamp = timestamp;
        this.activeTime = activeTime;
        this.sittingTime = sittingTime;
        this.lyingTime = lyingTime;
    }
}
