package me.zeldaboy111.engine.util;

import me.zeldaboy111.engine.EngineException;

public class UtilException extends EngineException {
    protected UtilException(String message) {
        super(message);
    }
    protected UtilException(String message, Exception e) {
        super(message, e);
    }
}
