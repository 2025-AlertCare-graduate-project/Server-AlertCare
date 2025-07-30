package com.alertcare.server.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VideoListResponseDto {
    private Long id;
    private LocalDateTime fallDetectTime;
    private boolean isVideoAccessible;
    private boolean isCheckedByUser;
}
