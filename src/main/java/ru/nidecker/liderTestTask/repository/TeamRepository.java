package ru.nidecker.liderTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nidecker.liderTestTask.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findAllByTypeOfSportEqualsIgnoreCase(String typeOfSport);
}
