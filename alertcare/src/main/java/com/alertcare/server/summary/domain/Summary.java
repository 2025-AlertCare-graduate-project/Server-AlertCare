package com.alertcare.server.summary.domain;

import com.alertcare.server.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "summary")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private int activeTime;
    private int sittingTime;
    private int lyingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Summary(User user, LocalDate date, int activeTime, int sittingTime, int lyingTime) {
        this.user = user;
        this.date = date;
        this.activeTime = activeTime;
        this.sittingTime = sittingTime;
        this.lyingTime = lyingTime;
    }
}
