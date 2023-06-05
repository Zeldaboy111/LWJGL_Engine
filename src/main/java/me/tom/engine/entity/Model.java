package me.tom.engine.entity;

public class Model {
    private final int id;
    private final int vertexCount;
    public Model(final int id, final int vertexCount) throws EntityException {
        // Run checks
        if(id < 0) {
            throw new EntityException("Cannot create Model: id must be at least zero");
        }

        // Set instance variables
        this.id = id;
        this.vertexCount = vertexCount;
    }

    /**
     *  Gets the id from the {@link Model}
     * @return Id from the {@link Model}
     */
    public int getId() { return id; }

    /**
     *  Gets the amount of vertices the {@link Model} has
     * @return Amount of vertices from the {@link Model}
     */
    public int getVertexCount() { return vertexCount; }
}
