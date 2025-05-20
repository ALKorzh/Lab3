package com.karzhou.fraction.warehouse;

import com.karzhou.fraction.entity.Figure;
import com.karzhou.fraction.observer.Observer;
import com.karzhou.fraction.observer.Observable;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Singleton Warehouse для хранения вычисленных значений фигур.
 * Не потокобезопасный.
 */
public class Warehouse implements Observer {

    private static Warehouse instance;

    private final Map<Integer, FigureMetrics> storage = new ConcurrentHashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    @Override
    public void update(Observable o) {
        if (o instanceof Figure) {
            Figure figure = (Figure) o;
            storage.put(figure.getId(),
                    new FigureMetrics(figure.area(), figure.perimeter(), figure.volume()));
        }
    }

    public FigureMetrics getMetrics(int figureId) {
        return storage.get(figureId);
    }

    public void removeMetrics(int figureId) {
        storage.remove(figureId);
    }

    public static class FigureMetrics {
        private final BigDecimal area;
        private final BigDecimal perimeter;
        private final BigDecimal volume;

        public FigureMetrics(BigDecimal area, BigDecimal perimeter, BigDecimal volume) {
            this.area = area;
            this.perimeter = perimeter;
            this.volume = volume;
        }

        public BigDecimal getArea() {
            return area;
        }

        public BigDecimal getPerimeter() {
            return perimeter;
        }

        public BigDecimal getVolume() {
            return volume;
        }

        @Override
        public String toString() {
            return String.format("Area=%s, Perimeter=%s, Volume=%s",
                    area.toPlainString(), perimeter.toPlainString(), volume.toPlainString());
        }
    }

}

