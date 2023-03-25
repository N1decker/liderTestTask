package ru.nidecker.liderTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findAllByTypeOfSport(String typeOfSport) {
        return teamRepository.findAllByTypeOfSportEqualsIgnoreCase(typeOfSport);
    }

    public List<Team> findAllByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo) {
        return teamRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(dateFrom, dateTo);
    }
}
