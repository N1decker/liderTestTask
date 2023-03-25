package ru.nidecker.liderTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nidecker.liderTestTask.entity.Athlete;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    List<Athlete> findAllByPositionEqualsIgnoreCase(String position);

    List<Athlete> findAllByTeamNameEqualsIgnoreCase(String name);

    List<Athlete> findAllByTeamNameEqualsIgnoreCaseAndPositionEqualsIgnoreCase(String name, String position);
}
