package ru.nidecker.liderTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nidecker.liderTestTask.entity.Team;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByTypeOfSportEqualsIgnoreCase(String typeOfSport);

    List<Team> findAllByDateGreaterThanEqualAndDateLessThanEqual(LocalDate dateFrom, LocalDate dateTo);

    Optional<Team> findByNameEqualsIgnoreCase(String name);
}
