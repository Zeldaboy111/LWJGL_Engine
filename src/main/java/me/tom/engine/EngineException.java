package me.tom.engine;

/**
 *  Exception thrown if anything with the Engine goes wrong
 */
public class EngineException extends Exception {
    protected EngineException(final String message) {
        super(message);
    }
    protected EngineException(final Exception e) {
        super(e);
    }
}
