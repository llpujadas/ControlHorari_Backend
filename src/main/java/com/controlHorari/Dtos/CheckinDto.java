package com.controlHorari.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckinDto {
    private String token;

    private long expiresIn;

    // Getters and setters...
}
