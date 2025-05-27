package com.karzhou.fraction.entity;

import com.karzhou.fraction.observer.Observable;
import com.karzhou.fraction.util.IdGenerator;
import java.math.BigDecimal;

public abstract class Figure extends Observable {
    private final int id;

    public Figure() {
        this.id = IdGenerator.nextId();
    }

    public int getId() {
        return id;
    }

    public abstract BigDecimal area();

    public abstract BigDecimal perimeter();

    public abstract BigDecimal volume();

    public void notifyChange() {
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Figure{id=" + id + "}";
    }
}

