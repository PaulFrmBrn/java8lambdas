package solid.dependency.inversion.good;

import solid.dependency.inversion.HeadingLookupException;

import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author paul
 */
public class HeadingFinder {

    public List<String> findHeadings(Reader input, FileReader fileReader) {

        Function<Stream<String>,List<String>> handlerImplementation =
                (stream) -> stream.filter(line -> line.endsWith(";"))
                                  .map(line -> line.substring(0, line.length() - 1))
                                  .collect(Collectors.toList());

        // FileReader do not know a thing about handler implementation
        // Handler implementation do not know a thing about FileReader
        // HeadingFinder control interaction of FileReader and Handler implementation
        return fileReader.withLilnesOf(
                input,
                handlerImplementation,
                HeadingLookupException::new
        );

    }

}
