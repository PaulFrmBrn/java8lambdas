package Strategy;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author paul
 */
public class Compressor {
    private final CompressionStrategy compressionStrategy;

    public Compressor(CompressionStrategy compressionStrategy) {
        this.compressionStrategy = compressionStrategy;
    }

    public void compress(Path inputFile, File outputFile) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        try {
            Files.copy(inputFile, compressionStrategy.compress(outputStream));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

}
