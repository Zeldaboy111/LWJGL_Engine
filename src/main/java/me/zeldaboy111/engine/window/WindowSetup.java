package me.zeldaboy111.engine.window;

import org.lwjgl.glfw.GLFWErrorCallback;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;

final class WindowSetup {
    private WindowSetup() { }

    /**
     *  Method used to set up a new window
     * @param builder - {@link WindowBuilder} that created the {@link Window}
     * @return Handle of created window
     * @throws WindowInitializationException - Thrown if any error occurs
     */
    static long setup(final WindowBuilder builder) throws WindowInitializationException {
        if(builder == null) {
            throw new WindowInitializationException("Cannot setup Window: WindowBuilder null");
        }

        /*

            Set error callback & Init GLFW

         */
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit()) {
            throw new WindowInitializationException("Could not initialize GLFW!");
        }



        /*

            Setup

         */
        configure(builder);

        // Create window
        long handle = createWindow(builder);

        // Set callbacks
        glfwSetFramebufferSizeCallback(handle, (window, width, height) ->
                builder.getResizeHandler().handle(window, width, height));
        builder.getResizeHandler().loadSizeFromWindowUsingGLFW(handle);

        glfwMakeContextCurrent(handle);
        glfwSwapInterval(1); // V-sync enabled
        glfwShowWindow(handle); // Show window

        return handle;
    }

    /**
     *  Configures the {@link DefaultWindow} according to the given {@link WindowBuilder}
     * @param builder - {@link WindowBuilder} used to create the {@link Window}
     */
    private static void configure(final WindowBuilder builder) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, getBooleanAsGlfwValue(builder.isResizable()));
        glfwWindowHint(GLFW_DECORATED, getBooleanAsGlfwValue(builder.isDecorated()));
    }

    /**
     *  Converts the given boolean to a glfw-state (true or false)
     * @param state - State to convert into GLFW-state
     * @return GLFW-state from boolean
     */
    private static int getBooleanAsGlfwValue(final boolean state) {
        return state ? GLFW_TRUE : GLFW_FALSE;
    }

    /**
     *  Method used to create a new GLFW-window
     * @param builder - {@link WindowBuilder} used to create the new {@link Window}
     * @return Handle of the created window
     * @throws WindowInitializationException Thrown if no window could be created
     */
    private static long createWindow(final WindowBuilder builder) throws WindowInitializationException {
        // Create new window
        long handle = glfwCreateWindow(builder.getWidth(), builder.getHeight(),
                builder.getTitle(),
                builder.isFullScreen() ? glfwGetPrimaryMonitor() : NULL,
                NULL);

        // Validate window has been created
        if(handle == NULL) {
            throw new WindowInitializationException("Could not create GLFW-window");
        }

        // Return handle of created window
        return handle;
    }
}
