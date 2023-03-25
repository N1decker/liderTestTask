package ru.nidecker.liderTestTask.entity;

import jakarta.persistence.*;
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

    @Id
//    @SequenceGenerator(name = "team_seq", sequenceName = "team_id_seq", initialValue = 13, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "team_id_seq")
    private Long id;

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
        return id != null && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
