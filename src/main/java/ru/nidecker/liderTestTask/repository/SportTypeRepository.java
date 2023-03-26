package ru.nidecker.liderTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nidecker.liderTestTask.entity.SportType;

import java.util.Optional;

public interface SportTypeRepository extends JpaRepository<SportType, Long> {

    Optional<SportType> findByNameEqualsIgnoreCase(String name);
}
