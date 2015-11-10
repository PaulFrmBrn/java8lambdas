package excerice;

import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.SampleData;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author paul
 */
public class Main {

    public static final String THE_BEATLES = "The Beatles";
    public static final String JOHN_COLTRANE = "John Coltrane";


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("Hello, world!");

        Function<String, Artist> artistLookupService = artistName -> {
            try {
                if (THE_BEATLES.equals(artistName)) {
                    System.out.println(">>> beatles...");
                    Thread.sleep(7000L);
                    System.out.println("<<< beatles...");
                    return SampleData.theBeatles;
                } else {
                    System.out.println(">>> coltrane...");
                    Thread.sleep(5000L);
                    System.out.println("<<< coltrane...");
                    return SampleData.johnColtrane;
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        };

        BlockingArtistAnalyser blockingArtistAnalyser = new BlockingArtistAnalyser(artistLookupService);
        long startSync = System.currentTimeMillis();
        System.out.println("blockingArtistAnalyser.isLargerGroup(THE_BEATLES, JOHN_COLTRANE) = " +
                blockingArtistAnalyser.isLargerGroup(THE_BEATLES, JOHN_COLTRANE)
        );
        long endSync = System.currentTimeMillis();
        System.out.println("sync = " + (endSync - startSync));

        AsyncArtistAnalyzer asyncArtistAnalyser = new AsyncArtistAnalyzer(artistLookupService);
        long startAsync = System.currentTimeMillis();
        System.out.println("asyncArtistAnalyser.isLargerGroup(THE_BEATLES, JOHN_COLTRANE) = " +
                asyncArtistAnalyser.isLargerGroup(THE_BEATLES, JOHN_COLTRANE).get()
        );
        long endAsync = System.currentTimeMillis();
        System.out.println("async = " + (endAsync - startAsync));

    }

}
