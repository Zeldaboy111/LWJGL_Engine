package me.zeldaboy111.engine.window;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class DefaultWindow implements Window {
    private final long handle;
    DefaultWindow(final WindowBuilder builder) throws WindowInitializationException {
        handle = WindowSetup.setup(builder);
    }


    @Override
    public long getHandle() { return handle; }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    @Override
    public boolean isKeyPressed(final int keyCode) {
        return glfwGetKey(handle, keyCode) == GLFW_PRESS;
    }

    @Override
    public void setTitle(final String title) {
        if(title == null) {
            return;
        }

        glfwSetWindowTitle(handle, title);
    }

    @Override
    public void cleanup() {
        glfwFreeCallbacks(handle);

        // Destroy & Terminate
        glfwDestroyWindow(handle);
        glfwTerminate();

        GLFWErrorCallback callback = glfwSetErrorCallback(null);
        if (callback != null) {
            callback.free();
        }
    }
}
