package me.tom.engine.loader;

import me.tom.engine.entity.EntityException;
import me.tom.engine.entity.Model;

public interface ObjectLoader {

    /**
     *  Loads a {@link Model} from the given parameters
     * @param vertices - Vertices from the {@link Model} to load
     * @param textureCoordinates - Float-array containing the coordinates from the texture to be applied
     * @param indices-  List of indices from the {@link Model} to load
     * @return Loaded {@link Model}
     * @throws EntityException
     */
    Model loadModel(float[] vertices, float[] textureCoordinates, int[] indices) throws EntityException;

    /**
     *  Loads a new texture from the given source
     * @param textureSource - Source to create a texture of
     * @return Id from created texture
     * @throws ObjectLoaderException Thrown if any error occurs
     */
    int loadTexture(final String textureSource) throws ObjectLoaderException;

    /**
     *  Cleans up the {@link ObjectLoader}
     */
    void cleanup();
}
