package me.zeldaboy111.engine.graphic.model;

import me.zeldaboy111.engine.util.BufferUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.system.MemoryUtil.memFree;

final class MeshUtil {
    static final int MIN_VERTICES = 1, VERTICES_PER_POINT = 3;
    private MeshUtil() { } // Prevent instantiation

    /**
     *  Validates whether the given vertices have a valid length.
     *   This is the case when:
     *   - {@link #MIN_VERTICES} is more than or equal to the length of the given vertices
     *   - the length of the given vertices is a multiple of {@link #VERTICES_PER_POINT}
     * @param vertices - Float-array containing every vertex
     * @return Whether the given array of vertices is valid
     */
    static boolean validateVertices(final float[] vertices) {
        if(vertices == null) {
            return false;
        }

        final int length = vertices.length;
        return length >= MIN_VERTICES && length % VERTICES_PER_POINT == 0;
    }

    /**
     *  Method used to enable every vertex attribute array below {@param maxAttributeArrayId}
     * @param maxAttributeArrayId - Upper bound from the attribute array to be enabled (exclusive)
     */
    static void enableAttributeArrays(final int maxAttributeArrayId) {
        if(maxAttributeArrayId < 1) {
            return; // Nothing to enable
        }

        for(int arrayId = 0; arrayId < maxAttributeArrayId; arrayId++) {
            glEnableVertexAttribArray(arrayId);
        }
    }

    /**
     *  Method used to disable every vertex attribute array below {@param maxAttributeArrayId}
     * @param maxAttributeArrayId - Upper bound from the attribute array to be disabled (exclusive)
     */
    static void disableAttributeArrays(final int maxAttributeArrayId) {
        if(maxAttributeArrayId < 1) {
            return; // Nothing to disable
        }

        for(int arrayId = 0; arrayId < maxAttributeArrayId; arrayId++) {
            glDisableVertexAttribArray(arrayId);
        }
    }

    /**
     *  Used to create a new vertex buffer object containing the given {@param contents}
     * @param contents - Float-contents from the vertex buffer object to be created
     * @return ID from the created vertex buffer object
     */
    static int createVboWithContentsFloatArray(final float[] contents) {
        if(contents == null) {
            throw new ModelException("Cannot create Vertex Buffer Object: contents null");
        }

        final FloatBuffer floatBuffer = BufferUtil.toFloatBuffer(contents);
        final int vboId = generateAndBindVertexBufferObject(GL_ARRAY_BUFFER);
        glBufferData(GL_ARRAY_BUFFER, floatBuffer, GL_STATIC_DRAW);
        memFree(floatBuffer);

        return vboId;
    }
    /**
     *  Used to create a new vertex buffer object containing the given {@param contents}
     * @param contents - Int-contents from the vertex buffer object to be created
     * @return ID from the created vertex buffer object
     */
    static int createVboWithContentsIntArray(final int[] contents) {
        if(contents == null) {
            throw new ModelException("Cannot create Vertex Buffer Object: contents null");
        }

        final IntBuffer intBuffer = BufferUtil.toIntBuffer(contents);
        final int vboId = generateAndBindVertexBufferObject(GL_ELEMENT_ARRAY_BUFFER);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL_STATIC_DRAW);
        memFree(intBuffer);

        return vboId;
    }

    /**
     *  Method used to create a new vertex buffer object and bind it towards OpenGL
     * @param target - Target to bind to
     * @return ID from the created vertex buffer object
     */
    static int generateAndBindVertexBufferObject(int target) {
        final int id = glGenBuffers();
        glBindBuffer(target, id);

        return id;
    }
}
