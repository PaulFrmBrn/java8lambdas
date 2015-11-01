package Strategy;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author paul
 */
public class ZipCompressionStrategy implements CompressionStrategy  {
    @Override
    public OutputStream compress(OutputStream data) throws IOException {
        return new ZipOutputStream(data);
    }
}
