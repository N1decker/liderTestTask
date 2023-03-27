package ru.nidecker.liderTestTask.util;


import ru.nidecker.liderTestTask.dto.AthleteDto;
import ru.nidecker.liderTestTask.entity.Athlete;
import ru.nidecker.liderTestTask.entity.Team;

import java.time.LocalDate;

public class PrepareAthleteAthleteDto {

    public static Athlete athleteDtoToAthlete(AthleteDto dto) {
        Athlete athlete = new Athlete();
        if (dto.getDate().isBefore(LocalDate.now())) {
            athlete.setDate(dto.getDate());
        } else {
            throw new IllegalArgumentException("date must be at least until today");
        }
        athlete.setName(dto.getName());
        athlete.setLastName(dto.getLastName());
        athlete.setPatronymic(dto.getPatronymic());
        athlete.setPosition(dto.getPosition());

        return athlete;
    }

    public static Athlete athleteDtoToAthlete(AthleteDto dto, Team team) {
        Athlete athlete = athleteDtoToAthlete(dto);
        athlete.setTeam(team);

        return athlete;
    }
}