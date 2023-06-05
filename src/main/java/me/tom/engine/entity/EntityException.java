package me.tom.engine.entity;

import me.tom.engine.EngineException;

public class EntityException extends EngineException {
    EntityException(final String message) {
        super(message);
    }
}
