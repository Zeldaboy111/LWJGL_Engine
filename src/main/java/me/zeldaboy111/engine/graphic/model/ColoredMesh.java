package me.zeldaboy111.engine.graphic.model;

import static me.zeldaboy111.engine.graphic.model.MeshUtil.createVboWithContentsFloatArray;
import static org.lwjgl.opengl.GL11.GL_FLOAT;

public class ColoredMesh extends BasicMesh {

    public ColoredMesh(final float[] vertices, final float[] colors, final int[] indices) {
        super(vertices, indices, false);
        if(colors == null || colors.length < 1) {
            throw new ModelException("Cannot create Mesh: colors length must be at least 1");
        }

        // Apply color
        vboIds.add(createVboWithContentsFloatArray(colors));
        setAttributePointer(3, GL_FLOAT, false, 0, 0);

        // Unbind
        unbind();
    }
}
