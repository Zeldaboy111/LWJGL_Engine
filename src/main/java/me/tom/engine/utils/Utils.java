package me.tom.engine.utils;

import com.sun.tools.javac.Main;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Scanner;

public final class Utils {
    private Utils() { }

    /**
     *  Stores the given float-array in a {@link FloatBuffer}
     * @param data - Float-array to be stored in a {@link FloatBuffer}
     * @return {@param data} as {@link FloatBuffer}
     */
    public static FloatBuffer storeDataInFloatBuffer(final float[] data) {
        final FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data)
                .flip();

        return buffer;
    }
    /**
     *  Stores the given int-array in a {@link IntBuffer}
     * @param data - Int-array to be stored in a {@link IntBuffer}
     * @return {@param data} as {@link IntBuffer}
     */
    public static IntBuffer storeDataInIntBuffer(final int[] data) {
        final IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data)
                .flip();

        return buffer;
    }

    /**
     *  Loads a resource from the given file
     * @param file - Path towards the resource to load
     * @return Loaded resource
     * @throws UtilException
     */
    public static String loadResource(final String file) throws UtilException {
        if(file == null || file.trim().equals("")) {
            throw new UtilException("Cannot load resource: file null");
        }

        try(final InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(file)) {
            // Run checks
            if(inputStream == null) {
                throw new UtilException(String.format("Cannot load resource: InputStream of file [%s] null", file));
            }

            final Scanner scanner = new Scanner(inputStream);
            return scanner.useDelimiter("\\A").next();
        } catch (IOException e) {
            throw new UtilException(e);
        }
    }

}
