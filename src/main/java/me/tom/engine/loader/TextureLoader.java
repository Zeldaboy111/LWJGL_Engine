package me.tom.engine.loader;

public interface TextureLoader {
    /**
     *  Used to load the texture
     * @return Id from the loaded texture
     * @throws ObjectLoaderException Thrown if any error occurs
     */
    int loadAndProcess() throws ObjectLoaderException;
}
