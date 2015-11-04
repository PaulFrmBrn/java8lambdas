package solid.dependency.inversion.good;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author paul
 */
// Separated logic of file reader, which does not know anything about handler implementation
// and can be replaced (e.g. by database reader) with no harm to handler implementation
public class FileReader {

    public <T> T withLilnesOf (Reader input,
                               Function<Stream<String>,T> handler,
                               Function<IOException,RuntimeException> error
                               ) {
        try(BufferedReader reader = new BufferedReader(input)) {
            return handler.apply(reader.lines());
        } catch (IOException e)  {
            throw error.apply(e);
        }
    }

}
