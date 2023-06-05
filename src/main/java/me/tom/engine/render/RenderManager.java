package me.tom.engine.render;

import me.tom.engine.EngineException;
import me.tom.engine.Window;
import me.tom.engine.entity.Model;
import me.tom.engine.render.shader.ShaderManager;
import me.tom.engine.utils.Utils;
import org.lwjgl.opengl.GL30;

public final class RenderManager {
    private ShaderManager shader;


    /**
     *  Initializes the {@link RenderManager}
     * @throws RenderException
     */
    public void init() throws EngineException {
        // Create new  shader
        shader = new ShaderManager();

        shader.createVertexShader(Utils.loadResource("shader/vertex.vert"));
        shader.createFragmentShader(Utils.loadResource("shader/fragment.frag"));
        shader.link();
    }

    /**
     *  Renders the given {@link Model}
     * @param model - {@link Model} to be rendered
     * @throws RenderException
     */
    public void render(final Model model) throws RenderException {
        // Run checks
        if(model == null) {
            throw new RenderException("Cannot render Model: null");
        }

        clear();
        shader.bind();

        // Setup before rendering
        GL30.glBindVertexArray(model.getId());
        GL30.glEnableVertexAttribArray(0);

        // Do the actual rendering
        GL30.glDrawArrays(GL30.GL_TRIANGLES, 0, model.getVertexCount());

        // Clean up
        GL30.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.unbind();
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
        shader.cleanup();
    }
}
