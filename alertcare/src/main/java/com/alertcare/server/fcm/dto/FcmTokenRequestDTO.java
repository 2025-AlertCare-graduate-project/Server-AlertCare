package com.alertcare.server.fcm.dto;


public record FcmTokenRequestDTO(
        Long userId,
        String token
) {}