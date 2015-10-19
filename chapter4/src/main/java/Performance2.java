import com.paulfrmbrn.lambda8.example.Artist;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

/**
 * @author paul
 */
public interface Performance2 {

    String getName();

    Stream<Artist> getMusicians();

    default Stream<Artist> getAllMusicians() {
        // my version - does not put artist itself in a result stream if members are present
        //return getMusicians().flatMap(artist -> artist.isSolo() ? Collections.singletonList(artist).stream() : artist.getMembers());
        return getMusicians().flatMap(artist -> concat(Stream.of(artist), artist.getMembers()));
    }

}
