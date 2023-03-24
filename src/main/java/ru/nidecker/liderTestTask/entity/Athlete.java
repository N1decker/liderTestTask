package ru.nidecker.liderTestTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Athlete extends BaseEntity {

    @NotBlank
    private String lastName;
    private String patronymic;

    @NonNull
    @ManyToOne
    @JsonIgnore
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
