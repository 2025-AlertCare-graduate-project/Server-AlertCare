package com.alertcare.server.fcm;

import com.alertcare.server.fcm.service.FcmSendService;
import com.alertcare.server.user.domain.User;
import com.alertcare.server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DailyNotificationScheduler {

    private final UserRepository userRepository;
    private final FcmSendService fcmSendService;

    // 매일 오전 7시
    @Scheduled(cron = "0 0 7 * * *")
    public void sendDailyNotification() {
        List<User> users = userRepository.findAllByFcmTokenIsNotNull();

        for (User user : users) {
            try {
                fcmSendService.sendMessage(
                        user.getFcmToken(),
                        "일일 활동 리포트가 준비되었습니다",
                        user.getCareReceiverName() + "님의 어제 활동을 확인해보세요"
                );
            } catch (Exception e) {
                log.error("스케줄러 에러 - userId: {}, 에러: {}", user.getId(), e.getMessage());
            }
        }
    }
}