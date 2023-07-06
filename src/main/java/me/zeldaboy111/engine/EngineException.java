package me.zeldaboy111.engine;

public class EngineException extends RuntimeException {
    protected EngineException(final String message) {
        super(message);
    }
    protected EngineException(final Exception e) {
        super(e);
    }

}
