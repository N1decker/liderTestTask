package ru.nidecker.liderTestTask.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    @NotNull
    private String name;

    @NotNull
    private String typeOfSport;

    @Past
    @NotNull
    private LocalDate date;
}
