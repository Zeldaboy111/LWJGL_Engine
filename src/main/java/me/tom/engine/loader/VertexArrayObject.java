package me.tom.engine.loader;

import org.lwjgl.opengl.GL30;

class VertexArrayObject {
    // When set to true, a VertexArrayObject will automatically be bound upon creation
    private static final boolean AUTO_BIND = true;

    private final int id;

    VertexArrayObject() {
        this(AUTO_BIND);
    }
    VertexArrayObject(final boolean bind) {
        id = GL30.glGenVertexArrays();

        if(bind) {
            bind(); // Should be bound based off the parameters thus bind
        }
    }

    /**
     *  Gets the id from the {@link VertexArrayObject}
     * @return Id from the {@link VertexArrayObject}
     */
    public int getId() { return id; }

    /**
     *  Binds the {@link VertexArrayObject}
     */
    public void bind() {
        GL30.glBindVertexArray(id);
    }

}
