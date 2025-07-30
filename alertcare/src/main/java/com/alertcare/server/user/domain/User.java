package com.alertcare.server.user.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "care_receiver_age", nullable = false)
    private int careReceiverAge;
}
