package me.tom.engine;

import me.tom.engine.render.RenderException;

final class EngineLoop {
    private static final long NANOSECOND = 1000000000L;
    private static final float FRAMERATE = 1000;

    private static float frameTime = 1f / FRAMERATE;

    private final Engine engine;
    private boolean running;
    private int fps;
    EngineLoop(final Engine engine) throws EngineException {
        // Run checks
        if(engine == null) {
            throw new EngineException("Cannot create EngineLoop: Engine null");
        }

        // Set instance variables
        this.engine = engine;
        running = false;

    }

    /**
     *  Starts the {@link EngineLoop} when inactive
     * @throws EngineException
     */
    public void start() throws EngineException {
        if(running) {
            throw new EngineException("Cannot start EngineLoop: already running");
        }

        running = true;
        run();
    }
    private void run() throws EngineException {
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while(running) {
            boolean render = false;
            final long startTime = System.nanoTime();
            final long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)NANOSECOND;
            frameCounter += passedTime;

            input();

            while(unprocessedTime > frameTime) {
                render = true;
                unprocessedTime -= frameTime;

                if(engine.getWindow().windowShouldClose()) {
                    if(running) {
                        stop();
                    }
                }

                // Push the FPS upon the passing of a second
                if(frameCounter >= NANOSECOND) {
                    fps = frames;
                    frames = 0;
                }
            }

            // Update & Render the Window
            if(render) {
                update();
                render();
                frames++;
            }
        }
    }

    /**
     *  Checks for any user inputs
     */
    private void input() {
        engine.getGameLogic().input();
    }

    /**
     *  Renders the screen
     */
    private void render() {
        try {
            engine.getGameLogic().render();
        } catch (RenderException e) {
            throw new RuntimeException(e);
        }
        engine.getWindow().update();
    }

    /**
     *  Cleans up the {@link Engine}
     */
    private void cleanup() {
        engine.cleanup();
    }

    /**
     *  Updates the {@link Engine}
     */
    private void update() {
        engine.getGameLogic().update();

    }



    /**
     *  Stops the {@link EngineLoop} when active
     * @throws EngineException
     */
    public void stop() throws EngineException {
        // Run checks
        if(!running) {
            throw new EngineException("Cannot stop EngineLoop: not running");
        }

        running = false;
    }
}
