package com.controlHorari.Dtos;

import com.controlHorari.Enum.CheckinType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CheckinDto {
    private Long userId;
    private CheckinType checkinType;
    private String latitud;
    private String longitud;
    private String deviceInfo;
}
