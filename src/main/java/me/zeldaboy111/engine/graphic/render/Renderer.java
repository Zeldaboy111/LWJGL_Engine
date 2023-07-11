package me.zeldaboy111.engine.graphic.render;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.window.Window;

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
     *  Method used to render
     * @param window - {@link Window} to render to
     * @param mesh - {@link Mesh} to render
     */
    void render(final Window window, final Mesh mesh);

    /**
     *  Method used to clean up the {@link Renderer}
     */
    void cleanup();

}
