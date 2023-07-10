package me.zeldaboy111.engine.graphic.render;

import static org.lwjgl.opengl.GL11.*;

public class DefaultRenderer implements Renderer {
    @Override
    public void init() {

    }

    @Override
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
