package patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paul
 */
public class Moon {

    private final List<LandingObserver> observers = new ArrayList<>();

    public void land(String name) {
        observers.forEach(observer -> observer.observeLanding(name));
    }

    public void startSpying(LandingObserver observer) {
        observers.add(observer);
    }
}
