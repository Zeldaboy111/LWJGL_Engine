package me.zeldaboy111.engine.window;

public final class WindowBuilder {
    private String title;
    private int width, height;
    private boolean resizable;
    public WindowBuilder() {
        title = "LWJGL Engine © Zeldaboy111";
        width = 1200;
        height = 1000;
        resizable = true;
    }

    /**
     *  Used to build the current configuration into a {@link Window}
     * @return Created {@link Window}
     * @throws WindowInitializationException - Thrown if any issues occur during the building of the {@link Window}
     */
    public Window build() throws WindowInitializationException {
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
     *  Sets whether the window may be resized by the user
     * @param resizable - Whether the window may be resized by the user
     */
    public WindowBuilder setResizable(final boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    String getTitle() { return title; }
    boolean isResizable() { return resizable; }
    int getWidth() { return width; }
    int getHeight() { return height; }
}
