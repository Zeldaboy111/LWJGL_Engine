package me.zeldaboy111.engine;

import me.zeldaboy111.engine.window.Window;

public interface Engine {
    /**
     *  Method used to start the {@link EngineException}
     * @throws EngineInitializationException - Thrown if the {@link DefaultEngine} cannot start
     */
    void start() throws EngineInitializationException;

    /**
     *  Sets the desired amount of renders/repaints per second to the given value
     * @param framesPerSecond - New value from the amount of renders/repaints per second
     */
    void setFramesPerSecond(final int framesPerSecond);

    /**
     *  Sets the desired amount of updates per second to the given value
     * @param updatesPerSecond - New value from the amount of updates per second
     */
    void setUpdatesPerSecond(final int updatesPerSecond);

    /**
     *  Gets the amount of frames processed in the last second
     * @return Amount of frames processed in the last second
     */
    int getFramesLastSecond();

    /**
     *  Gets the amount of updates processed in the last second
     * @return Amount of updates processed in the last second
     */
    int getUpdatesLastSecond();

    /**
     *  Method used to get the {@link Window} used by the {@link Engine}
     * @return {@link Window} used by the {@link Engine}
     */
    Window getWindow();

    /**
     *  Method used to cleanup the {@link DefaultEngine} and all subcomponents
     */
    void cleanup();

    /**
     *  Gets the version from the {@link Engine} currently being used
     * @return
     */
    String getVersion();
}
