package com.texasholdem.team;

/**
 * Unchecked exception thrown when an attempt is made to draw a {@link Card} but none is available.
 */
public class NoCardsRemainingException extends RuntimeException {

    public NoCardsRemainingException() {
    }

    public NoCardsRemainingException(String message) {
        super(message);
    }

    public NoCardsRemainingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoCardsRemainingException(Throwable cause) {
        super(cause);
    }

}
