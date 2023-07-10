package me.zeldaboy111.engine.window.resize;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DefaultWindowResizeHandler implements WindowResizeHandler {
    private int width, height, widthBeforeFullScreen, heightBeforeFullScreen, decorated;
    private boolean fullScreenEnabled, centerOnMonitor;
    public DefaultWindowResizeHandler() {
        width = -1;
        height = -1;
        widthBeforeFullScreen = -1;
        heightBeforeFullScreen = -1;
        fullScreenEnabled = false;
    }

    @Override
    public void handle(final long window, final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void centerMonitor(final long window) {
        // Retrieve monitor
        final long monitor = glfwGetPrimaryMonitor();
        if(monitor == NULL) {
            throw new ResizeHandlerException("Cannot center Window: monitor not found");
        }

        final int[] position = getPositionToCenterWindowAt();

        // Center position
        glfwSetWindowPos(
                window,
                position[0], position[1]
        );
    }

    /**
     *  Gets the position to center the {@link me.zeldaboy111.engine.window.Window} at on the primary monitor
     * @return Position where to place the {@link me.zeldaboy111.engine.window.Window} to be centered on the primary
     * monitor
     */
    private int[] getPositionToCenterWindowAt() {
        final int[] size = getPrimaryMonitorSize();
        return new int[] { (size[0] - widthBeforeFullScreen) / 2,
                            (size[1] - heightBeforeFullScreen) / 2 };
    }
    /**
     *  Gets the size from the primary monitor
     * @return Size of the primary monitor
     */
    private int[] getPrimaryMonitorSize() {
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        if(vidMode == null) {
            return new int[] { 1920, 1080 }; // None found, return default values
        }
        return new int[] { vidMode.width(), vidMode.height() };
    }

    @Override
    public void setCenterOnMonitorAfterToggleFullscreen(boolean centerOnMonitor) {
        this.centerOnMonitor = centerOnMonitor;
    }

    @Override
    public void setFullScreen(long window, boolean fullScreen) {
        if(fullScreenEnabled == fullScreen) {
            return; // State matches current state
        }
        fullScreenEnabled = fullScreen;

        // Retrieve position and size
        int[] size = getSetFullScreenSize();
        int[] position = getSetFullScreenPosition();

        // Set window size and position
        glfwSetWindowMonitor(window,
                fullScreen ? glfwGetPrimaryMonitor() : NULL,
                position[0], position[1],
                size[0], size[1],
                GLFW_DONT_CARE);

        // Set window hints
        if(!fullScreen) {
            glfwWindowHint(GLFW_DECORATED, decorated);
        }
    }

    /**
     *  Gets the size from the {@link me.zeldaboy111.engine.window.Window} based off the current mode
     * @return Size from the {@link me.zeldaboy111.engine.window.Window} based off the current mode
     */
    private int[] getSetFullScreenSize() {
        if(!fullScreenEnabled) {
            return new int[] { widthBeforeFullScreen, heightBeforeFullScreen };
        }

        return getPrimaryMonitorSize();
    }

    /**
     *  Gets the position to set the {@link me.zeldaboy111.engine.window.Window} to (given the current mode)
     * @return Position to set the {@link me.zeldaboy111.engine.window.Window} to (given the current mode)
     */
    private int[] getSetFullScreenPosition() {
        if(fullScreenEnabled || !centerOnMonitor) {
            return new int[] { 0, 0 };
        }

        return getPositionToCenterWindowAt();
    }

    @Override
    public boolean isFullScreen() {
        return fullScreenEnabled;
    }

    @Override
    public void loadSizeFromWindowUsingGLFW(long window) {
        try(MemoryStack stack = stackPush()) {
            final IntBuffer width = stack.mallocInt(1);
            final IntBuffer height = stack.mallocInt(1);

            // Store the size passed in 'glfwCreateWindow' in the IntBuffers
            glfwGetWindowSize(window, width, height);

            this.width = width.get();
            this.height = height.get();
            updateFullScreenEnabledAndSizeBeforeFullScreen(window);
        }
    }

    /**
     *  Used to update whether fullscreen is enabled and the size before when fullscreen-mode has been enabled
     * @param window - Handle from the window to retrieve the fullscreen-status from
     */
    private void updateFullScreenEnabledAndSizeBeforeFullScreen(long window) {
        if(glfwGetWindowMonitor(window) != NULL) {
            fullScreenEnabled = true;
            return;
        }

        fullScreenEnabled = false;
        decorated = glfwGetWindowAttrib(window, GLFW_DECORATED);
        widthBeforeFullScreen = width;
        heightBeforeFullScreen = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
