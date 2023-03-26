package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.dto.TeamDto;
import ru.nidecker.liderTestTask.dto.TeamDtoWithSportType;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.repository.TeamRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static ru.nidecker.liderTestTask.util.PrepareTeamTeamDto.teamDtoToTeam;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class TeamService {

    private final TeamRepository teamRepository;
    private final SportTypeService sportTypeService;

    public Optional<Team> findByNameIgnoreCase(String name) {
        log.info("find by name {} ignore case", name);
        return teamRepository.findByNameEqualsIgnoreCase(name);
    }

    public List<Team> findAll() {
        log.info("find all team");
        return teamRepository.findAll();
    }

    public List<Team> findAllBySportName(String sportName) {
        log.info("find all by sport {}", sportName);
        return teamRepository.findAllBySportTypeNameEqualsIgnoreCase(sportName);
    }

    public List<Team> findAllByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo) {
        log.info("find all by period from {} to {}", dateFrom, dateTo);
        return teamRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(dateFrom, dateTo);
    }

    @Transactional
    public Team create(TeamDtoWithSportType dto) {
        SportType sportType = sportTypeService.findByNameIgnoreCase(dto.getSportTypeName()).orElseThrow(() -> {
            throw new EntityNotFoundException("sport '" + dto.getSportTypeName() + "' not found");
        });

        findByNameIgnoreCase(dto.getName()).ifPresent(team -> {
            throw new EntityExistsException("team '" + dto.getName() + "' already exists");
        });
        Team team = teamDtoToTeam(dto, sportType);
        log.info("entity {} created", team);

        return teamRepository.save(team);
    }

    public Team findById(long id) {
        log.info("find by id {}", id);
        return teamRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("team by id " + id + " not found");
        });
    }

    @Transactional
    @Modifying
    public Team update(long id, TeamDto dto) {
        Team team = findById(id);
        team.setName(dto.getName());
        team.setDate(dto.getDate());
        log.info("entity {} updated", team);

        return teamRepository.save(team);
    }
}