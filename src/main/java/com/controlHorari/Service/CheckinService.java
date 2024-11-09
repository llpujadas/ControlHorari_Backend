package com.controlHorari.Service;

import com.controlHorari.Dtos.CheckinDto;
import com.controlHorari.Entity.Checkin;
import com.controlHorari.Repository.CheckinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckinService {
    private final CheckinRepository checkinRepository;

    // CONSTRUCTOR =====================================================================================================
    public CheckinService(CheckinRepository checkinRepository) {
        this.checkinRepository = checkinRepository;
    }
}