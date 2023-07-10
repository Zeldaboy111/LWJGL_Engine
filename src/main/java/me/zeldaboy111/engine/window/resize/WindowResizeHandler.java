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
     *  Used to set whether the {@link me.zeldaboy111.engine.window.Window} should be centered on the monitor after
     *  fullscreen-mode has been enabled or disabled
     * @param centerOnWindow - Whether the {@link me.zeldaboy111.engine.window.Window} should be centered upon toggling
     *                       fullscreen-mode
     */
    void setCenterOnMonitorAfterToggleFullscreen(final boolean centerOnWindow);

    /**
     *  Method used to center the {@link me.zeldaboy111.engine.window.Window} of the given handle on the primary
     *  monitor
     * @param window - Handle from the {@link me.zeldaboy111.engine.window.Window} to center
     */
    void centerMonitor(final long window);

    /**
     *  Makes the {@link me.zeldaboy111.engine.window.Window} display either fullscreen or not
     * @param window - Handle from the {@link me.zeldaboy111.engine.window.Window} to make either fullscreen or windowed
     * @param fullScreen - Whether the {@link me.zeldaboy111.engine.window.Window} should be displayed fullscreen
     */
    void setFullScreen(final long window, final boolean fullScreen);

    /**
     *  Gets if the {@link me.zeldaboy111.engine.window.Window} is being displayed fullscreen or not
     * @return
     */
    boolean isFullScreen();

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
