package com.controlHorari.Service;

import com.controlHorari.Dtos.CheckinDto;
import com.controlHorari.Entity.Checkin;
import com.controlHorari.Entity.User;
import com.controlHorari.Enum.CheckinType;
import com.controlHorari.Repository.CheckinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckinService {
    private final CheckinRepository repo;

    @Autowired
    private UserService userService;

    // CONSTRUCTOR =====================================================================================================
    public CheckinService(CheckinRepository checkinRepository) {
        this.repo = checkinRepository;
    }

    // CREATE METHODS ==================================================================================================
    public Checkin createCheckin(CheckinDto checkinDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        String formattedString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Checkin> fitxadesAvui = getFitxadesByData(formattedString);
        CheckinType checkinType = CheckinType.SORTIDA;

        if (fitxadesAvui.isEmpty()){
            checkinType = CheckinType.ENTRADA;
        } else {
            if (fitxadesAvui.get(fitxadesAvui.size() - 1).getCheckinType() == CheckinType.SORTIDA){
                checkinType = CheckinType.ENTRADA;
            }
        }

        Checkin checkin = new Checkin();
        checkin.setUser(user);
        checkin.setLatitud(checkinDto.getLatitud());
        checkin.setLongitud(checkinDto.getLongitud());
        checkin.setDeviceInfo(checkinDto.getDeviceInfo());
        checkin.setCheckinType(checkinType);
        return repo.save(checkin);
    }

    // READ METHODS ====================================================================================================
    public List<Checkin> getFitxadesByData(String dataSeleccionada) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String dataToFilter = dataSeleccionada + "00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dataToFilter, formatter);
        return repo.getCheckinByTimestampAndUserId(dateTime, user.getId());
    }
}