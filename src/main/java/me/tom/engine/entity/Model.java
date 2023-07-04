package me.tom.engine.entity;

public class Model {
    private final int id;
    private final int vertexCount;
    private Texture texture;
    public Model(final int id, final int vertexCount) throws EntityException {
        this(id, vertexCount, null);
    }
    public Model(final Model model, final Texture texture) throws EntityException {
        this(model.id, model.vertexCount, texture);
    }
    public Model(final int id, final int vertexCount, final Texture texture) throws EntityException {
        // Run checks
        if(id < 0) {
            throw new EntityException("Cannot create Model: id must be at least zero");
        }

        // Set instance variables
        this.id = id;
        this.vertexCount = vertexCount;
        this.texture = texture;
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

    /**
     *  Sets the {@link Texture} from the {@link Model} to the given {@link Texture}
     * @param texture - New {@link Texture} from the {@link Model}
     */
    public void setTexture(final Texture texture) {
        this.texture = texture;
    }

    /**
     *  Gets the {@link Texture} from the {@link Model}
     * @return - {@link Texture} from the {@link Model}
     */
    public Texture getTexture() { return texture; }
}
