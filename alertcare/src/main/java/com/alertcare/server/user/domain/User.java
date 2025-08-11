package com.alertcare.server.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "care_giver_name", nullable = false)
    private String careGiverName;

    @Column(name = "care_receiver_name", nullable = false)
    private String careReceiverName;

    @Column(name = "care_receiver_phone_number", nullable = false, unique = true)
    private String careReceiverPhoneNumber;

    @Column(unique = true)
    private String fcmToken;
}
