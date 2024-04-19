package com.intervals.entities.IntervalRelease;

import com.intervals.entities.Interval;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая интервалы цифр.
 */
@Entity
@Table(name = "digits")
@NoArgsConstructor
public class DigitInterval extends Interval<Long> {

    /**
     * Создает числовой интервал.
     *
     * @param st начальное значение интервала
     * @param en конечное значение интервала
     * @throws IllegalArgumentException если тип не поддерживается
     */
    public DigitInterval(Object st, Object en) throws IllegalArgumentException {
        super(convertToLong(st), convertToLong(en));
    }

    /**
     * Преобразует объект в тип Long.
     *
     * @param obj объект для преобразования
     * @return значение типа Long
     * @throws IllegalArgumentException если тип не поддерживается
     */
    private static Long convertToLong(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        } else if (obj instanceof Long) {
            return (Long) obj;
        } else {
            throw new IllegalArgumentException("This type is not supported");
        }
    }

    @Override
    public int compareTo(Interval<Long> object) {
        if (this.getStart() > object.getStart()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean mergeWith(Interval<Long> value) {
        if (getStart() <= value.getStart()) {
            if (getEnded() >= value.getStart() && getEnded() <= value.getEnded()) {
                setEnded(value.getEnded());
                return true;
            }
        }
        return false;
    }
    /**
     * Устанавливает конечное значение интервала.
     *
     * @param value значение для установки
     * @throws IllegalArgumentException если конечное значение меньше начального
     */
    @Override
    public void setEnded(Long value) {
        if (getStart() > value) throw new IllegalArgumentException();
        else {
            super.setEnded(value);
        }
    }

    @Override
    public void setStart(Long value) {
        super.setStart(value);
    }
}
