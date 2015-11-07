package future;

import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.Track;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

        Future<Credentials> trackLogin = slowExternalSystem.trackDBLogin(userName);
        Future<Credentials> artistLogin = slowExternalSystem.artistDBLogin(userName);

        try {
            Credentials trackDBCredentials = trackLogin.get();
            System.out.println("got trackDBCredentials!");
            Credentials artistDBCredentials = artistLogin.get();
            System.out.println("got artistDBCredentials!");
            System.out.println("===============>>>> all credentials are found");

            Future<List<Track>> tracks = slowExternalSystem.lookupTracks(albumName, trackDBCredentials);
            Future<List<Artist>> artists = slowExternalSystem.lookupArtists(albumName, artistDBCredentials);
            List<Track> trackList = tracks.get();
            System.out.println("got trackList!");
            List<Artist> artistList = artists.get();
            System.out.println("got artistList!");
            System.out.println("===============>>>> all the data is found");
            System.out.println("slowExternalSystem is disconnected " + slowExternalSystem.disconnect());
            return new Album(albumName, trackList, artistList);
        } catch (InterruptedException | ExecutionException e) {
            throw new AlbumLookupException(e.getCause());
        }

    }

}
