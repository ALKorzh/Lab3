package com.karzhou.fraction.observer;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.warehouse.Warehouse;

public class FigureObserver implements Observer {

    @Override
    public void update(Observable o) {
        if (o instanceof Figure) {
            Figure figure = (Figure) o;
            Warehouse.getInstance().updateMetrics(figure);
        }
    }
}
