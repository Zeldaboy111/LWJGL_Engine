package me.tom.engine.render;

import me.tom.engine.Window;
import org.lwjgl.opengl.GL30;

public final class RenderManager {

    /**
     *  Initializes the {@link RenderManager}
     * @throws RenderException
     */
    public void init() throws RenderException {

    }

    /**
     *  Renders
     * @throws RenderException
     */
    public void render() throws RenderException {

    }

    /**
     *  Clears the {@link Window}
     */
    public void clear() {
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
    }

    /**
     *  Cleans up the {@link RenderManager}
     */
    public void cleanup() {

    }
}
