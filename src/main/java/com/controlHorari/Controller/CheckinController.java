package com.controlHorari.Controller;

import com.controlHorari.Dtos.CheckinDto;
import com.controlHorari.Entity.Checkin;
import com.controlHorari.Service.CheckinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.controlHorari.Constants.Constants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/checkin")
public class CheckinController {

    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService) {
        this.checkinService = checkinService;
    }

    // CREATE METHODS ==================================================================================================
    @PostMapping("/create")
    public ResponseEntity<Checkin> createCheckin(@RequestBody CheckinDto checkinDto){
        return ResponseEntity.ok(checkinService.createCheckin(checkinDto));
    }

    // READ METHODS ====================================================================================================
    @GetMapping("/get")
    public ResponseEntity<List<Checkin>> getCheckin(@RequestParam String dataSeleccionada){
        List<Checkin> result = checkinService.getFitxadesByData(dataSeleccionada);
        return ResponseEntity.ok(result);
    }
}
