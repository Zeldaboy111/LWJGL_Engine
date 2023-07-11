package me.zeldaboy111.engine.graphic.render;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.shader.DefaultShaderProgram;
import me.zeldaboy111.engine.graphic.shader.ShaderProgram;
import me.zeldaboy111.engine.util.Util;
import me.zeldaboy111.engine.window.Window;

import static org.lwjgl.opengl.GL11.*;

public class DefaultRenderer implements Renderer {
    private ShaderProgram shaderProgram;
    @Override
    public void init() {
        shaderProgram = new DefaultShaderProgram();
        shaderProgram.createVertexShader(Util.readFile("./src/main/resources/shader/vertex.vert"));
        shaderProgram.createFragmentShader(Util.readFile("./src/main/resources/shader/fragment.frag"));
        shaderProgram.link();
    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void render(Window window, Mesh mesh) {
        clear();
        glViewport(0, 0, window.getWidth(), window.getHeight());

        // Render
        if(mesh == null) {
            return;
        }

        shaderProgram.bind();

        mesh.bind();
        mesh.render();
        mesh.unbind();

        shaderProgram.unbind();
    }

    @Override
    public void cleanup() {
        if(shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
