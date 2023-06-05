package me.tom.engine.loader;

import me.tom.engine.entity.EntityException;
import me.tom.engine.entity.Model;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

public class ObjectLoader {
    private List<VertexArrayObject> vertexArrayObjects;
    private List<VertexBufferObject> vertexBufferObjects;
    public ObjectLoader() {
        vertexArrayObjects = new ArrayList<>();
        vertexBufferObjects = new ArrayList<>();
    }

    /**
     *  Loads a {@link Model} from the given parameters
     * @param vertices - Vertices from the {@link Model} to load
     * @param indices-  List of indices from the {@link Model} to load
     * @return Loaded {@link Model}
     * @throws EntityException
     */
    public Model loadModel(float[] vertices, int[] indices) throws EntityException {
        final VertexArrayObject vertexArrayObject = createVertexArrayObject();
        storeIndicesBuffer(indices);
        storeDataInAttributeList(0, 3, vertices);
        unbind();
        return new Model(vertexArrayObject.getId(), vertices.length / 3);
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


    //TODO COMMENTS
    private void unbind() {

    }

    /**
     *  Cleans up the {@link ObjectLoader}
     */
    public void cleanup() {
        for(VertexArrayObject vertexArrayObject : vertexArrayObjects) {
            GL30.glDeleteVertexArrays(vertexArrayObject.getId());
        }
        for(VertexBufferObject vertexBufferObject : vertexBufferObjects) {
            GL30.glDeleteBuffers(vertexBufferObject.getId());
        }
    }


}
