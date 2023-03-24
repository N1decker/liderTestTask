package ru.nidecker.liderTestTask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Athlete extends BaseEntity {

    @NotBlank
    private String lastName;
    private String patronymic;

    @ManyToOne
    private Team team;

    private String position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Athlete athlete = (Athlete) o;
        return getId() != null && Objects.equals(getId(), athlete.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
