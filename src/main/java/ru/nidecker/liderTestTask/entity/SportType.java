package ru.nidecker.liderTestTask.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class SportType extends BaseEntity {

    public SportType(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SportType sportType = (SportType) o;
        return getId() != null && Objects.equals(getId(), sportType.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
