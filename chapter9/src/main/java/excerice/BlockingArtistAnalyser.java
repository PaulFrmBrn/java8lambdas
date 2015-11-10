package excerice;

import com.paulfrmbrn.lambda8.example.Artist;

import java.util.function.Function;

/**
 * @author paul
 */
public class BlockingArtistAnalyser {

    private final Function<String,Artist> artistLookupService;

    public BlockingArtistAnalyser(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    public boolean isLargerGroup(String artistName, String otehrArtistName) {
        return getNumberOfMembers(artistName) > getNumberOfMembers(otehrArtistName);
    }

    private long getNumberOfMembers(String artistName) {
        return artistLookupService.apply(artistName).getMembers().count();
    }
}
