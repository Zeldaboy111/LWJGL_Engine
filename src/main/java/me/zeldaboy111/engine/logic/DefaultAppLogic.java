package me.zeldaboy111.engine.logic;

import me.zeldaboy111.engine.graphic.render.DefaultRenderer;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.window.Window;
import org.lwjgl.glfw.GLFW;

public class DefaultAppLogic implements AppLogic {
    private final Renderer renderer;
    private float color;
    public DefaultAppLogic() {
        renderer = new DefaultRenderer();
    }

    @Override
    public void init() {
        renderer.init();
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
        window.setClearColor(color, color, color);
        renderer.clear();
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
    }
}
