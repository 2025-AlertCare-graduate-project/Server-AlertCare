package com.alertcare.server.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String careGiverName;

    @Column(nullable = false)
    private String careReceiverName;

    @Column(nullable = false, unique = true)
    private String careReceiverPhoneNumber;

    @Column(nullable = false)
    private int careReceiverAge;
}
