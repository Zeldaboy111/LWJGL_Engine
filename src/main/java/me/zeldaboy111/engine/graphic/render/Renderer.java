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
     *  Used to bind the {@link Renderer} thereby preparing it to render
     * @param window - {@link Window} to bind the {@link Renderer} to
     */
    void bind(final Window window);

    /**
     *  Method used to render
     * @param mesh - {@link Mesh} to render
     */
    void render(final Mesh mesh);

    /**
     *  Used to unbind the {@link Renderer}
     */
    void unbind();

    /**
     *  Method used to clean up the {@link Renderer}
     */
    void cleanup();

}
