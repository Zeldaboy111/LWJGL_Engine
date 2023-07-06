package me.zeldaboy111.engine.loop;

import me.zeldaboy111.engine.EngineInitializationException;

public class LoopInitializationException extends EngineInitializationException {
    protected LoopInitializationException(String message) {
        super(message);
    }
    protected LoopInitializationException(Exception e) {
        super(e);
    }
}
