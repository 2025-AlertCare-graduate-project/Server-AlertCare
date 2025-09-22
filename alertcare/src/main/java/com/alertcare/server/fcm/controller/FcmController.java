package com.alertcare.server.fcm.controller;

import com.alertcare.server.common.response.BasicResponse;
import com.alertcare.server.fcm.service.FcmSendService;
import com.alertcare.server.fcm.service.FcmTokenService;
import com.alertcare.server.fcm.controller.enums.FcmMessage;
import com.alertcare.server.fcm.controller.enums.ResponseMessage;
import com.alertcare.server.fcm.dto.FcmSendRequestDTO;
import com.alertcare.server.fcm.dto.FcmTokenRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/fcm")
@Slf4j
public class FcmController {

    private final FcmTokenService fcmTokenService;
    private final FcmSendService fcmSendService;

    @PostMapping("/register")
    public BasicResponse<String> registerToken(@RequestBody FcmTokenRequestDTO request) {
        log.info("UserId: {}", request.userId());
        log.info("Token: {}", request.token());


        fcmTokenService.saveToken(request.userId(), request.token());
        return BasicResponse.success(ResponseMessage.FCM_TOKEN_SAVE_SUCCESS, null);
    }

    @PostMapping("/send")
    public BasicResponse<String> sendPush(@RequestBody FcmSendRequestDTO request) throws IOException {
        String token = fcmTokenService.getToken(request.userId());

        String title = FcmMessage.FCM_TITLE.getMessage();
        String body = FcmMessage.FCM_BODY.getMessage();

        fcmSendService.sendMessage(token, title, body);
        return BasicResponse.success(ResponseMessage.FCM_PUSH_SUCCESS, null);
    }
}