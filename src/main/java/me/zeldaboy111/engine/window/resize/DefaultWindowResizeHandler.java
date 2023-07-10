package me.zeldaboy111.engine.window.resize;

import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class DefaultWindowResizeHandler implements WindowResizeHandler {
    private int width, height;
    public DefaultWindowResizeHandler() {
        width = -1;
        height = -1;
    }

    @Override
    public void handle(final long window, final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void loadSizeFromWindowUsingGLFW(long window) {
        try(MemoryStack stack = stackPush()) {
            final IntBuffer width = stack.mallocInt(1);
            final IntBuffer height = stack.mallocInt(1);

            // Store the size passed in 'glfwCreateWindow' in the IntBuffers
            glfwGetWindowSize(window, width, height);

            this.width = width.get();
            this.height = height.get();
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
