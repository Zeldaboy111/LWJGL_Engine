package me.zeldaboy111.engine.logic;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.model.BasicMesh;
import me.zeldaboy111.engine.graphic.render.DefaultRenderer;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.scene.DefaultScene;
import me.zeldaboy111.engine.scene.Scene;
import me.zeldaboy111.engine.window.Window;
import org.lwjgl.glfw.GLFW;

public class DefaultAppLogic implements AppLogic {
    private final Scene scene;
    private final Renderer renderer;
    private float color;


    public DefaultAppLogic() {
        renderer = new DefaultRenderer();
        scene = new DefaultScene();
    }

    @Override
    public void init() {
        renderer.init();

        float[] vertices = new float[] {
                -0.5f,  0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f,  0.5f, 0.0f
        };
        int[] indices = new int[]{
                0, 1, 3,
                3, 1, 2
        };

        final Mesh mesh = new BasicMesh(vertices, indices);
        scene.addMesh("rectangle", mesh);
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
        scene.render(window, renderer);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        scene.cleanup();
    }
}
