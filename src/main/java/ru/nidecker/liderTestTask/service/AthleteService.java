package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.dto.AthleteDto;
import ru.nidecker.liderTestTask.entity.Athlete;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.repository.AthleteRepository;

import java.util.List;

import static ru.nidecker.liderTestTask.util.PrepareAthleteAthleteDto.athleteDtoToAthlete;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final SportTypeService sportTypeService;
    private final TeamService teamService;

    public List<Athlete> findAllByTeamName(String teamName) {
        return athleteRepository.findAllByTeamNameEqualsIgnoreCase(teamName);
    }

    public List<Athlete> findAllByPosition(String position) {
        return athleteRepository.findAllByPositionEqualsIgnoreCase(position);
    }

    public List<Athlete> findAllByTeamNameOrPosition(String teamName, String position) {
        if (teamName == null && position == null) {
            return athleteRepository.findAll();
        } else if (teamName != null && position != null) {
            return athleteRepository.findAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase(teamName, position);
        } else if (position == null) {
            return findAllByTeamName(teamName);
        } else {
            return findAllByPosition(position);
        }
    }

    @Transactional
    public Athlete create(AthleteDto dto) {
        SportType sportType = sportTypeService.findByNameIgnoreCase(dto.getSportTypeName()).orElseThrow(() -> {
            throw new EntityNotFoundException("sport '" + dto.getSportTypeName() + "' not found");
        });

        Team team = teamService.findByNameIgnoreCase(dto.getTeamName()).orElseThrow(() -> {
            throw new EntityNotFoundException("team '" + dto.getTeamName() + "' not found");
        });

        if (team.getSportType() != sportType) {
            throw new IllegalArgumentException("team '" + team.getName() + "' does not exist in '" + sportType.getName() + "'");
        }

        return athleteRepository.save(athleteDtoToAthlete(dto, team));
    }
}