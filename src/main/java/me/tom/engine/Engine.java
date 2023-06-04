package me.tom.engine;

import me.tom.engine.render.IGameLogic;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public final class Engine {
    private final Window window;
    private final GLFWErrorCallback errorCallback;
    private final EngineLoop loop;
    private IGameLogic gameLogic;

    public Engine(final WindowSettings settings, final IGameLogic gameLogic) throws EngineException {
        this.window = new Window(settings); // Create window

        // Run checks
        if(gameLogic == null) {
            throw new EngineException("Cannot create Engine: IGameLogic null");
        }
        this.gameLogic = gameLogic;

        // Set & Create error callback
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        gameLogic.init(window);
        loop = new EngineLoop(this);
    }

    /**
     *  Starts the {@link EngineLoop}
     * @throws EngineException
     */
    public void start() throws EngineException {
        loop.start();
    }

    /**
     *  Stops the {@link EngineLoop}
     * @throws EngineException
     */
    public void stop() throws EngineException {
        loop.stop();
    }

    /**
     *  Cleans up the {@link Engine} and all components in use
     */
    void cleanup() {
        window.cleanup();
        gameLogic.cleanup();
        errorCallback.free();

        // Terminate
        GLFW.glfwTerminate();
    }

    /**
     *  Gets the {@link Window} from the {@link Engine}
     * @return {@link Window} from the {@link Engine}
     */
    public Window getWindow() {
        return window;
    }

    /**
     *  Gets the GameLogic from the {@link Engine}
     * @return {@link IGameLogic} representing the GameLogic
     */
    IGameLogic getGameLogic() { return gameLogic; }

}
