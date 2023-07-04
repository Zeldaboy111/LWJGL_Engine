package me.tom.engine.utils;

import me.tom.engine.entity.Entity;
import org.joml.Matrix4f;

public class Transformation {
    private Transformation() { }

    /**
     *  Used to create the transformation matrix from the given {@link Entity} representing its' position and scale
     *
     * @param entity - {@link Entity} to create a matrix of
     * @return Transformation matrix as {@link Matrix4f}
     */
    public static Matrix4f createTransformationMatrix(final Entity entity) {
        Matrix4f matrix = new Matrix4f();
        matrix.identity().translate(entity.getPosition())
                .rotateX((float)Math.toRadians(entity.getRotation().x))
                .rotateY((float)Math.toRadians(entity.getRotation().y))
                .rotateZ((float)Math.toRadians(entity.getRotation().z))
                .scale(entity.getScale());

        return matrix;
    }

}
