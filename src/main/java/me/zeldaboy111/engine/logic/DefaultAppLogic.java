package me.zeldaboy111.engine.logic;

import me.zeldaboy111.engine.graphic.render.DefaultRenderer;
import me.zeldaboy111.engine.graphic.render.Renderer;
import me.zeldaboy111.engine.window.Window;

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
        System.out.println(" COLOR: " + color);
        window.setClearColor(color, color, color);
        renderer.clear();
    }
}
