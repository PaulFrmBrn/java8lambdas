import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.SampleData;
import com.paulfrmbrn.lambda8.example.Track;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello, world!");

        // Example 7.3
        ThreadLocal<String> localStringAnonymous = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "long complicated string value from dta base";
            }
        };

        // Example 7.4
        ThreadLocal<String> localStringLambda =
                ThreadLocal.withInitial(() -> "long complicated string value from dta base");

        Album album = new Album("sample Short Album", asList(new Track("short track", 30)),
                asList(SampleData.johnColtrane, SampleData.theBeatles, SampleData.johnLennon));

        album.getMusicians()
             //.filter(artist -> artist.getName().startsWith("The"))
             .map(Artist::getNationality)
             .peek(nationality -> System.out.println("Found nationality = " + nationality))
             .collect(Collectors.toSet());

    }

}
