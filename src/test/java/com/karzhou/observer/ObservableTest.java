package com.karzhou.observer;

import com.karzhou.fraction.observer.Observable;
import com.karzhou.fraction.observer.Observer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ObservableTest {

    static class TestObservable extends Observable {
    }

    static class TestObserver implements Observer {
        private boolean updated = false;

        @Override
        public void update(Observable observable) {
            updated = true;
        }

        public boolean isUpdated() {
            return updated;
        }
    }

    @Test
    void testAddObserverAndNotify() {
        TestObservable observable = new TestObservable();
        TestObserver observer = new TestObserver();

        observable.addObserver(observer);
        observable.notifyObservers();

        assertAll(
                () -> assertTrue(observer.isUpdated(), "Observer should have been notified")
        );
    }

    @Test
    void testAddNullObserverDoesNothing() {
        TestObservable observable = new TestObservable();

        assertAll(
                () -> assertDoesNotThrow(() -> observable.addObserver(null))
        );
    }

    @Test
    void testRemoveObserverStopsNotification() {
        TestObservable observable = new TestObservable();
        TestObserver observer = new TestObserver();

        observable.addObserver(observer);
        observable.removeObserver(observer);
        observable.notifyObservers();

        assertAll(
                () -> assertFalse(observer.isUpdated(), "Observer should not have been notified after removal")
        );
    }

    @Test
    void testAddSameObserverTwiceOnlyNotifiesOnce() {
        TestObservable observable = new TestObservable();
        AtomicBoolean updateCalled = new AtomicBoolean(false);

        Observer observer = new Observer() {
            @Override
            public void update(Observable observable) {
                if (updateCalled.get()) {
                    fail("Observer should only be notified once");
                }
                updateCalled.set(true);
            }
        };

        observable.addObserver(observer);
        observable.addObserver(observer); // should not be added twice
        observable.notifyObservers();

        assertAll(
                () -> assertTrue(updateCalled.get(), "Observer should have been notified once")
        );
    }
}
