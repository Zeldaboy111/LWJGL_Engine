package me.zeldaboy111.engine.window;

import me.zeldaboy111.engine.window.resize.WindowResizeHandler;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class DefaultWindow implements Window {
    private final WindowResizeHandler resizeHandler;
    private final long handle;
    DefaultWindow(final WindowBuilder builder) throws WindowInitializationException {
        handle = WindowSetup.setup(builder);
        this.resizeHandler = builder.getResizeHandler();

        if(builder.centerOnMonitor()) {
            resizeHandler.setCenterOnMonitorAfterToggleFullscreen(true);
            centerOnMonitor();
        }
    }


    @Override
    public long getHandle() { return handle; }

    @Override
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }
    @Override
    public void centerOnMonitor() {
        resizeHandler.centerMonitor(handle);
    }

    @Override
    public void setFullScreen(boolean fullScreen) {
        resizeHandler.setFullScreen(handle, fullScreen);
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
    public int getWidth() {
        return resizeHandler.getWidth();
    }

    @Override
    public int getHeight() {
        return resizeHandler.getHeight();
    }

    @Override
    public void cleanup() {
        glfwFreeCallbacks(handle);

        // Destroy & Terminate
        glfwDestroyWindow(handle);
        glfwTerminate();

        freeErrorCallback();
    }

    /**
     *  Method used to free the error callback
     */
    private void freeErrorCallback() {
        try(GLFWErrorCallback callback = glfwSetErrorCallback(null)) {
            if (callback != null) {
                callback.free();
            }
        }
    }
}
