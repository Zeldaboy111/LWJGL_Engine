package me.zeldaboy111.engine.logic;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.model.MeshLoader;
import me.zeldaboy111.engine.graphic.render.DefaultRenderer;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.window.Window;
import org.lwjgl.glfw.GLFW;

public class DefaultAppLogic implements AppLogic {
    private final Renderer renderer;
    private float color;
    private Mesh mesh;


    public DefaultAppLogic() {
        renderer = new DefaultRenderer();
    }

    @Override
    public void init() {
        renderer.init();

        float[] vertices = new float[]{
                0.0f,  0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        };

        mesh = MeshLoader.loadMesh(vertices);
    }

    @Override
    public void input(Window window) {
        if(window.isKeyPressed(GLFW.GLFW_KEY_A)) {
            window.setFullScreen(true);
        } else if(window.isKeyPressed(GLFW.GLFW_KEY_S)) {
            window.setFullScreen(false);
        }
    }

    @Override
    public void update(double timePassed) {
        color += timePassed;
        if(color > 1f) {
            color = 0;
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, mesh);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        MeshLoader.cleanup();
    }
}
