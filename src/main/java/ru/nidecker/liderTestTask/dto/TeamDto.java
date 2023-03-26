package ru.nidecker.liderTestTask.dto;

import jakarta.validation.constraints.NotNull;
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
    private String sportTypeName;

    @NotNull
    private LocalDate date;
}
