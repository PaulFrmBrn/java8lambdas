import com.paulfrmbrn.lambda8.example.Artist;

import java.util.List;
import java.util.Optional;

/**
 * @author paul
 */
public class Artists {

    private List<Artist> artists;

    public Artists(List<Artist> artists) {
        this.artists = artists;
    }

    public Artist getArtist(int index) {
        if (index < 0 || index > artists.size() -1) { // mistake in the example: -1 was missed
            throw new IllegalArgumentException(index + " doesn't correspond to an artist");
        }
        return artists.get(index);
     }

    public String getArtistName(int index) {
        try {
            Artist artist = getArtist(index);
            return artist.getName();
        } catch (IllegalArgumentException e) {
            return "unknown";
        }

    }

    public Optional<Artist> getArtistFixed(int index) {
        if (index < 0 || index > artists.size() -1) {
            return Optional.empty();
        }
        return Optional.of(artists.get(index));
    }

    public String getArtistNameFixed(int index) {
        Optional<Artist> artist = getArtistFixed(index);
        //return artist.isPresent() ? artist.get().getName() : "unknown";
        return artist.map(Artist::getName).orElse("unknown");


    }

}
