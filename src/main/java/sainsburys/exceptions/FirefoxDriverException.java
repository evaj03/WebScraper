package sainsburys.exceptions;

public class FirefoxDriverException extends BaseException {
	private static final long serialVersionUID = -2297995493806740858L;


	public FirefoxDriverException(final String message) {
        super(message);
    }


    public FirefoxDriverException(final String message, final Throwable cause) {
        super(message, cause);
    }
}