package com.intervals.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Bean;

/*
Абстрактный класс для интервалов
В классах насследниках определен тип объектов для сравнения(либо Integer либо String)
Также в классах насследниках настроен компоратор для более удобной сортировки
 */
@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class Interval<T> implements Comparable<Interval<T>> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start")
    T start;
    @Column(name = "ended")
    T ended;
    @Override
    public abstract int compareTo(Interval<T> object);
    public abstract int canMerge(Interval<T> object);
}
