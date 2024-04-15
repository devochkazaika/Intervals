package com.intervals.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/*
Абстрактный класс для интервалов
В классах насследниках определен тип объектов для сравнения(либо Integer либо String)
Также в классах насследниках настроен компоратор для более удобной сортировки
 */
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
//конструктор без аргументов для Jpa
@NoArgsConstructor
public abstract class Interval<T> implements Comparable<Interval<T>>, Merge<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start")
    T start;
    @Column(name = "ended")
    T ended;

    public Interval(T start, T end){
        setStart(start);
        setEnded(end);
    }
    @Override
    public abstract int compareTo(Interval<T> object);

    @Override
    public abstract boolean mergeWith(Interval<T> value);
    //    public abstract int canMerge(Interval<T> object);

}
