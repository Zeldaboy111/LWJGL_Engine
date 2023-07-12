package me.zeldaboy111.engine.util;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class BufferUtil {
    private BufferUtil() { }

    /**
     *  Used to convert the incoming float-array into a {@link FloatBuffer}
     * @param input - Float-array to be converted to a {@link FloatBuffer}
     * @return {@link FloatBuffer} containing the information from {@param input}
     */
    public static FloatBuffer toFloatBuffer(final float[] input) {
        if(input == null) {
            throw new UtilException("Cannot convert to FloatBuffer: input null");
        }

        // Create FloatBuffer & Store data
        final FloatBuffer result = MemoryUtil.memAllocFloat(input.length);
        result.put(input)
                .flip(); // Reset FloatBuffer position to 0

        return result; // Return result
    }
    /**
     *  Used to convert the incoming float-array into a {@link IntBuffer}
     * @param input - Int-array to be converted to a {@link IntBuffer}
     * @return {@link IntBuffer} containing the information from {@param input}
     */
    public static IntBuffer toIntBuffer(final int[] input) {
        if(input == null) {
            throw new UtilException("Cannot convert to IntBuffer: input null");
        }

        // Create IntBuffer & Store data
        final IntBuffer result = MemoryUtil.memAllocInt(input.length);
        result.put(input)
                .flip(); // Reset FloatBuffer position to 0

        return result; // Return result
    }

}
