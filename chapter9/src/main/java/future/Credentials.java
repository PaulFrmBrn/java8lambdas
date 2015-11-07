package future;

/**
 * @author paul
 */
public class Credentials {
    private final boolean authorized;

    public Credentials(String userName) {
        authorized = "paul".equals(userName);
    }

    public boolean isAuthorized() {
        return authorized;
    }
}
