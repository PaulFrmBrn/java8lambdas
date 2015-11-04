package patterns.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

/**
 * @author paul
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("patterns/strategy");

        //oldway way
        Compressor zipCompressor = new Compressor(new ZipCompressionStrategy());
        zipCompressor.compress(Paths.get("input.txt"),new File("output.zip"));

        // newway way
        // classes ZipCompressionStrategy and GzipCompressionStrategy are redundant
        Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
        gzipCompressor.compress(Paths.get("input.txt"),new File("output.gzip"));

    }
}
