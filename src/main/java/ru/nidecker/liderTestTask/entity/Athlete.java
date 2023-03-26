package ru.nidecker.liderTestTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class Athlete extends BaseEntityWithDate {

    @NotBlank
    private String lastName;
    private String patronymic;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Team team;

    private String position;

    public Athlete(@NotBlank String name, @Past @NotNull LocalDate date, String lastName, String patronymic, @NonNull Team team, String position) {
        super(name, date);
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.team = team;
        this.position = position;
    }

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
