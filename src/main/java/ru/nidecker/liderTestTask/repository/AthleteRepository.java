package ru.nidecker.liderTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nidecker.liderTestTask.entity.Athlete;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
}
