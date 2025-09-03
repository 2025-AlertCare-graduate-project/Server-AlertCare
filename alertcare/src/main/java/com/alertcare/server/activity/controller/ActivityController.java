package com.alertcare.server.activity.controller;


import com.alertcare.server.activity.dto.ActivityRequestDto;
import com.alertcare.server.activity.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/hourly")
    public ResponseEntity<Void> saveHourlyActivity(@RequestBody ActivityRequestDto dto) {
        activityService.saveActivity(dto);
        return ResponseEntity.ok().build();
    }
}
