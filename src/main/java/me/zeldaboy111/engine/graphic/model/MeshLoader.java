package me.zeldaboy111.engine.graphic.model;

import me.zeldaboy111.engine.util.BufferUtil;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memFree;

public final class MeshLoader {
    private static final List<Integer> vaoIdList = new ArrayList<>();
    private static final List<Integer> vboIdList = new ArrayList<>();
    private MeshLoader() {} // Prevent instantiation

    /**
     *  Method used to load a new {@link Mesh} from the given parameters
     * @param vertices - Vertices from the {@link Mesh} to load
     * @return Created {@link Mesh}
     */
    public static Mesh loadMesh(final float[] vertices) {
        final int vertexArrayObjectId = generateAndBindVertexArrayObject();
        final int vertexBufferObjectId = generateVertexBufferObjectDataFloatArray(vertices);

        // TODO: Auto-increment index
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        // Unbind
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        return new DefaultMesh(vertexArrayObjectId, vertices.length / 3);
    }

    /**
     *  Method used to generate and bind an ID for a vertex object array
     * @return ID from generated vertex object array
     */
    private static int generateAndBindVertexArrayObject() {
        // Generate id and bind
        final int id = glGenVertexArrays();
        glBindVertexArray(id);

        vaoIdList.add(id);
        return id;
    }

    /**
     *  Method used to create a new vertex buffer object and bind the data towards it
     * @param data - Float-array of data to be bound towards the vertex buffer object
     */
    private static int generateVertexBufferObjectDataFloatArray(final float[] data) {
        if(data == null) {
            throw new ModelException("Cannot create Vertex Buffer Object: data null");
        }

        final FloatBuffer dataBuffer = BufferUtil.toFloatBuffer(data);
        final int vertexBufferObjectId = generateAndBindVertexBufferObject();
        glBufferData(GL_ARRAY_BUFFER, dataBuffer, GL_STATIC_DRAW);
        memFree(dataBuffer);

        return vertexBufferObjectId;
    }

    /**
     *  Method used to create a new vertex buffer object and bind it
     * @return ID from created vertex buffer object
     */
    private static int generateAndBindVertexBufferObject() {
        final int id = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, id);

        vboIdList.add(id);
        return id;
    }

    /**
     *  Method used to clean up the {@link MeshLoader}
     */
    public static void cleanup() {
        vboIdList.forEach(GL30::glDeleteBuffers);
        vaoIdList.forEach(GL30::glDeleteVertexArrays);
    }

}
