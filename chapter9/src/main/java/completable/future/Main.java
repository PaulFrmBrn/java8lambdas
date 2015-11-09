package completable.future;

import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.Track;

import java.util.stream.Collectors;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        SlowExternalSystem slowExternalSystem = new SlowExternalSystem();
        AlbumFinder albumFinder = new AlbumFinder(slowExternalSystem,"paul");
        System.out.println("start finding album");
        Album album;
        try {
            album = albumFinder.albumLookup("Californication");
        } catch (AlbumLookupException e) {
            System.out.println("error occurred: " + e.getMessage());
            return;
        }
        System.out.println("album found: " + album.getName());
        System.out.println("tracks: " + album.getTracks().map(Track::getName).collect(Collectors.toList()));
        System.out.println("artists: " + album.getMusicians().map(Artist::getName).collect(Collectors.toList()));

    }

}
