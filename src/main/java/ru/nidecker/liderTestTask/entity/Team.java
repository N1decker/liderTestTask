package ru.nidecker.liderTestTask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Team extends BaseEntityWithDate {

    @NotNull
    @ManyToOne
    private SportType sportType;

    public Team(String name, LocalDate date, SportType sportType) {
        super(name, date);
        this.sportType = sportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Team team = (Team) o;
        return getId() != null && Objects.equals(getId(), team.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}