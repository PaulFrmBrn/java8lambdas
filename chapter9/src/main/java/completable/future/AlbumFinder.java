package completable.future;

import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;
import future.*;
import future.Credentials;

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
        CompletableFuture<Credentials> credentialsFuture = slowExternalSystem.artistDBLogin(userName);
        System.out.println("nothing is happening here...");
        CompletableFuture<List<Artist>> artistLookup = credentialsFuture
                .thenCompose(credentials -> slowExternalSystem.lookupArtists("Californication", credentials));

        System.out.println("sleep...");
        try {
            Thread.sleep(30000L);
        } catch (InterruptedException e) {
            System.out.println("Ooooops...");
        }
        System.out.println("...awake");
        return slowExternalSystem.trackDBLogin(userName)
                .thenCompose(credentials -> slowExternalSystem.lookupTracks("Californication",credentials))
                .thenCombine(artistLookup, (tracks,artists) -> new Album(albumName,tracks,artists)).join();
    }
}
