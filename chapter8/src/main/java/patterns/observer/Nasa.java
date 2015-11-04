package patterns.observer;

/**
 * @author paul
 */
public class Nasa implements LandingObserver {
    @Override
    public void observeLanding(String name) {
        if (name.contains("Apollo")) {
            System.out.println("We've done it!");
        }
    }
}
