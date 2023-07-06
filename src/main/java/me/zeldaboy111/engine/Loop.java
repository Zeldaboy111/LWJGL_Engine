package me.zeldaboy111.engine;

import me.zeldaboy111.engine.window.Window;
import org.lwjgl.opengl.GL;

class Loop {
    private static final int MILLISECOND = 1000000;
    private final Engine engine;
    private final Window window;
    private boolean running;
    private int targetFramesPerSecond, targetUpdatesPerSecond;
    private double secondsPerFrame, secondsPerUpdate;
    private int framesLastSecond, updatesLastSecond;

    Loop(final Engine engine, final Window window, final int framesPerSecond, final int updatesPerSecond) throws EngineInitializationException {
        if(engine == null) {
            throw new EngineInitializationException("Cannot create Loop: Engine null");
        }
        if(window == null) {
            throw new EngineInitializationException("Cannot create Loop: Window null");
        }

        // Set instance variables
        this.engine = engine;
        this.window = window;
        this.running = false;

        // Try to set the frames and updates per second
        try {
            setTargetFramesPerSecond(framesPerSecond);
            setTargetUpdatesPerSecond(updatesPerSecond);
        } catch(EngineException e) {
            throw new EngineInitializationException(e);
        }


        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        window.setClearColor(1f, 0f, 0f);
    }

    /**
     *  Sets the desired amount of renders/repaints per second to the given value
     * @param framesPerSecond - New value from the amount of renders/repaints per second
     */
    void setTargetFramesPerSecond(final int framesPerSecond) {
        if(framesPerSecond < 0) {
            throw new EngineException("Cannot set frames per second: value must be positive!");
        }

        targetFramesPerSecond = framesPerSecond;
        updateSecondPerFrame();
    }

    /**
     *  Updates teh amount of time between every render/repaint according to the target updates per second
     *  (value in seconds)
     */
    private void updateSecondPerFrame() {
        secondsPerFrame = targetFramesPerSecond > 0 ? 1d / targetFramesPerSecond : 0;
    }

    /**
     *  Sets the desired amount of updates to the given value
     * @param updatesPerSecond - New value from the amount of updates per second
     */
    void setTargetUpdatesPerSecond(final int updatesPerSecond) {
        if(updatesPerSecond < 0) {
            throw new EngineException("Cannot set updates per second: value must be positive!");
        }

        targetUpdatesPerSecond = updatesPerSecond;
        updateSecondPerUpdate();
    }

    /**
     *  Updates the amount of time between every update according to the target updates per second (value is in seconds)
     */
    private void updateSecondPerUpdate() {
        secondsPerUpdate = 1d / targetUpdatesPerSecond;
    }

    /**
     *  Method used to start the {@link Loop}
     * @throws EngineInitializationException Thrown when attempted to start whilst already active
     */
    void start() throws EngineInitializationException {
        if(running) {
            throw new EngineInitializationException("Cannot start Loop: already running");
        }

        this.running = true;
        LoopSecond loopSecond = new LoopSecond(this);

        while(running && !window.shouldClose()) {
            loopSecond.process();

            if(loopSecond.getDuration() >= MILLISECOND) {
                // Update frames and updates processed in the last second
                this.framesLastSecond = loopSecond.getFrames();
                this.updatesLastSecond = loopSecond.getUpdates();

                // Set LoopSecond to new instance for the next second
                loopSecond = new LoopSecond(loopSecond);
            }
        }

        running = true;
        stop();

        engine.cleanup();
    }

    /**
     *  Used to stop the {@link Loop}
     */
    public void stop() {
        if(!running) {
            throw new EngineException("Cannot stop Loop: not running");
        }

        running = false;
    }


    /*
        GETTERS
     */
    int getTargetFramesPerSecond() { return targetFramesPerSecond; }
    double getSecondsPerFrame() { return secondsPerFrame; }
    double getSecondsPerUpdate() { return secondsPerUpdate; }
    Window getWindow() { return window; }

    public int getFramesLastSecond() { return framesLastSecond; }
    public int getUpdatesLastSecond() { return updatesLastSecond; }
}
