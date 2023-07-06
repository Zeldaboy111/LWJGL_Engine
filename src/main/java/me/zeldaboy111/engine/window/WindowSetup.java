package me.zeldaboy111.engine.window;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.system.MemoryStack.stackPush;

final class WindowSetup {
    private WindowSetup() { }

    /**
     *  Method used to setup a new window
     * @param builder - {@link WindowBuilder} that created the {@link Window}
     * @return Handle of created window
     * @throws WindowInitializationException - Thrown if any error occurs
     */
    static long setup(final WindowBuilder builder) throws WindowInitializationException {
        if(builder == null) {
            throw new WindowInitializationException("Cannot setup Window: WindowBuilder null");
        }

        /*

            Set callback & init GLFW

         */
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit()) {
            throw new WindowInitializationException("Could not initialize GLFW!");
        }

        /*

            Setup

         */
        configure(builder);

        long handle = createWindow(builder);
        centerWindow(handle);

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
                builder.getTitle(), NULL, NULL);

        // Validate window has been created
        if(handle == NULL) {
            throw new WindowInitializationException("Could not create GLFW-window");
        }

        // Return handle of created window
        return handle;
    }

    /**
     *  Centers the window on the primary monitor form the user
     * @param handle - Handle from the window to center
     */
    private static void centerWindow(final long handle) {
        try(MemoryStack stack = stackPush()) {
            final IntBuffer width = stack.mallocInt(1);
            final IntBuffer height = stack.mallocInt(1);

            // Store the size passed in 'glfwCreateWindow' in the IntBuffers
            glfwGetWindowSize(handle, width, height);

            // Retrieve resolution of primary monitor
            GLFWVidMode primaryMonitorVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    handle,
                    (primaryMonitorVidMode.width() - width.get(0)) / 2,
                    (primaryMonitorVidMode.height() - height.get(0)) / 2
            );
        }
    }
}
