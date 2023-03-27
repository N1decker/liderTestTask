package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.nidecker.liderTestTask.AbstractTest;
import ru.nidecker.liderTestTask.dto.AthleteDto;
import ru.nidecker.liderTestTask.entity.Athlete;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.repository.AthleteRepository;

import java.time.LocalDate;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AthleteServiceTest extends AbstractTest {

    @Mock
    private AthleteRepository athleteRepository;

    @Mock
    private SportTypeService sportTypeService;

    @Mock
    private TeamService teamService;

    @InjectMocks
    private AthleteService athleteService;

    @Test
    void findAllByTeamNameOrPosition_shouldCallFindAll_whenTeamNameAndPositionAreNull() {
        when(athleteRepository.findAll()).thenReturn(emptyList());

        athleteService.findAllByTeamNameOrPosition(null, null);

        verify(athleteRepository, times(1)).findAll();
        verify(athleteRepository, times(0)).findAllByPositionEqualsIgnoreCase(anyString());
    }

    @Test
    void findAllByTeamNameOrPosition_shouldCallFindAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase_whenArgumentsNotNull() {
        when(athleteRepository.findAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase(anyString(), anyString()))
                .thenReturn(emptyList());

        athleteService.findAllByTeamNameOrPosition(anyString(), anyString());

        verify(athleteRepository, times(1))
                .findAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase(anyString(), anyString());
    }

    @Test
    void create_shouldThrowException_whenSportTypeNotFound() {
        AthleteDto dto = mock(AthleteDto.class);
        when(sportTypeService.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> athleteService.create(dto));
    }

    @Test
    void create_shouldThrowException_whenTeamDoesNotExistsInSportType() {
        AthleteDto dto = mock(AthleteDto.class);
        when(sportTypeService.findByNameIgnoreCase(dto.getSportTypeName())).thenReturn(Optional.of(mock(SportType.class)));
        when(teamService.findByNameIgnoreCase(dto.getTeamName())).thenReturn(Optional.of(mock(Team.class)));

        assertThrows(IllegalArgumentException.class, () -> athleteService.create(dto));
    }

    @Test
    void create_shouldBeOk() {
        AthleteDto dto = new AthleteDto();
        dto.setDate(LocalDate.now().minusDays(1));
        SportType sportType = mock(SportType.class);
        Team team = new Team();
        team.setSportType(sportType);

        when(sportTypeService.findByNameIgnoreCase(dto.getSportTypeName())).thenReturn(Optional.of(sportType));
        when(teamService.findByNameIgnoreCase(dto.getTeamName())).thenReturn(Optional.of(team));

        athleteService.create(dto);

        verify(athleteRepository, times(1)).save(any(Athlete.class));
    }

    @Test
    void update_shouldBeOk() {
        long id = 1L;
        AthleteDto dto = new AthleteDto();
        dto.setDate(LocalDate.now().minusDays(1));
        SportType sportType = mock(SportType.class);
        Team team = new Team();
        team.setSportType(sportType);

        when(sportTypeService.findByNameIgnoreCase(dto.getSportTypeName())).thenReturn(Optional.of(sportType));
        when(teamService.findByNameIgnoreCase(dto.getTeamName())).thenReturn(Optional.of(team));
        when(athleteRepository.findById(id)).thenReturn(Optional.of(mock(Athlete.class)));

        athleteService.update(id, dto);

        verify(athleteRepository, times(1)).save(any(Athlete.class));
    }

    @Test
    void findById_shouldBeOk() {
        long id = 1L;
        when(athleteRepository.findById(id)).thenReturn(Optional.of(mock(Athlete.class)));

        athleteService.findById(id);
    }

    @Test
    void findById_shouldThrowException_whenIdNotFound() {
        long id = 1L;
        when(athleteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> athleteService.findById(id));
    }
}