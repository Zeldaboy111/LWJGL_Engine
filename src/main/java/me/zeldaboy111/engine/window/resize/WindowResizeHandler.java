package me.zeldaboy111.engine.window.resize;

public interface WindowResizeHandler {

    /**
     *  Method used to load the size of the {@link me.zeldaboy111.engine.window.Window}
     *  using GLFW
     */
    void loadSizeFromWindowUsingGLFW(final long window);

    /**
     *  Method used to handle a resize from a {@link me.zeldaboy111.engine.window.Window}
     * @param window - Handle of the resized {@link me.zeldaboy111.engine.window.Window}
     * @param width - New width of the {@link me.zeldaboy111.engine.window.Window}
     * @param height - New height of the {@link me.zeldaboy111.engine.window.Window}
     */
    void handle(final long window, final int width, final int height);

    /**
     *  Gets the current width of the {@link me.zeldaboy111.engine.window.Window}
     * @return Current width of the {@link me.zeldaboy111.engine.window.Window}
     */
    int getWidth();

    /**
     *  Gets the current height of the {@link me.zeldaboy111.engine.window.Window}
     * @return Current height of the {@link me.zeldaboy111.engine.window.Window}
     */
    int getHeight();
}
