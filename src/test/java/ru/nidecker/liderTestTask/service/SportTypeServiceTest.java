package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.nidecker.liderTestTask.AbstractTest;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.repository.SportTypeRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SportTypeServiceTest extends AbstractTest {

    @Mock
    private SportTypeRepository sportTypeRepository;

    @InjectMocks
    private SportTypeService sportTypeService;

    @Test
    void create_shouldThrowException_whenNameIsExists() {
        String name = "name";
        SportType sportType = new SportType(name);
        when(sportTypeRepository.findAll()).thenReturn(List.of(sportType));
        when(sportTypeRepository.save(mock(SportType.class, name))).thenReturn(sportType);

        assertThrows(IllegalArgumentException.class, () -> sportTypeService.create(name));
    }

    @Test
    void findById_shouldThrowException_whenIdNotFound() {
        long id = 1L;
        when(sportTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> sportTypeService.findById(id));
    }
}