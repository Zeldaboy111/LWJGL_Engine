package me.zeldaboy111.engine.graphic.model;

public interface Mesh {

    /**
     * Method used to render the {@link Mesh}
     */
    void render();

    /**
     *  Method used to bind the {@link Mesh}
     */
    void bind();

    /**
     *  Used to unbind the {@link Mesh}
     */
    void unbind();

}
