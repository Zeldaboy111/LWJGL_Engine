package me.tom.engine.render;

import me.tom.engine.EngineException;
import me.tom.engine.Window;

/**
 *  Handles the logic part of the application
 */
public interface IGameLogic {
    /**
     *  Called upon initialization of the {@link IGameLogic}
     * @param window - {@link Window} used for the {@link IGameLogic}
     * @throws EngineException
     */
    void init(final Window window) throws EngineException;

    /**
     *  Used to handle inputs received by the {@link me.tom.engine.Window}
     */
    void input();

    /**
     *  Used to handle an update to the game
     */
    void update();

    /**
     *  Used to do the rendering of the game
     * @throws RenderException
     */
    void render() throws RenderException;

    /**
     *  Used to clean up the used resources.
     */
    void cleanup();
}
