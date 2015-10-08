import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.paulfrmbrn.lambda8.example.Album;
import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.SampleData;

/**
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        // 1.a
        System.out.println("addUpResult = " + addUp(Stream.of(3, 5)));
        System.out.println("addUpResult = " + addUp(Stream.of(11, 5)));

        // 1.b
        System.out.println("getArtistsInfo(Artist.getSampleArtistList()) = " + getArtistsInfo(SampleData.getThreeArtists()));

        // 1.c
        List<Album> albums = new ArrayList<>();
        albums.add(SampleData.aLoveSupreme);
        albums.add(SampleData.sampleShortAlbum);
        albums.add(SampleData.manyTrackAlbum);
        System.out.println("getSmallAlbums(albums) = " + getSmallAlbums(albums));

    }

    public static int addUp (Stream<Integer> numbers) {
        return numbers.reduce(0,(accumulator,element) -> accumulator + element);
    }

    public static List<String> getArtistsInfo(List<Artist> artists) {
        //return artist.getMembers().map(artist1 -> artist.getName() + " is from " +  artist.getNationality()).collect(Collectors.toList()) ;
        //return artists.stream().map(Artist::getName).collect(Collectors.toList());
        //return artists.stream().map(artist -> artist.getName()).collect(Collectors.toList());
        //return artists.stream().map(artist -> artist.getName() + " is from " + artist.getNationality()).collect(Collectors.toList());
        return artists.stream().flatMap(artist -> Stream.of(artist.getName(), artist.getNationality())).collect(Collectors.toList());
    }

    public static List<String> getSmallAlbums(List<Album> albums) {
        return albums.stream().filter(album -> album.getTrackList().size() <= 3).map(Album::getName).collect(Collectors.toList());
    }


}
