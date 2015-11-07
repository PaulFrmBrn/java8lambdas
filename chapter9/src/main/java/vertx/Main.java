package vertx;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, Vert.x!");

        ChatVerticle chatVerticle = new ChatVerticle();
        chatVerticle.start();

//        EventBus eventBus = chatVerticle.getVertx().eventBus();
//
//        String paul = "paul";
//        eventBus.registerHandler(paul,
//                event -> System.out.println(paul + " << : " + event.body()));

    }

}
