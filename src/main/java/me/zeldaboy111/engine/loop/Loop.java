package me.zeldaboy111.engine.loop;

import me.zeldaboy111.engine.window.Window;

public interface Loop {
    /**
     *  Sets the desired amount of renders/repaints per second to the given value
     * @param framesPerSecond - New value from the amount of renders/repaints per second
     */
    void setTargetFramesPerSecond(final int framesPerSecond);

    /**
     *  Sets the desired amount of updates to the given value
     * @param updatesPerSecond - New value from the amount of updates per second
     */
    void setTargetUpdatesPerSecond(final int updatesPerSecond);

    /**
     *  Method used to start the {@link Loop}
     */
    void start();

    /**
     *  Used to stop the {@link Loop}
     */
    void stop();

    /**
     *  Gets the amount of frames processed the last second
     * @return Amount of frames processed the last second
     */
    int getFramesLastSecond();

    /**
     *  Gets the amount of updates processed the last second
     * @return Amount of updates processed the last second
     */
    int getUpdatesLastSecond();

    /**
     *  Gets the {@link Window} used by the {@link Loop}
     * @return {@link Window} used by the {@link Loop}
     */
    Window getWindow();

    /**
     * Gets the FPS (Frames Per Second) to be reached
     * @return Target FPS (Frames Per Second)
     */
    int getTargetFramesPerSecond();

    /**
     * Gets the UPS (Updates Per Second) to be reached
     * @return Target UPS (Updates Per Second)
     */
    int getTargetUpdatesPerSecond();

    /**
     * Gets the amount of seconds between each render
     * @return Amount of seconds between each render
     */
    double getSecondsPerFrame();

    /**
     * Gets the amount of seconds between each update
     * @return Amount of seconds between each update
     */
    double getSecondsPerUpdate();

    /**
     *  Method used to clean up the {@link Loop}
     */
    void cleanup();
}
