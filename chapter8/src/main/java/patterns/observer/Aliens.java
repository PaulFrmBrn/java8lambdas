package patterns.observer;

/**
 * @author paul
 */
public class Aliens implements LandingObserver {

    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("Attack the Earth");
        }
    }
}
