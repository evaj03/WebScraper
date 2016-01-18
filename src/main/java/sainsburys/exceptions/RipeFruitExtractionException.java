package sainsburys.exceptions;

public class RipeFruitExtractionException extends BaseException {
	private static final long serialVersionUID = 3796110128952356055L;


	public RipeFruitExtractionException(final String message) {
        super(message);
    }


    public RipeFruitExtractionException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
