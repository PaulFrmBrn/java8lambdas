package excerice;

import com.paulfrmbrn.lambda8.example.Artist;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @author paul
 */
public class AsyncArtistAnalyzer {

    private final Function<String,Artist> artistLookupService;

    public AsyncArtistAnalyzer(Function<String, Artist> artistLookupService) {
        this.artistLookupService = artistLookupService;
    }

    public CompletableFuture<Boolean> isLargerGroup(String artistName, String otherArtistName) {

        CompletableFuture<Long> firstArtistCount =
                CompletableFuture.supplyAsync(() -> artistLookupService.apply(artistName))
                                 .thenApply(artist -> artist.getMembers().count());

        CompletableFuture<Long> secondArtistCount =
                CompletableFuture.supplyAsync(() -> artistLookupService.apply(otherArtistName))
                        .thenApply(artist -> artist.getMembers().count());

        return secondArtistCount.thenCombineAsync(firstArtistCount, (firstCount, secondCount) -> firstCount > secondCount);
    }

    private long getNumberOfMembers(String artistName) {
        return artistLookupService.apply(artistName).getMembers().count();
    }

}
