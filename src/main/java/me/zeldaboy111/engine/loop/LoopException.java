package me.zeldaboy111.engine.loop;

import me.zeldaboy111.engine.EngineException;

public class LoopException extends EngineException {
    protected LoopException(String message) {
        super(message);
    }

    protected LoopException(Exception e) {
        super(e);
    }
}
