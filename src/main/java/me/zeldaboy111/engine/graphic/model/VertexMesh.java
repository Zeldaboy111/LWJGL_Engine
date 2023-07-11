package me.zeldaboy111.engine.graphic.model;

import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

import static me.zeldaboy111.engine.graphic.model.MeshUtil.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL30.*;

public class VertexMesh implements Mesh {
    protected final int vaoId;
    protected final List<Integer> vboIds;
    protected int nextAttributeId;
    private final int numberOfVertices;
    public VertexMesh(final float[] vertices) {
        if(!validateVertices(vertices)) {
            throw new ModelException(
                    String.format("Cannot create Mesh: vertices length no multiple of %d", VERTICES_PER_POINT));
        }

        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        numberOfVertices = vertices.length / VERTICES_PER_POINT;
        nextAttributeId = 0;

        // Create list of VBOs and generate VBO for vertices
        vboIds = new ArrayList<>();
        vboIds.add(createVboWithContentsFloatArray(vertices));

        // Set attributes
        setAttributePointer(3, GL_FLOAT, false, 0, 0);

        // Unbind
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        unbind();
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
        glDrawArrays(GL_TRIANGLES, 0, numberOfVertices);
    }

    @Override
    public void bind() {
        glBindVertexArray(vaoId);
        enableAttributeArrays(nextAttributeId);
    }

    @Override
    public void unbind() {
        disableAttributeArrays(nextAttributeId);
        glBindVertexArray(0);
    }


    @Override
    public void cleanup() {
        vboIds.forEach(GL30::glDeleteBuffers);
        glDeleteVertexArrays(vaoId);
    }

}
