import com.paulfrmbrn.lambda8.example.SampleData;

import java.util.Arrays;

/**
 *
 *
 * @author paul
 */
public class Main {

    public static void main(String[] args) {

        // 1.
        Performance2 performance = new Performance2Impl("Rock fest",
                Arrays.asList(SampleData.johnColtrane,SampleData.theBeatles,SampleData.johnLennon)
        );
        performance.getAllMusicians().forEach(artist -> System.out.println("name = " + artist.getName()));

        // 2. No - both equals and hash code are defined in Java.lang.Object and cuold be overloaded only by
        // non default method, because "class always wins"

        // 3.
        Artists artists = new Artists(Arrays.asList(SampleData.johnColtrane,SampleData.theBeatles,SampleData.johnLennon));
        int index = 3;
        System.out.println("artists.getArtistName(index) = " + artists.getArtistName(index));
        try {
            System.out.println("artists.getArtist(index) = " + artists.getArtist(index));
        } catch (IllegalArgumentException e) {
            System.out.println("exception in artists.getArtist was thrown for index = " + index);
        }

        System.out.println("artists.getArtistNameFixed((index) = " + artists.getArtistNameFixed(index));
        System.out.println("artists.getArtistFixed((index) = " + artists.getArtistFixed(index));

    }

}
