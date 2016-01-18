package sainsburys.exceptions;

public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseException(final String message) {
        super(message);
    }

    public BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }
 }
