package me.zeldaboy111.engine.loop;

import me.zeldaboy111.engine.Engine;
import me.zeldaboy111.engine.EngineBuilder;
import me.zeldaboy111.engine.EngineException;
import me.zeldaboy111.engine.logic.AppLogic;
import me.zeldaboy111.engine.util.Constants;
import me.zeldaboy111.engine.window.Window;
import org.lwjgl.opengl.GL;

public class DefaultLoop implements Loop {
    private final Engine engine;
    private final AppLogic appLogic;
    private boolean running;
    private int targetFramesPerSecond, targetUpdatesPerSecond;
    private double secondsPerFrame, secondsPerUpdate;
    private int framesLastSecond, updatesLastSecond;

    public DefaultLoop(final Engine engine, final EngineBuilder builder) throws LoopInitializationException {
        if(engine == null) {
            throw new LoopInitializationException("Cannot create Loop: Engine null");
        }
        if(builder == null) {
            throw new LoopInitializationException("Cannot create Loop: EngineBuilder null");
        }

        // Set instance variables
        this.engine = engine;
        this.appLogic = builder.getAppLogic();
        this.running = false;

        // Try to set the frames and updates per second
        try {
            setTargetFramesPerSecond(builder.getFramesPerSecond());
            setTargetUpdatesPerSecond(builder.getUpdatesPerSecond());
        } catch(EngineException e) {
            throw new LoopInitializationException(e);
        }


        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        engine.getWindow().setClearColor(0f, 0f, 0f);
    }

    @Override
    public void setTargetFramesPerSecond(final int framesPerSecond) {
        if(framesPerSecond < 0) {
            throw new LoopException("Cannot set frames per second: value must be positive!");
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

    @Override
    public void setTargetUpdatesPerSecond(final int updatesPerSecond) {
        if(updatesPerSecond < 0) {
            throw new LoopException("Cannot set updates per second: value must be positive!");
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

    @Override
    public void start() {
        if(running) {
            throw new LoopException("Cannot start Loop: already running");
        }

        running = true;

        appLogic.init();
        DefaultLoopSecond loopSecond = new DefaultLoopSecond(this, appLogic);

        while(running && !engine.getWindow().shouldClose()) {
            loopSecond.process();

            if(loopSecond.getDuration() >= Constants.MILLISECOND) {
                // Update frames and updates processed in the last second
                framesLastSecond = loopSecond.getFrames();
                updatesLastSecond = loopSecond.getUpdates();

                // Set LoopSecond to new instance for the next second
                loopSecond = new DefaultLoopSecond(loopSecond);
            }

            sleep();
        }

        // Stop & Cleanup
        stop();

        engine.cleanup();
    }

    /**
     *  Lets the {@link DefaultLoop} sleep for one millisecond
     */
    private void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new LoopException(e);
        }
    }

    @Override
    public void stop() {
        if(!running) {
            return;
        }

        running = false;
    }


    /*
        GETTERS
     */
    @Override
    public int getFramesLastSecond() { return framesLastSecond; }
    @Override
    public int getUpdatesLastSecond() { return updatesLastSecond; }

    @Override
    public Window getWindow() {
        return engine.getWindow();
    }

    @Override
    public int getTargetFramesPerSecond() { return targetFramesPerSecond; }
    @Override
    public int getTargetUpdatesPerSecond() { return targetUpdatesPerSecond; }
    @Override
    public double getSecondsPerFrame() { return secondsPerFrame; }
    @Override
    public double getSecondsPerUpdate() { return secondsPerUpdate; }

    @Override
    public void cleanup() {
        appLogic.cleanup();

    }
}
