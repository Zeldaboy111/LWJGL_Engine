package me.tom.engine;

import org.joml.Matrix4f;

public class WindowManager {
    private static float FOV;
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 1000f;

    private final String title;
    private int width, height;
    private boolean resize, vSync;
    private final Matrix4f projectionMatrix;

    public WindowManager(final String title, final int width, final int height, final boolean vSync) throws EngineException {
        if(title == null || title.trim().equals("")) {
            throw new EngineException("Cannot create WindowManager: title null");
        }
        if(width < 1) {
            throw new EngineException("Cannot create WindowManager: width must be at least one!");
        }
        if(height < 1) {
            throw new EngineException("Cannot create WindowManager: height must be at least one!");
        }

        this.title = title;
        setFOV(60);
        this.width = width;
        this.height = height;
        this.vSync = vSync;
        this.projectionMatrix = new Matrix4f();
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
    public final float getFOV() { return FOV; }


}
