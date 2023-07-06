package me.zeldaboy111.engine.util;

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
}
