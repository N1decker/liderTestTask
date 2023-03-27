package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.nidecker.liderTestTask.AbstractTest;
import ru.nidecker.liderTestTask.dto.TeamDto;
import ru.nidecker.liderTestTask.dto.TeamDtoWithSportType;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.entity.Team;
import ru.nidecker.liderTestTask.repository.TeamRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TeamServiceTest extends AbstractTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private SportTypeService sportTypeService;

    @InjectMocks
    private TeamService teamService;

    @Test
    void create_shouldThrowException_whenSportTypeNotFound() {
        TeamDtoWithSportType dto = mock(TeamDtoWithSportType.class);
        when(sportTypeService.findByNameIgnoreCase(dto.getSportTypeName())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> teamService.create(dto));
    }

    @Test
    void create_shouldThrowException_whenTeamNameIsExists() {
        TeamDtoWithSportType dto = mock(TeamDtoWithSportType.class);
        when(sportTypeService.findByNameIgnoreCase(dto.getSportTypeName())).thenReturn(Optional.of(mock(SportType.class)));
        when(teamRepository.findByNameEqualsIgnoreCase(dto.getName())).thenReturn(Optional.of(mock(Team.class)));

        assertThrows(EntityExistsException.class, () -> teamService.create(dto));
    }

    @Test
    void create_shouldBeOk() {
        TeamDtoWithSportType dto = mock(TeamDtoWithSportType.class);
        when(sportTypeService.findByNameIgnoreCase(dto.getSportTypeName())).thenReturn(Optional.of(mock(SportType.class)));
        when(teamRepository.findByNameEqualsIgnoreCase(dto.getName())).thenReturn(Optional.empty());

        teamService.create(dto);

        verify(teamRepository, Mockito.times(1)).save(any(Team.class));
    }

    @Test
    void findById_shouldThrowException_whenIdNotFound() {
        long id = 1L;
        when(teamRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> teamService.findById(id));
    }

    @Test
    void update_shouldThrowException_whenIdNotFound() {
        long id = 1L;
        when(teamRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> teamService.update(id, mock(TeamDto.class)));
    }

    @Test
    void update_shouldBeOk() {
        long id = 1L;
        when(teamRepository.findById(id)).thenReturn(Optional.of(mock(Team.class)));

        teamService.update(id, mock(TeamDto.class));

        verify(teamRepository, times(1)).save(any(Team.class));
    }
}