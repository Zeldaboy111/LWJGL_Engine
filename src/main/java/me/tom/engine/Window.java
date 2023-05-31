package me.tom.engine;

import org.joml.Matrix4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Window {
    private final WindowSettings settings;
    private long handle;
    public Window(final WindowSettings settings) throws Window.Exception {
        // Run checks
        if(settings == null) {
            throw new Window.Exception("Cannot create Window: WindowSettings null");
        }

        // Set instance variables
        this.settings = settings;

        init();
    }

    /**
     *  Initializes the {@link Window}
     * @throws Exception
     */
    private void init() throws Exception {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!GLFW.glfwInit()) {
            throw new Exception("Initializing GLFW failed");
        }

        initWindowHints();
        handle = GLFW.glfwCreateWindow(settings.getWidth(), settings.getHeight(), settings.getTitle(),
                MemoryUtil.NULL, MemoryUtil.NULL);
        if(handle == MemoryUtil.NULL) {
            throw new Exception("Creating GLFW window failed");
        }

        initResizeHandler();

        //TODO Remove this line
        GLFW.glfwSetKeyCallback(handle, (handle, key, scanCode, action, mods) -> {
            if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(handle,true);
            }
        });

        openWindowCenteredOrMaximize();

        GLFW.glfwMakeContextCurrent(handle);

        if(settings.vSyncEnabled()) {
            GLFW.glfwSwapInterval(1);
        }

        // Set the title
        GLFW.glfwSetWindowTitle(handle, settings.getTitle());

        // Show the window
        GLFW.glfwShowWindow(handle);

        GL.createCapabilities();

        GL30.glClearColor(0f, 0f, 0f, 0f);
        GL30.glEnable(GL30.GL_DEPTH_TEST);
        GL30.glEnable(GL30.GL_STENCIL_TEST);

        // Disable rendering the back face
        GL30.glEnable(GL30.GL_CULL_FACE);
        GL30.glCullFace(GL30.GL_BACK);
    }

    /**
     *  Initializes the hints from the {@link Window}
     */
    private void initWindowHints() {
        // Set defaults
        GLFW.glfwDefaultWindowHints();

        // Init custom
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, settings.isMaximized() ? GLFW.GLFW_TRUE : GLFW.GLFW_FALSE);
    }

    /**
     *  Initializes the resize handler from the {@link Window}
     */
    private void initResizeHandler() {
        GLFW.glfwSetFramebufferSizeCallback(handle, (windowHandle, width, height) -> {
            try {
                settings.setSize(width, height);
                settings.setResized(true);
            } catch (EngineException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     *  Either opens the {@link Window} in the center of the screen or maximizes the {@link Window}
     */
    private void openWindowCenteredOrMaximize() {
        if(settings.isMaximized()) {
            // Window is maximized, make window maximized
            GLFW.glfwMaximizeWindow(handle);
        } else {
            // Window is not maximized, open the window in the center
            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            GLFW.glfwSetWindowPos(handle,
                    (vidMode.width() - settings.getWidth()) / 2,
                    (vidMode.height() - settings.getHeight()) / 2);
        }
    }

    //TODO Validate method visibility
    /**
     *  Updates the {@link Window} thereby swapping the buffers (rendering) and polling events
     */
    public void update() {
        GLFW.glfwSwapBuffers(handle);
        GLFW.glfwPollEvents();
    }

    /**
     *  Cleans up the {@link Window}
     */
    public void cleanup() {
        GLFW.glfwDestroyWindow(handle);
    }

    /**
     *  Sets the clear color from the {@link Window}
     * @param red - Red-value from the color
     * @param green - Green-value from the color
     * @param blue - Blue-value from the color
     * @param alpha - Alpha-value from the color
     */
    public void setClearColor(float red, float green, float blue, float alpha) {
        GL30.glClearColor(red, green, blue, alpha);
    }

    /**
     *  Gets if the key of the given code is being pressed
     * @param keyCode - {@link int} key to check the state of
     * @return Whether the key of the given code is being pressed
     */
    public boolean isKeyPressed(int keyCode) {
        return GLFW.glfwGetKey(handle, keyCode) == GLFW.GLFW_PRESS;
    }

    /**
     *  Gets whether the {@link Window} should close
     * @return Whether the {@link Window} should close
     */
    public boolean windowShouldClose() {
        return GLFW.glfwWindowShouldClose(handle);
    }

    /**
     *  Sets the title of the {@link Window} to the given title
     * @param title - {@link String} representing the new title of the {@link Window}
     * @throws EngineException
     */
    public void setTitle(final String title) throws EngineException {
        settings.setTitle(title);
        GLFW.glfwSetWindowTitle(handle, settings.getTitle());
    }

    /**
     *  Gets the {@link Matrix4f} used as projection matrix
     * @return {@link Matrix4f} used as projection matrix
     */
    public Matrix4f getProjectionMatrix() {
        return settings.getProjectionMatrix();
    }

    /**
     *  Gets the width from the {@link Window}
     * @return Width from the {@link Window}
     */
    public int getWidth() {
        return settings.getWidth();
    }

    /**
     *  Gets the height from the {@link Window}
     * @return Height from the {@link Window}
     */
    public int getHeight() {
        return settings.getHeight();
    }

    /**
     *  Inner class used as Exception since the only class using this
     *  exception is {@link Window}
     */
    public static class Exception extends EngineException {
        private Exception(final String message) {
            super(message);
        }
    }
}
