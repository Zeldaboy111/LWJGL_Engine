package me.tom.engine.render;

import me.tom.engine.EngineException;
import me.tom.engine.entity.Entity;
import me.tom.engine.entity.Model;

public interface RenderManager {
    /**
     *  Initializes the {@link RenderManager}
     * @throws RenderException Thrown if any error occurs during initialization
     */
    void init() throws EngineException;


    /**
     *  Renders the given {@link Entity}
     * @param entity - {@link Entity} to be rendered
     * @throws RenderException Thrown if any error occurs whilst rendering
     */
    void render(final Entity entity) throws RenderException;

    /**
     *  Clears the screen
     */
    void clear();

    /**
     *  Cleans up the {@link RenderManager}
     */
    void cleanup();
}
