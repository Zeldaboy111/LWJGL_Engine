package me.zeldaboy111.engine.window;

/**
 *  Class used to configure the {@link Window}
 */
public final class WindowConfiguration {
    private String title;
    private boolean resizable;
    private int width, height;
    private int framesPerSecond, updatesPerSecond;

    public WindowConfiguration() {
        title = "LWJGL Engine";
        resizable = true;
        width = 1200;
        height = 1000;

        framesPerSecond = 60;
        updatesPerSecond = 20;
    }

    /*
        SETTERS
     */

    /**
     *  Sets whether the window may be resized by the user
     * @param resizable - Whether the window may be resized by the user
     * @return
     */
    public WindowConfiguration setResizable(final boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    /**
     *  Set the width to open the {@link Window} at to the given value
     * @param width - New width to open the {@link Window} as
     * @return
     */
    public WindowConfiguration setWidth(final int width) {
        if(width > 0) {
            this.width = width;
        }

        return this;
    }

    /**
     *  Set the height to open the {@link Window} at to the given value
     * @param height - New height to open the {@link Window} as
     * @return
     */
    public WindowConfiguration setHeight(final int height) {
        if(height > 0) {
            this.height = height;
        }

        return this;
    }

    /**
     *  Sets the title to open the {@link Window} with to the given name
     * @param title - New title to open the {@link Window} with
     * @return
     */
    public WindowConfiguration setTitle(final String title) {
        if(title != null) {
            this.title = title;
        }

        return this;
    }

    /**
     *  Sets the amount of frames displayed every second
     * @param framesPerSecond - New amount of frames to be displayed every second
     * @return
     */
    public WindowConfiguration setFramesPerSecond(final int framesPerSecond) {
        if(framesPerSecond > -1) {
            this.framesPerSecond = framesPerSecond;
        }

        return this;
    }

    /**
     *  Sets the amount of updates processed per second
     * @param updatesPerSecond - New amount of updates to be processed every second
     * @return
     */
    public WindowConfiguration setUpdatesPerSecond(final int updatesPerSecond) {
        if(updatesPerSecond > -1) {
            this.updatesPerSecond = updatesPerSecond;
        }

        return this;
    }


    /*
        GETTERS
     */
    String getTitle() { return title; }
    boolean isResizable() { return resizable; }
    int getWidth() { return width; }
    int getHeight() { return height; }
    public int getFramesPerSecond() { return framesPerSecond; }
    public int getUpdatesPerSecond() { return updatesPerSecond; }
}
