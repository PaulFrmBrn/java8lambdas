package completable.future;

import com.paulfrmbrn.lambda8.example.Artist;
import com.paulfrmbrn.lambda8.example.Track;
import future.AlbumLookupException;
import future.Credentials;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author paul
 */
public class SlowExternalSystem {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public boolean disconnect() throws InterruptedException {
        return executor.awaitTermination(25, TimeUnit.SECONDS);
    }


    public CompletableFuture<Credentials> trackDBLogin(String userName) {

        return CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    System.out.println("--> trackDBLogin start");
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000 + new Random().nextInt(7000));
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e.getMessage());
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("<-- trackDBLogin end. duration = " + (end - start));
                    return new Credentials(userName);
                }, executor
        );

    }

    public CompletableFuture<Credentials> artistDBLogin(String userName) {

        return CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    System.out.println("--> artistDBLogin start");
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000 + new Random().nextInt(7000));
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e.getMessage());
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("<-- artistDBLogin end. duration = " + (end - start));
                    return new Credentials(userName);
                }, executor
        );

    }

    public CompletableFuture<List<Track>> lookupTracks(String albumName, Credentials credentials) {

        if (!credentials.isAuthorized()) {
            throw new AlbumLookupException("user is not authenticated");
        }
        if (!"Californication".equals(albumName)) {
            throw new AlbumLookupException("no information found on album " + albumName);
        }

        return CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    System.out.println(">>> lookupTracks start");
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000 + new Random().nextInt(7000));
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e.getMessage());
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("<<< lookupTracks end. duration = " + (end - start));
                    return Arrays.asList(
                            new Track("Around the World", 0),
                            new Track("Parallel Universe", 0),
                            new Track("Scar Tissue", 0),
                            new Track("Otherside", 0),
                            new Track("Get on Top", 0),
                            new Track("Californication", 0),
                            new Track("Easily", 0),
                            new Track("Porcelain", 0),
                            new Track("Emit Remmus", 0),
                            new Track("I Like Dirt", 0),
                            new Track("This Velvet Glove", 0),
                            new Track("Savior", 0),
                            new Track("Purple Stain", 0),
                            new Track("Right on Time", 0),
                            new Track("Road Trippin'", 0)
                    );
                }, executor
        );
    }

    public CompletableFuture<List<Artist>> lookupArtists(String albumName, Credentials credentials) {

        if (!credentials.isAuthorized()) {
            throw new AlbumLookupException("user is not authenticated");
        }
        if (!"Californication".equals(albumName)) {
            throw new AlbumLookupException("no information found on album " + albumName);
        }

        return CompletableFuture.supplyAsync(() -> {
                    long start = System.currentTimeMillis();
                    System.out.println(">>> lookupArtists start");
                    try {
                        TimeUnit.MILLISECONDS.sleep(13000 + new Random().nextInt(7000));
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e.getMessage());
                    }
                    long end = System.currentTimeMillis();
                    System.out.println("<<< lookupArtists end. duration = " + (end - start));
                    return Arrays.asList(
                            new Artist("Anthony Kiedis", "US"),
                            new Artist("Michael Peter Balzary", "US"),
                            new Artist("John Anthony Frusciante", "US"),
                            new Artist("Chad Gaylord Smith", "US")
                    );
                }, executor
        );

    }

}
