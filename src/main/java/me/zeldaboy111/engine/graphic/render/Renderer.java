package me.zeldaboy111.engine.graphic.render;

public interface Renderer {

    /**
     *  Method used to initialize the {@link Renderer}
     */
    void init();

    /**
     *  Method used to clear the screen
     */
    void clear();

    /**
     *  Method used to clean up the {@link Renderer}
     */
    void cleanup();

}
