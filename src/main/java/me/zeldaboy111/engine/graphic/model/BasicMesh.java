package me.zeldaboy111.engine.graphic.model;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static me.zeldaboy111.engine.graphic.model.MeshUtil.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.*;

public class BasicMesh implements Mesh {
    protected final int vaoId;
    protected final List<Integer> vboIds;
    protected int nextAttributeId;
    private final int numberOfVertices;
    protected BasicMesh(final float[] vertices, final int[] indices, final boolean unbind) {
        if(!validateVertices(vertices)) {
            throw new ModelException(
                    String.format("Cannot create Mesh: vertices length no multiple of %d", VERTICES_PER_POINT));
        }
        if(indices == null || indices.length < 1) {
            throw new ModelException("Cannot create Mesh: indices length must be at least 1");
        }

        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        numberOfVertices = indices.length;
        nextAttributeId = 0;

        // Create list of VBOs and generate VBO for vertices
        vboIds = new ArrayList<>();
        vboIds.add(createVboWithContentsFloatArray(vertices));
        vboIds.add(createVboWithContentsIntArray(indices));

        // Set attributes
        setAttributePointer(3, GL_FLOAT, false, 0, 0);

        // Unbind
        if(unbind) {
            unbind();
        }

    }
    public BasicMesh(final float[] vertices, final int[] indices) {
        this(vertices, indices, true);
    }

    /**
     *  Used to set a new attribute pointer and increase the id for the next attribute pointer to be set
     * @param size - Size from the attribute
     * @param type - Type from the attribute
     * @param normalized - Whether the attribute is normalized
     * @param stride - Stride of the attribute
     * @param pointer - Where the attribute points to
     */
    protected void setAttributePointer(final int size, final int type, final boolean normalized, final int stride,
                                     final int pointer) {
        glVertexAttribPointer(nextAttributeId, size, type, normalized, stride, pointer);
        nextAttributeId++;
    }

    @Override
    public void render() {
        glDrawElements(GL_TRIANGLES, numberOfVertices, GL_UNSIGNED_INT, 0);
    }

    @Override
    public void bind() {
        glBindVertexArray(vaoId);
        enableAttributeArrays(nextAttributeId);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        disableAttributeArrays(nextAttributeId);
        glBindVertexArray(0);
    }


    @Override
    public void cleanup() {
        // Delete vertex buffer objects
        vboIds.forEach(GL30::glDeleteBuffers);

        // Unbind
        unbind();
        // Delete vertex array object
        glDeleteVertexArrays(vaoId);
    }

}
