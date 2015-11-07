package vertx;

import org.vertx.java.core.net.NetServer;
import org.vertx.java.platform.Verticle;

/**
 * @author paul
 */
public class ChatVerticle extends Verticle {

    @Override
    public void start() {

        NetServer netServer = vertx.createNetServer();
        netServer.connectHandler(socket -> {
            container.logger().info("socket connected");
            socket.dataHandler(new User(socket,this));
        }).listen(10_000);
        container.logger().info("vertx.ChatVerticle started");
    }
}
