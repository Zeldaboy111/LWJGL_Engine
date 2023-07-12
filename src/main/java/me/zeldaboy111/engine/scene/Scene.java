package me.zeldaboy111.engine.scene;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.window.Window;

public interface Scene {

    /**
     *  Method used to add a new {@link Mesh} to the {@link Scene}
     * @param id - ID from the {@link Mesh} to add
     * @param mesh - {@link Mesh} to add
     */
    void addMesh(final String id, final Mesh mesh);

    /**
     *  Method used to retrieve the {@link Mesh} from given ID
     * @param id - ID from the {@link Mesh} to retrieve
     * @return {@link Mesh} from given ID, if none found null is returned
     */
    Mesh getMesh(final String id);

    /**
     *  Used to render every object from the {@link Scene}
     * @param window - {@link Window} to render to
     * @param renderer - {@link Renderer} to use for rendering
     */
    void render(final Window window, final Renderer renderer);

    /**
     *  Used to render every object from the {@link Scene}
     * @param renderer - {@link Renderer} to use for rendering
     */
    void render(final Renderer renderer);

    /**
     *  Method used to clean up the {@link Scene}
     */
    void cleanup();

}

