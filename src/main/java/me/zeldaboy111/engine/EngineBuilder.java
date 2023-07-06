package me.zeldaboy111.engine;

import me.zeldaboy111.engine.window.Window;

public final class EngineBuilder {
    private int framesPerSecond, updatesPerSecond;
    public EngineBuilder() {
        this.framesPerSecond = 60;
        updatesPerSecond = 20;
    }

    /**
     *  Used to build the current configuration into an {@link Engine}
     *
     * @param window - {@link Window} to create an {@link Engine} for
     * @return Created {@link Engine}
     * @throws EngineInitializationException - Thrown if any issues occur during the building of the {@link Engine}
     */
    public Engine build(final Window window) throws EngineInitializationException {
        if (window == null) {
            throw new EngineInitializationException("Cannot build Engine: Window null");
        }

        return new DefaultEngine(window, this);
    }


    /*
        SETTERS
     */

    /**
     *  Sets the amount of frames displayed every second
     * @param framesPerSecond - New amount of frames to be displayed every second
     */
    public EngineBuilder setFramesPerSecond(final int framesPerSecond) {
        if(framesPerSecond > -1) {
            this.framesPerSecond = framesPerSecond;
        }

        return this;
    }

    /**
     *  Sets the amount of updates processed per second
     * @param updatesPerSecond - New amount of updates to be processed every second
     */
    public EngineBuilder setUpdatesPerSecond(final int updatesPerSecond) {
        if(updatesPerSecond > -1) {
            this.updatesPerSecond = updatesPerSecond;
        }

        return this;
    }


    /*
        GETTERS
     */
    public int getFramesPerSecond() { return framesPerSecond; }
    public int getUpdatesPerSecond() { return updatesPerSecond; }
}
