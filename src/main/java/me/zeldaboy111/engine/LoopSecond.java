package me.zeldaboy111.engine;

import static org.lwjgl.opengl.GL11C.*;

class LoopSecond {
    private final Loop loop;
    private final long startTime;
    private int frames, updates;
    private long lastProcessingStartTime, deltaFps, deltaUpdates;


    LoopSecond(final Loop loop) {
        if(loop == null) {
            throw new EngineException("Cannot instantiate LoopSecond: Loop null");
        }

        this.loop = loop;
        startTime = System.currentTimeMillis();
        lastProcessingStartTime = startTime;

        frames = 0;
        updates = 0;

        deltaFps = 0;
        deltaUpdates = 0;
    }
    LoopSecond(final LoopSecond previousSecond) {
        this(previousSecond == null ? null : previousSecond.loop);

        deltaFps = previousSecond.deltaFps;
        deltaUpdates = previousSecond.deltaUpdates;
    }

    /**
     *  Method used to process another {@link LoopSecond}
     */
    void process() {
        // Listen for events
        loop.getWindow().pollEvents();

        // Update delta-values
        updateDeltaFramesAndUpdates();
        processInput();

        if(mustUpdateBeProcessed()) {
            processUpdate();
        }
        if(mustFrameBeProcessed()) {
            processFrame();
        }

        lastProcessingStartTime = now();
    }

    /**
     *  Method used to update the amount of frames and seconds to be processed (delta frames and updates)
     */
    private void updateDeltaFramesAndUpdates() {
        deltaFps += (now() - lastProcessingStartTime) / loop.getSecondsPerFrame();
        deltaUpdates += (now() - lastProcessingStartTime) / loop.getSecondsPerUpdate();
    }

    /**
     *  Gets if a new frame should be processed
     * @return Whether a new frame should be processed
     */
    private boolean mustFrameBeProcessed() {
        return deltaFps > 0 || loop.getTargetFramesPerSecond() < 1;
    }

    /**
     *  Gets if a new update should be processed
     * @return Whether a new update should be processed
     */
    private boolean mustUpdateBeProcessed() {
        return deltaUpdates > 0;
    }

    /**
     *  Util-method to retrieve the current millisecond time
     * @return Current time in milliseconds
     */
    private long now() {
        return System.currentTimeMillis();
    }

    /**
     *  Method used to process the user input
     */
    private void processInput() {
        if (!mustFrameBeProcessed()) {
            return; // Nothing to process
        }

        //TODO appLogic.input(window, scene, now - initialTime);
    }

    /**
     *  Method used to process an update
     */
    private void processUpdate() {
        if(!mustUpdateBeProcessed()) {
            return; // Nothing to process
        }

        //TODO
        // long diffTimeMillis = now - updateTime;
        // appLogic.update(window, scene, diffTimeMillis);
        // updateTime = now;

        deltaUpdates--;
    }

    /**
     *  Method used to process a frame
     */
    private void processFrame() {
        if (!mustFrameBeProcessed()) {
            return; // Nothing to process
        }

        //TODO render.render(window, scene);
        loop.getWindow().setClearColor(1, 0, 0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        loop.getWindow().swapBuffers();
        deltaFps--;
    }



    /*
        GETTERS
     */

    /**
     *  Gets the amount of frames processed
     * @return Amount of frames processed
     */
    int getFrames() {
        return frames;
    }

    /**
     *  Gets the amount of updates processed
     * @return Amount of updates processed
     */
    int getUpdates() {
        return updates;
    }

    /**
     *  Gets the current duration of the {@link LoopSecond} in milliseconds
     * @return Current duration of the {@link LoopSecond} (in milliseconds)
     */
    long getDuration() { return now() - startTime; }
}
