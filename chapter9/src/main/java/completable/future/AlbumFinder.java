package completable.future;

import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author paul
 */
public class AlbumFinder {

    private final SlowExternalSystem slowExternalSystem;
    private final String userName;

    public AlbumFinder(SlowExternalSystem slowExternalSystem, String userName) {
        this.slowExternalSystem = slowExternalSystem;
        this.userName = userName;
    }

    @Nonnull
    public Album albumLookup(String albumName) {
        CompletableFuture<List<Artist>> artistLookup =
                slowExternalSystem.artistDBLogin(userName)
                .thenCompose(credentials -> slowExternalSystem.lookupArtists("Californication",credentials));

        return slowExternalSystem.trackDBLogin(userName)
                .thenCompose(credentials -> slowExternalSystem.lookupTracks("Californication",credentials))
                .thenCombine(artistLookup, (tracks,artists) -> new Album(albumName,tracks,artists)).join();
    }
}
