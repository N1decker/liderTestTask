package ru.nidecker.liderTestTask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AthleteDto {

    private String name;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String lastName;

    private String patronymic;

    @NotBlank
    private String position;

    @NotBlank
    private String teamName;

    @NotBlank
    private String sportTypeName;
}