package ru.nidecker.liderTestTask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDto {

    private String name;

    @Past
    @NotNull
    private LocalDate date;

    @NotBlank
    private String lastName;

    private String patronymic;

    private String position;
}
