package me.tom.engine.render;

import me.tom.engine.EngineException;
import me.tom.engine.entity.Entity;
import me.tom.engine.entity.Model;
import me.tom.engine.render.shader.ShaderManager;
import me.tom.engine.utils.Transformation;
import me.tom.engine.utils.Utils;
import org.lwjgl.opengl.GL30;

public final class DefaultRenderManager implements RenderManager {
    private ShaderManager shader;

    @Override
    public void init() throws EngineException {
        // Create new  shader
        shader = new ShaderManager();

        shader.createVertexShader(Utils.loadResource("shader/vertex.vert"));
        shader.createFragmentShader( Utils.loadResource("shader/fragment.frag"));
        shader.link();

        // Create uniforms
        shader.getUniforms().create("textureSampler");
        shader.getUniforms().create("transformationMatrix");
    }

    @Override
    public void render(final Entity entity) throws RenderException {
        // Run checks
        if(entity == null) {
            throw new RenderException("Cannot render Entity: null");
        }

        clear();
        shader.bind();

        // Set uniforms
        shader.getUniforms().set("textureSampler", 0);
        shader.getUniforms().set("transformationMatrix", Transformation.createTransformationMatrix(entity));

        // Setup before rendering
        GL30.glBindVertexArray(entity.getModel().getId());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);

        // Texture
        GL30.glActiveTexture(GL30.GL_TEXTURE0);
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, entity.getModel().getTexture().getId());

        // Do the actual rendering
        GL30.glDrawElements(GL30.GL_TRIANGLES, entity.getModel().getVertexCount(), GL30.GL_UNSIGNED_INT, 0);

        // Clean up
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.unbind();
    }

    @Override
    public void clear() {
        GL30.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void cleanup() {
        shader.cleanup();
    }
}
