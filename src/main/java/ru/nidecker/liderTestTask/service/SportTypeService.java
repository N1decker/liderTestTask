package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.repository.SportTypeRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportTypeService {

    private final SportTypeRepository sportTypeRepository;

    public SportType findByNameIgnoreCase(String name) {
        return sportTypeRepository.findByNameEqualsIgnoreCase(name).orElseThrow(() -> {
            throw new EntityNotFoundException("sport type '" + name + "' not found");
        });
    }
}
