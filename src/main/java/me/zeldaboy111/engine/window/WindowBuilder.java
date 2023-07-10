package me.zeldaboy111.engine.window;

import me.zeldaboy111.engine.window.resize.DefaultWindowResizeHandler;
import me.zeldaboy111.engine.window.resize.WindowResizeHandler;

@SuppressWarnings("UnusedReturnValue")
public final class WindowBuilder {
    private String title;
    private int width, height;
    private boolean resizable, decorated, fullScreen, centerOnMonitor;
    private WindowResizeHandler resizeHandler;
    public WindowBuilder() {
        title = "LWJGL Engine © Zeldaboy111";
        width = 1200;
        height = 1000;
        resizable = true;
        decorated = true;
        fullScreen = false;
        centerOnMonitor = true;

        resizeHandler = new DefaultWindowResizeHandler();
    }

    /**
     *  Used to build the current configuration into a {@link Window}
     * @return Created {@link Window}
     * @throws WindowInitializationException - Thrown if any issues occur during the building of the {@link Window}
     */
    public synchronized Window build() throws WindowInitializationException {
        return new DefaultWindow(this);
    }


    /*
        SETTERS
     */
    /**
     *  Sets the title to open the {@link DefaultWindow} with to the given name
     * @param title - New title to open the {@link DefaultWindow} with
     */
    public WindowBuilder setTitle(final String title) {
        if(title != null) {
            this.title = title;
        }

        return this;
    }

    /**
     *  Set the width to open the {@link DefaultWindow} at to the given value
     * @param width - New width to open the {@link DefaultWindow} as
     */
    public WindowBuilder setWidth(final int width) {
        if(width > 0) {
            this.width = width;
        }

        return this;
    }

    /**
     *  Set the height to open the {@link DefaultWindow} at to the given value
     * @param height - New height to open the {@link DefaultWindow} as
     */
    public WindowBuilder setHeight(final int height) {
        if(height > 0) {
            this.height = height;
        }

        return this;
    }

    /**
     *  Sets whether the {@link Window} may be resized by the user
     * @param resizable - Whether the {@link Window} may be resized by the user
     */
    public WindowBuilder setResizable(final boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    /**
     *  Sets whether the {@link Window} is decorated or not (undecorated hides the top bar)
     * @param decorated - Whether the {@link Window} is decorated or not
     */
    public WindowBuilder setDecorated(final boolean decorated) {
        this.decorated = decorated;
        return this;
    }

    /**
     *  Sets whether the {@link Window} should be displayed on fullscreen or not
     * @param fullScreen - Whether the {@link Window} should be displayed fullscreen
     */
    public WindowBuilder setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
        return this;
    }

    /**
     *  Sets whether the {@link Window} should be centered on the monitor upon startup
     * @param centerOnMonitor - Whether the {@link Window} should be centered on the monitor upon startup
     */
    public WindowBuilder setCenterOnMonitor(final boolean centerOnMonitor) {
        this.centerOnMonitor = centerOnMonitor;
        return this;
    }

    /**
     *  Sets the {@link WindowResizeHandler} to be used upon resizing the {@link Window}
     * @param resizeHandler - {@link WindowResizeHandler} to be used upon resizing the {@link Window}
     */
    public WindowBuilder setResizeHandler(final WindowResizeHandler resizeHandler) {
        if(resizeHandler != null) {
            this.resizeHandler = resizeHandler;
        }

        return this;
    }

    String getTitle() { return title; }
    int getWidth() { return width; }
    int getHeight() { return height; }
    boolean isResizable() { return resizable; }
    boolean isDecorated() { return decorated; }
    boolean centerOnMonitor() { return centerOnMonitor; }
    boolean isFullScreen() { return fullScreen; }
    WindowResizeHandler getResizeHandler() { return resizeHandler; }
}
