package ru.nidecker.liderTestTask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Id
//    @SequenceGenerator(name = "athlete_seq", sequenceName = "athlete_id_seq", initialValue = 22, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "athlete_id_seq")
    private Long id;

    @NotBlank
    private String lastName;
    private String patronymic;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Team team;

    private String position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Athlete athlete = (Athlete) o;
        return id != null && Objects.equals(id, athlete.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
