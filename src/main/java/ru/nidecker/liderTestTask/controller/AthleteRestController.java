package ru.nidecker.liderTestTask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nidecker.liderTestTask.entity.Athlete;
import ru.nidecker.liderTestTask.service.AthleteService;

import java.util.List;

@RestController
@RequestMapping("api/athletes/")
@RequiredArgsConstructor
public class AthleteRestController {

    private final AthleteService athleteService;

    @GetMapping
    public List<Athlete> findAllByTeamNameOrPosition(@RequestParam(required = false, name = "team_name") String teamName,
                                                     @RequestParam(required = false, name = "position") String position) {
        return athleteService.findAllByTeamNameOrPosition(teamName, position);
    }
}
