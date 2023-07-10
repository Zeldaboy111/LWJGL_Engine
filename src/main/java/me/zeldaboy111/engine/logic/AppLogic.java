package me.zeldaboy111.engine.logic;

import me.zeldaboy111.engine.window.Window;

public interface AppLogic {
    /**
     *  Method used to initialize the {@link AppLogic}
     */
    void init();

    /**
     *  Method used to handle user input on the given {@link Window}
     * @param window - {@link Window} to handle the input from the user on
     */
    void input(final Window window);

    /**
     *  Method used to handle an update
     * @param deltaTime - Delta time from the last update
     */
    void update(double deltaTime);

    /**
     *  Method used to render the {@link Window}
     * @param window - {@link Window} to be rendered
     */
    void render(final Window window);
}
