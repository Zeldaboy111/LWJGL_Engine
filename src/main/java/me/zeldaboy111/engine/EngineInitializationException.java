package me.zeldaboy111.engine;

public class EngineInitializationException extends Exception {
    protected EngineInitializationException(final String message) {
        super(message);
    }
    protected EngineInitializationException(final Exception e) {
        super(e);
    }

}
