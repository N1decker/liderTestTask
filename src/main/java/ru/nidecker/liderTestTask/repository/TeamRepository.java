package ru.nidecker.liderTestTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nidecker.liderTestTask.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
