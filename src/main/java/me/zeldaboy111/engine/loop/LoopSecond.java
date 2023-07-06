package me.zeldaboy111.engine.loop;

interface LoopSecond {
    /**
     *  Method used to process the {@link LoopSecond}
     */
    void process();

    /**
     *  Gets the amount of frames processed
     * @return Amount of frames processed
     */
    int getFrames();

    /**
     *  Gets the amount of updates processed
     * @return Amount of updates processed
     */
    int getUpdates();

    /**
     *  Gets the current duration of the {@link LoopSecond} in milliseconds
     * @return Current duration of the {@link LoopSecond} (in milliseconds)
     */
    long getDuration();
}
