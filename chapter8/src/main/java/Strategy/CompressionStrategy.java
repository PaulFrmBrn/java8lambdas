package Strategy;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author paul
 */
public interface CompressionStrategy {
    OutputStream compress(OutputStream data) throws IOException;
}