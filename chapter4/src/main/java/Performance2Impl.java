import com.paulfrmbrn.lambda8.example.Artist;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author paul
 */
public class Performance2Impl implements Performance2 {

    private final String name;
    private final List<Artist> participants;

    public Performance2Impl(String name, List<Artist> participants) {
        this.name = name;
        this.participants = participants;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Stream<Artist> getMusicians() {
        return this.participants.stream();
    }
}
