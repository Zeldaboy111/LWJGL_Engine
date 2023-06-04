package me.tom.engine;

import org.joml.Matrix4f;

public final class WindowSettings {
    private static float FOV;
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000f;

    private String title;
    private int width, height;
    private boolean resized, vSync, maximized;
    private final Matrix4f projectionMatrix;

    public WindowSettings(final String title, final int width, final int height, final boolean vSync) throws EngineException {

        // Set instance variables
        this.vSync = vSync;
        this.projectionMatrix = new Matrix4f();
        this.resized = false;
        setTitle(title);
        setSize(width, height); // <-- validates size too
        setFOV(60);
    }

    /**
     *  Sets the field of view (FOV) to the radians-value from the given degrees
     * @param degrees - FOV in degrees
     */
    public void setFOV(final float degrees) {
        FOV = (float)Math.toRadians(degrees);
    }

    /**
     *  Gets the field of view (FOV)
     * @return
     */
    public float getFOV() { return FOV; }

    /**
     *  Gets the title to be used for the window
     * @return Title for the window
     */
    String getTitle() {
        return title;
    }

    /**
     *  Sets the title from the {@link WindowSettings} to the given title
     * @param title - {@link String} representing the new title
     * @throws EngineException
     */
    void setTitle(final String title) throws EngineException {
        if(title == null || title.trim().equals("")) {
            throw new EngineException("Cannot set title of WindowManager: title null");
        }

        this.title = title;
    }

    /**
     *  Sets the size from the {@link WindowSettings} to the given size
     * @param width - New width from the {@link WindowSettings}
     * @param height - New height from the {@link WindowSettings}
     * @throws EngineException
     */
    void setSize(final int width, final int height) throws EngineException {
        if(width < 0) {
            throw new EngineException("Cannot set size of WindowManager: width must be at least zero!");
        }
        if(height < 0) {
            throw new EngineException("Cannot set size of WindowManager: height must be at least zero!");
        }

        this.width = width;
        this.height = height;
        maximized = false;

        if(width == 0 || height == 0) {
            maximized = true;
            this.width = 100; // Set for calculating
            this.height = 100;
        }
        updateProjectionMatrix();
    }

    /**
     *  Updates the {@link Matrix4f} used as projection matrix
     */
    private void updateProjectionMatrix() {
        float aspectRatio = (float)(width / height);
        projectionMatrix.setPerspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
    }

    /**
     *  Sets whether the window is resized or not
     * @param resized
     */
    void setResized(final boolean resized) {
        this.resized = resized;
    }

    /**
     *  Gets the width to be used upon creation of a window
     * @return Width to be used upon creation of a window
     */
    int getWidth() {
        return width;
    }

    /**
     *  Gets the height to be used upon creation of a window
     * @return Height to be used upon creation of a window
     */
    int getHeight() {
        return height;
    }

    /**
     *  Gets if the window is maximized
     * @return Whether the window is maximized
     */
    boolean isMaximized() {
        return maximized;
    }

    /**
     *  Gets whether the window is resized
     * @return Whether the window is resized
     */
    boolean isResized() {
        return resized;
    }

    /**
     *  Gets whether V-SYNC is enabled, this limits the FPS-rate to the hertz-rate from a screen
     * @return Whether V-SYNC is enabled or not
     */
    boolean vSyncEnabled() {
        return vSync;
    }

    /**
     *  Gets the {@link Matrix4f} used as projection matrix
     * @return {@link Matrix4f} which represents the projection matrix
     */
    Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
}
