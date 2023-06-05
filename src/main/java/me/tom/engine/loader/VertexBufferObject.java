package me.tom.engine.loader;

import me.tom.engine.utils.Utils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

final class VertexBufferObject {
    private final int id;
    VertexBufferObject() {
        id = GL20.glGenBuffers();
    }

    /**
     *  Gets the id from the {@link VertexBufferObject}
     * @return Id from the {@link VertexBufferObject}
     */
    int getId() { return id; }

    void storeDataInAttributeList(final int attributeNumber, final int vertexCount, final float[] data) {
        // Convert data to FloatBuffer since float-array is non-accepted
        final FloatBuffer buffer = Utils.storeDataInFloatBuffer(data);

        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, id);
        GL20.glBufferData(GL20.GL_ARRAY_BUFFER, buffer, GL20.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, vertexCount, GL20.GL_FLOAT, false, 0, 0);
        GL20.glBindBuffer(GL20.GL_ARRAY_BUFFER, 0);
    }

    /**
     *  Stores the given indices to the {@link VertexBufferObject}
     * @param indices - Indices to be stored
     */
    void storeIndicesBuffer(final int[] indices) {
        // Convert indices to IntBuffer
        final IntBuffer buffer = Utils.storeDataInIntBuffer(indices);

        GL20.glBindBuffer(GL20.GL_ELEMENT_ARRAY_BUFFER, id);
        GL20.glBufferData(GL20.GL_ELEMENT_ARRAY_BUFFER, buffer, GL20.GL_STATIC_DRAW);

    }
}
