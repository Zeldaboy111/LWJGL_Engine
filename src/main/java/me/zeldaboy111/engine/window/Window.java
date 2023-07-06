package me.zeldaboy111.engine.window;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public class Window {
    private long handle;
    private final WindowConfiguration configuration;
    public Window() throws WindowInitializationException {
        this(new WindowConfiguration());
    }

    public Window(final WindowConfiguration configuration) throws WindowInitializationException {
        this.configuration = configuration;

        handle = WindowSetup.setup(configuration);
    }


    /**
     *  Gets the handle of the {@link Window}
     * @return Handle of the {@link Window}
     */
    public long getHandle() { return handle; }

    /**
     *  Method used to poll events / listen for events
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     *  Method used to swap the buffers thus displaying the last rendered scene
     */
    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    /**
     *  Method used to set the clear color from the {@link Window}
     * @param red - Red-value from the clear color
     * @param green - Green-value from the clear color
     * @param blue - Blue-value from the clear color
     */
    public void setClearColor(final float red, final float green, final float blue) {
        setClearColor(red, green, blue, 0f);
    }

    /**
     *  Method used to set the clear color from the {@link Window}
     * @param red - Red-value from the clear color
     * @param green - Green-value from the clear color
     * @param blue - Blue-value from the clear color
     * @param alpha - Alpha-value from the clear color
     */
    public void setClearColor(final float red, final float green, final float blue, final float alpha) {
        glClearColor(red, green, blue, alpha);
    }

    /**
     *  Gets whether the {@link Window} should be closed or not
     * @return Whether the {@link Window} should be closed
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    /**
     *  Used to set the title of the {@link Window} to the given {@param title}
     * @param title - New title of the {@link Window}
     */
    public void setTitle(final String title) {
        if(title == null) {
            return;
        }

        glfwSetWindowTitle(handle, title);
    }

    /**
     *  Method used to cleanup the {@link Window}
     */
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
