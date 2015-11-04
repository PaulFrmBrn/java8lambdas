package patterns.observer;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        // oldway way
        Moon moon = new Moon();
        moon.startSpying(new Aliens());
        moon.startSpying(new Nasa());
        System.out.println("Asteroid");
        moon.land("An asteroid!");
        System.out.println("Spaceship");
        moon.land("Apollo 254!");

        // newway way
        // classes Aliens & Nasa are redundant
        Moon moon2 = new Moon();
        moon2.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("We've done it!");
            }
        });
        moon2.startSpying(name -> {
            if (name.contains("Apollo")) {
                System.out.println("Attack the Earth");
            }
        });
        System.out.println("Asteroid");
        moon2.land("An asteroid!");
        System.out.println("Spaceship");
        moon2.land("Apollo 254!");

    }

}
