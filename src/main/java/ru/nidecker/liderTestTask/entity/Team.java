package ru.nidecker.liderTestTask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Team extends BaseEntity {

    @NotBlank
    private String typeOfSport;

    @OneToMany(orphanRemoval = true, mappedBy = "team")
    @ToString.Exclude
    private List<Athlete> athletes;

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
