package me.zeldaboy111.engine.loop;

import me.zeldaboy111.engine.logic.AppLogic;

class DefaultLoopSecond implements LoopSecond {
    private final Loop loop;
    private final AppLogic appLogic;
    private final long startTime;
    private int frames, updates;
    private long lastProcessingStartTime, lastUpdateTime, deltaFps, deltaUpdates;


    DefaultLoopSecond(final Loop loop, final AppLogic appLogic) {
        if(loop == null) {
            throw new LoopException("Cannot instantiate LoopSecond: Loop null");
        }
        if(appLogic == null) {
            throw new LoopException("Cannot instantiate LoopSecond: AppLogic null");
        }

        this.loop = loop;
        this.appLogic = appLogic;
        startTime = System.currentTimeMillis();
        lastProcessingStartTime = startTime;
        lastUpdateTime = startTime;

        frames = 0;
        deltaFps = 0;

        updates = 0;
        deltaUpdates = 0;
    }
    DefaultLoopSecond(final DefaultLoopSecond previousSecond) {
        this(previousSecond == null ? null : previousSecond.loop,
                previousSecond == null ? null : previousSecond.appLogic);

        deltaFps = previousSecond.deltaFps;
        deltaUpdates = previousSecond.deltaUpdates;
        lastUpdateTime = previousSecond.lastUpdateTime;
    }

    @Override
    public void process() {
        // Listen for events
        loop.getWindow().pollEvents();

        // Update delta-values
        updateDeltaFramesAndUpdates();

        // Process
        processInput();
        processUpdate();
        processFrame();

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
     *  Gets if there is no frame is to be processed
     * @return Whether no frame update is to be processed
     */
    private boolean isNoFrameToBeProcessed() {
        return deltaFps < 1 && loop.getTargetFramesPerSecond() > 0;
    }

    /**
     *  Gets if there is no update to be processed
     * @return Whether no update is to be processed
     */
    private boolean isNoUpdateToBeProcessed() {
        return deltaUpdates < 1;
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
        if (isNoFrameToBeProcessed()) {
            return; // Nothing to process
        }

        appLogic.input(loop.getWindow());
    }

    /**
     *  Method used to process an update
     */
    private void processUpdate() {
        if(isNoUpdateToBeProcessed()) {
            return; // Nothing to process
        }

        appLogic.update((now() - lastUpdateTime) / 1000d);
        lastUpdateTime = now();
        deltaUpdates--;
        updates++;
    }

    /**
     *  Method used to process a frame
     */
    private void processFrame() {
        if (isNoFrameToBeProcessed()) {
            return; // Nothing to process
        }

        appLogic.render(loop.getWindow());
        loop.getWindow().swapBuffers();
        deltaFps--;
        frames++;
    }


    @Override
    public int getFrames() {
        return frames;
    }

    @Override
    public int getUpdates() {
        return updates;
    }

    @Override
    public long getDuration() { return now() - startTime; }
}
