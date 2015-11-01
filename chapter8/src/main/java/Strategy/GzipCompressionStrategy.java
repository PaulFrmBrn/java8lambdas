package Strategy;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author paul
 */
public class GzipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new GZIPOutputStream(data);
    }
}
