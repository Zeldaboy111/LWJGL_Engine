package me.zeldaboy111.engine.graphic.model;

import static org.lwjgl.opengl.GL30.*;

public class DefaultMesh implements Mesh {
    private final int vaoId;
    private final int numberOfVertices;
    DefaultMesh(final int vaoId, final int numberOfVertices) {
        this.vaoId = vaoId;
        this.numberOfVertices = numberOfVertices;
    }

    @Override
    public void render() {
        glDrawArrays(GL_TRIANGLES, 0, numberOfVertices);
    }

    @Override
    public void bind() {
        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);
    }
    @Override
    public void unbind() {
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

    }


}
