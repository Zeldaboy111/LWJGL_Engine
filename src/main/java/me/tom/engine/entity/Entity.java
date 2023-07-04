package me.tom.engine.entity;

import org.joml.Vector3f;

public class Entity {
    private final Model model;
    private final Vector3f position, rotation;
    private final float scale;

    public Entity(final Model model) {
        this(model, new Vector3f(0, 0, 0));
    }
    public Entity(final Model model, final Vector3f position) {
        this(model, position, new Vector3f(0, 0, 0));
    }
    public Entity(final Model model, final Vector3f position, final Vector3f rotation) {
        this(model, position, rotation, 1);
    }

    public Entity(final Model model, final Vector3f position, final Vector3f rotation, final float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    /**
     *  Increases the position by the given offset
     * @param x - Offset on the x-value
     * @param y - Offset on the y-value
     * @param z - Offset on the z-value
     */
    public void incrementPosition(final float x, final float y, final float z) {
        this.position.add(x, y, z);
    }

    /**
     * Sets the position to a position from the given coordinates
     * @param x - X-value of new position
     * @param y - Y-value of new position
     * @param z - Z-value of new position
     */
    public void setPosition(final float x, final float y, final float z) {
        this.position.set(x, y, z);
    }

    /**
     *  Increases the rotation by the given offset
     * @param x - Offset on the x-value
     * @param y - Offset on the y-value
     * @param z - Offset on the z-value
     */
    public void incrementRotation(final float x, final float y, final float z) {
        this.rotation.add(x, y, z);
    }

    /**
     * Sets the rotation to a rotation from the given coordinates
     * @param x - X-value of new rotation
     * @param y - Y-value of new rotation
     * @param z - Z-value of new rotation
     */
    public void setRotation(final float x, final float y, final float z) {
        this.rotation.set(x, y, z);
    }

    /**
     *  Gets the {@link Model} from the {@link Entity}
     * @return {@link Model} from the {@link Entity}
     */
    public Model getModel() {
        return model;
    }

    /**
     *  Gets the position from the {@link Entity}
     * @return Position from the {@link Entity}
     */
    public Vector3f getPosition() {
        return position;
    }

    /**
     *  Gets the rotation from the {@link Entity}
     * @return Rotation from the {@link Entity}
     */
    public Vector3f getRotation() {
        return rotation;
    }

    /**
     *  Gets the scale from the {@link Entity}
     * @return Scale from the {@link Entity}
     */
    public float getScale() {
        return scale;
    }
}

