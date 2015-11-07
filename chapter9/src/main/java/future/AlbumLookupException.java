package future;

/**
 * @author paul
 */
public class AlbumLookupException extends RuntimeException {
    public AlbumLookupException(Throwable cause) {
        super(cause);
    }
    public AlbumLookupException(String message) {
        super(message);
    }
}
