package ru.nidecker.liderTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findAllByTypeOfSport(String typeOfSport) {
        return teamRepository.findAllByTypeOfSportEqualsIgnoreCase(typeOfSport);
    }
}
