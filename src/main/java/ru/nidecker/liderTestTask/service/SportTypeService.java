package ru.nidecker.liderTestTask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.repository.SportTypeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportTypeService {

    private final SportTypeRepository sportTypeRepository;

    public Optional<SportType> findByNameIgnoreCase(String name) {
        return sportTypeRepository.findByNameEqualsIgnoreCase(name);
    }

//    TODO: should add sport type creation and deletion
}
