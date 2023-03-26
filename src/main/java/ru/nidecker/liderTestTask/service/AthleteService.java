package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
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
@Slf4j
public class AthleteService {

    private final AthleteRepository athleteRepository;
    private final SportTypeService sportTypeService;
    private final TeamService teamService;

    public List<Athlete> findAllByTeamName(String teamName) {
        log.info("find all by team {}", teamName);
        return athleteRepository.findAllByTeamNameEqualsIgnoreCase(teamName);
    }

    public List<Athlete> findAllByPosition(String position) {
        log.info("find all by position {}", position);
        return athleteRepository.findAllByPositionEqualsIgnoreCase(position);
    }

    public List<Athlete> findAllByTeamNameOrPosition(String teamName, String position) {
        if (teamName == null && position == null) {
            return athleteRepository.findAll();
        } else if (teamName != null && position != null) {
            log.info("find all by team {} and position {}", teamName, position);
            return athleteRepository.findAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase(teamName, position);
        } else if (position == null) {
            log.info("find all by team {}", teamName);
            return findAllByTeamName(teamName);
        } else {
            log.info("find all by position {}", position);
            return findAllByPosition(position);
        }
    }

    @Transactional
    public Athlete create(AthleteDto dto) {
        Athlete athlete = returnAthleteIfSportTypeAndTeamExists(dto);
        log.info("entity {} created", athlete);

        return athleteRepository.save(athlete);
    }

    @Transactional
    @Modifying
    public Athlete update(long id, AthleteDto dto) {
        Athlete athlete = returnAthleteIfSportTypeAndTeamExists(dto);
        athlete.setId(getById(id).getId());
        log.info("entity {} updated", athlete);

        return athleteRepository.save(athlete);
    }

    public Athlete getById(long id) {
        log.info("find by id {}", id);
        return athleteRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("athlete by id " + id + " not found");
        });
    }

    private Athlete returnAthleteIfSportTypeAndTeamExists(AthleteDto dto) {
        SportType sportType = sportTypeService.findByNameIgnoreCase(dto.getSportTypeName()).orElseThrow(() -> {
            throw new EntityNotFoundException("sport '" + dto.getSportTypeName() + "' not found");
        });

        Team team = teamService.findByNameIgnoreCase(dto.getTeamName()).orElseThrow(() -> {
            throw new EntityNotFoundException("team '" + dto.getTeamName() + "' not found");
        });

        if (team.getSportType() != sportType) {
            throw new IllegalArgumentException("team '" + team.getName() + "' does not exist in '" + sportType.getName() + "'");
        }

        return athleteDtoToAthlete(dto, team);
    }

    @Transactional
    public void deleteById(long id) {
        athleteRepository.deleteById(id);
    }
}
