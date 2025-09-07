package com.alertcare.server.activity.service;

import com.alertcare.server.activity.domain.Activity;
import com.alertcare.server.activity.dto.ActivityRequestDto;
import com.alertcare.server.activity.repository.ActivityRepository;
import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;  // 로깅
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    public void saveActivity(ActivityRequestDto dto) {

        User user = userRepository.findByCareReceiverPhoneNumber(dto.getPhoneNumber())
                .orElseThrow(() -> {
                    return new RuntimeException("User not found: " + dto.getPhoneNumber());
                });

        LocalDateTime timestamp;
        try {
            timestamp = LocalDateTime.parse(dto.getTimestamp(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            timestamp = LocalDateTime.now();
        }

        Activity activity = Activity.builder()
                .user(user)
                .timestamp(timestamp)
                .activeTime(dto.getActiveTime())
                .sittingTime(dto.getSittingTime())
                .lyingTime(dto.getLyingTime())
                .build();

        activityRepository.save(activity);
    }
}
