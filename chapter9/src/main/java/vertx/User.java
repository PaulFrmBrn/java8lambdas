package vertx;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.net.NetSocket;
import org.vertx.java.platform.Verticle;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author paul
 */
public class User implements Handler<Buffer> {

    private static final Pattern newline = Pattern.compile("\\n");
    private final NetSocket socket;
    private final Set<String> names;
    private final EventBus eventBus;
    private Optional<String> name;


    public User(NetSocket socket, Verticle verticle) {
        this.socket = socket;
        Vertx vertx = verticle.getVertx();
        names = vertx.sharedData().getSet("names");
        eventBus = vertx.eventBus();
        name = Optional.empty();
    }

    @Override
    public void handle(Buffer buffer) {
        newline.splitAsStream(
                buffer.toString()).forEach(line -> {
                    if (!name.isPresent()) { // setting name to the user
                        name = Optional.of(line);
                    } else {
                        eventBus.registerHandler( // javadoc
                                name.get(),
                                event -> System.out.println(name.get() + " send message: " + event.body().toString())
                        );
                        handleMessage(line);
                    }
                }
        );
    }

    private void handleMessage(String line) {
        System.out.println(name.get() + " received message: " + line);

    }


}
