package me.zeldaboy111.engine.graphic.render;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.shader.DefaultShaderProgram;
import me.zeldaboy111.engine.graphic.shader.ShaderProgram;
import me.zeldaboy111.engine.util.Util;
import me.zeldaboy111.engine.window.Window;

import static org.lwjgl.opengl.GL11.*;

public class BasicRenderer implements Renderer {
    protected ShaderProgram shaderProgram;
    @Override
    public void init() {
        shaderProgram = new DefaultShaderProgram();
        shaderProgram.createVertexShader(Util.readFile("./src/main/resources/shader/basic/vertex.vert"));
        shaderProgram.createFragmentShader(Util.readFile("./src/main/resources/shader/basic/fragment.frag"));
        shaderProgram.link();
    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void bind(final Window window) {
        if(window == null) {
            throw new RenderException("Cannot bind render to Window: Window null");
        }

        clear();
        glViewport(0, 0, window.getWidth(), window.getHeight());

        shaderProgram.bind();
    }

    @Override
    public void render(Mesh mesh) {
        if(mesh == null) {
            return;
        }

        mesh.bind();
        mesh.render();
        mesh.unbind();
    }

    @Override
    public void unbind() {
        shaderProgram.unbind();
    }

    @Override
    public void cleanup() {
        if(shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
