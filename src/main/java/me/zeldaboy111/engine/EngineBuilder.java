package me.zeldaboy111.engine;

import me.zeldaboy111.engine.logic.AppLogic;
import me.zeldaboy111.engine.logic.DefaultAppLogic;
import me.zeldaboy111.engine.window.Window;
import me.zeldaboy111.engine.window.WindowBuilder;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public final class EngineBuilder {
    private int framesPerSecond, updatesPerSecond;
    private AppLogic appLogic;
    public EngineBuilder() {
        this.framesPerSecond = 60;
        updatesPerSecond = 20;
        appLogic = new DefaultAppLogic();
    }

    /**
     *  Used to build the current configuration into an {@link Engine}
     *
     * @param windowBuilder - {@link WindowBuilder} to create an {@link Engine} for
     * @return Created {@link Engine}
     * @throws EngineInitializationException - Thrown if any issues occur during the building of the {@link Engine}
     */
    public synchronized Engine build(final WindowBuilder windowBuilder) throws EngineInitializationException {
        if (windowBuilder == null) {
            throw new EngineInitializationException("Cannot build Engine: WindowBuilder null");
        }

        return new DefaultEngine(windowBuilder, this);
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

    /**
     *  Sets the {@link AppLogic} to be used to the given {@link AppLogic}
     * @param appLogic - New {@link AppLogic} to be used
     */
    public EngineBuilder setAppLogic(final AppLogic appLogic) {
        if(appLogic != null) {
            this.appLogic = appLogic;
        }

        return this;
    }


    /*
        GETTERS
     */
    public int getFramesPerSecond() { return framesPerSecond; }
    public int getUpdatesPerSecond() { return updatesPerSecond; }
    public AppLogic getAppLogic() { return appLogic; }
}
