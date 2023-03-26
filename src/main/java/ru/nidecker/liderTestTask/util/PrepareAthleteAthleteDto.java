package ru.nidecker.liderTestTask.util;


import ru.nidecker.liderTestTask.dto.AthleteDto;
import ru.nidecker.liderTestTask.entity.Athlete;

public class PrepareAthleteAthleteDto {

    public static Athlete athleteDtoToAthlete(AthleteDto dto) {
        Athlete athlete = new Athlete();
        athlete.setDate(dto.getDate());
        athlete.setName(dto.getName());
        athlete.setLastName(dto.getLastName());
        athlete.setPatronymic(dto.getPatronymic());
        athlete.setPosition(dto.getPosition());

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