package ru.nidecker.liderTestTask.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.service.TeamService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
@RequestMapping("api/teams")
@RequiredArgsConstructor
@Slf4j
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping
    public List<Team> findAll(@RequestParam(required = false, name = "type_of_sport") String typeOfSport) {
        if (typeOfSport == null) {
            log.info("find all teams request");
            return teamService.findAll();
        } else {
            log.info("find teams by type of sport = {}", typeOfSport);
            return teamService.findAllByTypeOfSport(typeOfSport);
        }
    }

    @GetMapping("/by_period")
    public List<Team> findAllByPeriod(
            @RequestParam(name = "dateFrom") @DateTimeFormat(iso = DATE) LocalDate dateFrom,
            @RequestParam(name = "dateTo") @DateTimeFormat(iso = DATE) LocalDate dateTo) {
        return teamService.findAllByDateFromAndDateTo(dateFrom, dateTo);
    }



}
