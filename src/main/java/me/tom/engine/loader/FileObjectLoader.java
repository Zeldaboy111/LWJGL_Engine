package me.tom.engine.loader;

import me.tom.engine.entity.EntityException;
import me.tom.engine.entity.Model;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

public class FileObjectLoader implements ObjectLoader {
    private final List<VertexArrayObject> vertexArrayObjects;
    private final List<VertexBufferObject> vertexBufferObjects;
    private final List<Integer> textures;

    public FileObjectLoader() {
        vertexArrayObjects = new ArrayList<>();
        vertexBufferObjects = new ArrayList<>();
        textures = new ArrayList<>();
    }

    @Override
    public Model loadModel(float[] vertices, float[] textureCoordinates, int[] indices) throws EntityException {
        final VertexArrayObject vertexArrayObject = createVertexArrayObject();
        storeIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, vertices);
        storeDataInAttributeList(1, 2, textureCoordinates);
        unbind();

        return new Model(vertexArrayObject.getId(), indices.length);
    }

    @Override
    public int loadTexture(final String textureSource) throws ObjectLoaderException {
        final int id = new FileTextureLoader(textureSource).loadAndProcess();

        textures.add(id); // Register id of texture
        return id;
    }

    /**
     *  Creates a new plain {@link VertexArrayObject} which is bound to GL
     * @return New {@link VertexArrayObject}
     */
    private VertexArrayObject createVertexArrayObject() {
        final VertexArrayObject vertexArrayObject = new VertexArrayObject(true);
        vertexArrayObjects.add(vertexArrayObject);

        return vertexArrayObject;
    }

    /**
     *  Stores the given data in the attribute list from a new vertex buffer object
     * @param attributeNumber - Id from the attribute to store
     * @param vertexCount - Amount of vertices
     * @param data - Float-array from the data to be stored
     */
    private void storeDataInAttributeList(final int attributeNumber, final int vertexCount, final float[] data) {
        // Make a new VertexBufferObject
        final VertexBufferObject vertexBufferObject = new VertexBufferObject();
        vertexBufferObjects.add(vertexBufferObject);

        // Store the data
        vertexBufferObject.storeDataInAttributeList(attributeNumber, vertexCount, data);
    }

    /**
     *  Stores the given indices in a new {@link VertexBufferObject}
     * @param indices - Int-array of indices to be stored
     */
    private void storeIndicesBuffer(final int[] indices) {
        final VertexBufferObject vertexBufferObject = new VertexBufferObject();
        vertexBufferObjects.add(vertexBufferObject);

        vertexBufferObject.storeIndicesBuffer(indices);
    }


    /**
     *  Method used to unbind the current vertex array
     */
    private void unbind() {
        GL30.glBindVertexArray(0);
    }

    @Override
    public void cleanup() {
        // Cleanup Vertex Array Objects
        for(VertexArrayObject vertexArrayObject : vertexArrayObjects) {
            GL30.glDeleteVertexArrays(vertexArrayObject.getId());
        }

        // Cleanup Vertex Buffer Objects
        for(VertexBufferObject vertexBufferObject : vertexBufferObjects) {
            GL30.glDeleteBuffers(vertexBufferObject.getId());
        }

        // Cleanup textures
        for(int id : textures) {
            GL30.glDeleteTextures(id);
        }
    }


}
