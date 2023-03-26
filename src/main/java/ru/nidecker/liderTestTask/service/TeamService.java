package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.dto.TeamDto;
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
public class TeamService {

    private final TeamRepository teamRepository;
    private final SportTypeService sportTypeService;

    public Optional<Team> findByNameIgnoreCase(String name) {
        return teamRepository.findByNameEqualsIgnoreCase(name);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public List<Team> findAllByTypeOfSport(String typeOfSport) {
        return teamRepository.findAllBySportTypeNameEqualsIgnoreCase(typeOfSport);
    }

    public List<Team> findAllByDateFromAndDateTo(LocalDate dateFrom, LocalDate dateTo) {
        return teamRepository.findAllByDateGreaterThanEqualAndDateLessThanEqual(dateFrom, dateTo);
    }

    @Transactional
    public Team create(TeamDto dto) {
        SportType sportType = sportTypeService.findByNameIgnoreCase(dto.getSportTypeName()).orElseThrow(() -> {
            throw new EntityNotFoundException("sport '" + dto.getSportTypeName() + "' not found");
        });

        findByNameIgnoreCase(dto.getName()).ifPresent(team -> {
            throw new EntityExistsException("team '" + dto.getName() + "' already exists");
        });

        return teamRepository.save(teamDtoToTeam(dto, sportType));
    }
}