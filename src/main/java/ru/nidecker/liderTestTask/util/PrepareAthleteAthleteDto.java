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
            throw new IllegalArgumentException("date should be before today");
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

    public static AthleteDto athleteToAthleteDto(Athlete athlete) {
        AthleteDto dto = new AthleteDto();
        dto.setDate(athlete.getDate());
        dto.setName(athlete.getName());
        dto.setLastName(athlete.getLastName());
        dto.setPatronymic(athlete.getPatronymic());
        dto.setPosition(athlete.getPosition());

        return dto;
    }
}