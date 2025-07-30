package com.alertcare.server.video.domain;


import com.alertcare.server.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fallDetectVideoUrl;

    private LocalDateTime fallDetectTime;

    private boolean isVideoAccessible;

    private boolean isCheckedByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void checkByUser() {
        this.isCheckedByUser = true;
    }

    @Builder
    public Video(String fallDetectVideoUrl, LocalDateTime fallDetectTime, boolean isVideoAccessible, boolean isCheckedByUser, User user) {
        this.fallDetectVideoUrl = fallDetectVideoUrl;
        this.fallDetectTime = fallDetectTime;
        this.isVideoAccessible = true;
        this.isCheckedByUser = isCheckedByUser;
        this.user = user;
    }
}
