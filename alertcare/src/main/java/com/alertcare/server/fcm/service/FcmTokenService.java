package com.alertcare.server.fcm.service;

import com.alertcare.server.fcm.exception.FcmErrorCode;
import com.alertcare.server.fcm.exception.FcmException;
import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmTokenService {

    private final UserRepository userRepository;

    public void saveToken(Long userId, String token) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FcmException(FcmErrorCode.MEMBER_NOT_FOUND));

        user.setFcmToken(token);
        userRepository.save(user);
    }

    public String getToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FcmException(FcmErrorCode.MEMBER_NOT_FOUND));

        if (user.getFcmToken() == null) {
            throw new FcmException(FcmErrorCode.MEMBER_FCM_NOT_FOUND);
        }

        return user.getFcmToken();
    }
}
