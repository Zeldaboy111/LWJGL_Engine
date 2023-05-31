package me.tom.engine;

/**
 *  Exception thrown if anything with the Engine goes wrong
 */
public class EngineException extends Exception {
    public EngineException(final String message) {
        super(message);
    }
    public EngineException(final Exception e) {
        super(e);
    }
}
