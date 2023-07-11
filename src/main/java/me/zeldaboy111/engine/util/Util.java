package me.zeldaboy111.engine.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Util {
    private Util() { }

    /**
     *  Gets whether the given {@link String} is either null or empty
     * @param input - {@link String} to check
     * @return Whether the given {@link String} is either null or empty
     */
    public static boolean isNullOrEmpty(final String input) {
        return input == null || input.trim().equals("");
    }

    /**
     *  Method used to read the contents of the file of given path
     * @param filePath - Path towards the file
     * @return Contents from the file at the given path
     */
    public static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException excp) {
            throw new UtilException("Error reading file [" + filePath + "]", excp);
        }
    }
}
