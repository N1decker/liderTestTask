package ru.nidecker.liderTestTask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nidecker.liderTestTask.dto.TeamDto;
import ru.nidecker.liderTestTask.dto.TeamDtoWithSportType;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.service.TeamService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Slf4j
@RestController
@RequestMapping(value = "api/teams", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping
    public List<Team> findAll(@RequestParam(required = false, name = "type_of_sport") String typeOfSport) {
        if (typeOfSport == null) {
            log.info("find all teams request");
            return teamService.findAll();
        } else {
            log.info("find teams by type of sport = {}", typeOfSport);
            return teamService.findAllBySportName(typeOfSport);
        }
    }

    @GetMapping("/by_period")
    public List<Team> findAllByPeriod(
            @RequestParam(name = "dateFrom") @DateTimeFormat(iso = DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo") @DateTimeFormat(iso = DATE) LocalDate dateTo) {
        return teamService.findAllByDateFromAndDateTo(dateFrom, dateTo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team create(@RequestBody TeamDtoWithSportType dto) {
        return teamService.create(dto);
    }

    @GetMapping("/{id}")
    public Team getById(@PathVariable long id) {
        return teamService.findById(id);
    }

    @PutMapping("/{id}")
    public Team update(@PathVariable long id,
                       @RequestBody TeamDto dto) {
        return teamService.update(id, dto);
    }
}