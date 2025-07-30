package com.alertcare.server.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoCheckResponseDto {
    private Long id;
    private boolean isCkecked;
}
