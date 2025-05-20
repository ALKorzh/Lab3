package com.karzhou.fraction.entity;

import com.karzhou.fraction.observer.Observable;
import java.math.BigDecimal;

public abstract class Figure extends Observable {
    private final int id; // уникальный идентификатор фигуры
    private static int count = 0;

    public Figure() {
        this.id = ++count;
    }

    public int getId() {
        return id;
    }

    // Теперь возвращаем BigDecimal, чтобы было удобно работать с дробями и иррациональными числами
    public abstract BigDecimal area();

    public abstract BigDecimal perimeter();

    public abstract BigDecimal volume();

    // Метод для уведомления об изменениях
    public void notifyChange() {
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Figure{id=" + id + "}";
    }
}
