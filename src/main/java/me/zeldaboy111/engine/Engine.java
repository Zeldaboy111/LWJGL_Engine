package me.zeldaboy111.engine;

import me.zeldaboy111.engine.window.Window;
import me.zeldaboy111.engine.window.WindowConfiguration;

public class Engine {
    private final Window window;
    private final Loop loop;
    public Engine(final WindowConfiguration windowConfiguration) throws EngineInitializationException {
        this.window = new Window(windowConfiguration);
        this.loop = new Loop(this, window,
                windowConfiguration.getFramesPerSecond(), windowConfiguration.getUpdatesPerSecond());
    }

    /**
     *  Method used to start the {@link EngineException}
     * @throws EngineInitializationException - Thrown if the {@link Engine} cannot start
     */
    public void start() throws EngineInitializationException {
        loop.start();
    }

    /**
     *  Sets the desired amount of renders/repaints per second to the given value
     * @param framesPerSecond - New value from the amount of renders/repaints per second
     */
    public void setFramesPerSecond(final int framesPerSecond) {
        loop.setTargetFramesPerSecond(framesPerSecond);
    }
    /**
     *  Sets the desired amount of updates per second to the given value
     * @param updatesPerSecond - New value from the amount of updates per second
     */
    public void setUpdatesPerSecond(final int updatesPerSecond) {
        loop.setTargetUpdatesPerSecond(updatesPerSecond);
    }

    /**
     *  Gets the amount of frames processed in the last second
     * @return Amount of frames processed in the last second
     */
    public int getFramesLastSecond() {
        return loop.getFramesLastSecond();
    }

    /**
     *  Gets the amount of updates processed in the last second
     * @return Amount of updates processed in the last second
     */
    public int getUpdatesLastSecond() {
        return loop.getUpdatesLastSecond();
    }

    /**
     *  Method used to cleanup the {@link Engine} and all subcomponents
     */
    void cleanup() {
        //TODO METHOD
        // appLogic.cleanup();
        // render.cleanup();
        // scene.cleanup();
        window.cleanup();
    }
}
