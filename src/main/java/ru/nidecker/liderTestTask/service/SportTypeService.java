package ru.nidecker.liderTestTask.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nidecker.liderTestTask.entity.SportType;
import ru.nidecker.liderTestTask.repository.SportTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportTypeService {

    private final SportTypeRepository sportTypeRepository;

    public Optional<SportType> findByNameIgnoreCase(String name) {
        return sportTypeRepository.findByNameEqualsIgnoreCase(name);
    }

    public List<SportType> findAll() {
        return sportTypeRepository.findAll();
    }

    @Transactional
    public SportType create(String name) {
        checkIsUnique(name);
        return sportTypeRepository.save(new SportType(name));
    }

    @Transactional
    @Modifying
    public SportType update(long id, String name) {
        SportType sportType = findById(id);
        checkIsUnique(name);
        sportType.setName(name);

        return sportTypeRepository.save(sportType);
    }

    public SportType findById(long id) {
        return sportTypeRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("entity with id " + id + " not found");
        });
    }

    @Transactional
    public void delete(long id) {
        sportTypeRepository.deleteById(id);
    }

    private void checkIsUnique(String name) {
        List<String> names = findAll().stream().map(SportType::getName).toList();
        if (names.contains(name)) {
            throw new IllegalArgumentException("entity with name " + name + " already exists");
        }
    }
}