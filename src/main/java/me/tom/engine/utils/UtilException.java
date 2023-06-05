package me.tom.engine.utils;

import me.tom.engine.EngineException;

public class UtilException extends EngineException {
    protected UtilException(final String message) {
        super(message);
    }
    protected UtilException(final Exception e) {
        super(e);
    }
}
