package me.zeldaboy111.engine.logic;

import me.zeldaboy111.engine.graphic.model.Mesh;
import me.zeldaboy111.engine.graphic.model.VertexMesh;
import me.zeldaboy111.engine.graphic.render.DefaultRenderer;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.window.Window;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class DefaultAppLogic implements AppLogic {
    private final List<Mesh> meshes;
    private final Renderer renderer;
    private float color;
    private Mesh mesh;


    public DefaultAppLogic() {
        renderer = new DefaultRenderer();
        meshes = new ArrayList<>();
    }

    @Override
    public void init() {
        renderer.init();

        float[] vertices = new float[]{
                0.0f,  0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f
        };

        //mesh = MeshLoader.loadMesh(vertices);
        mesh = new VertexMesh(vertices);
        meshes.add(mesh);
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
        meshes.forEach(Mesh::cleanup);
    }
}
