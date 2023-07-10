package me.zeldaboy111.engine.window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public interface Window {

    /**
     *  Gets the handle of the {@link Window}
     * @return Handle of the {@link Window}
     */
    long getHandle();

    /**
     *  Method used to poll events / listen for events
     */
    default void pollEvents() {
        glfwPollEvents();
    }

    /**
     *  Method used to center the {@link Window} on the monitor
     */
    void centerOnMonitor();

    /**
     *  Makes the {@link Window} either fullscreen or not (based off the given value)
     * @param fullScreen - Whether the {@link Window} should be fullscreen or not
     */
    void setFullScreen(boolean fullScreen);

    /**
     *  Method used to swap the buffers thus displaying the last rendered scene
     */
    void swapBuffers();

    /**
     *  Method used to set the clear color from the {@link Window}
     * @param red - Red-value from the clear color
     * @param green - Green-value from the clear color
     * @param blue - Blue-value from the clear color
     */
    default void setClearColor(final float red, final float green, final float blue) {
        setClearColor(red, green, blue, 0f);
    }

    /**
     *  Method used to set the clear color from the {@link Window}
     * @param red - Red-value from the clear color
     * @param green - Green-value from the clear color
     * @param blue - Blue-value from the clear color
     * @param alpha - Alpha-value from the clear color
     */
    default void setClearColor(final float red, final float green, final float blue, final float alpha) {
        glClearColor(red, green, blue, alpha);
    }

    /**
     *  Gets whether the {@link Window} should be closed or not
     * @return Whether the {@link Window} should be closed
     */
    boolean shouldClose();

    /**
     *  Gets whether the key of given code is being pressed
     * @param keyCode - Code from key to check whether it is being pressed
     * @return Whether the key of the given code is being pressed
     */
    boolean isKeyPressed(final int keyCode);

    /**
     *  Used to set the title of the {@link Window} to the given {@param title}
     * @param title - New title of the {@link Window}
     */
    void setTitle(final String title);

    /**
     *  Gets the width from the {@link Window}
     * @return Width from the {@link Window}
     */
    int getWidth();

    /**
     *  Gets the height from the {@link Window}
     * @return Height from the {@link Window}
     */
    int getHeight();

    /**
     *  Method used to clean up the {@link Window}
     */
    void cleanup();
}
