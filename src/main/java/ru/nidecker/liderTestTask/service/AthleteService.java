package ru.nidecker.liderTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.entity.Athlete;
import ru.nidecker.liderTestTask.repository.AthleteRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository athleteRepository;

    public List<Athlete> findAllByTeamName(String teamName) {
        return athleteRepository.findAllByTeamNameEqualsIgnoreCase(teamName);
    }

    public List<Athlete> findALlByPosition(String position) {
        return athleteRepository.findAllByPositionEqualsIgnoreCase(position);
    }

    public List<Athlete> findAllByTeamNameOrPosition(String teamName, String position) {
        if (teamName != null && position != null) {
            return athleteRepository.findAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase(teamName, position);
        } else if (position == null) {
            return findAllByTeamName(teamName);
        } else {
            return findALlByPosition(position);
        }
    }
}
