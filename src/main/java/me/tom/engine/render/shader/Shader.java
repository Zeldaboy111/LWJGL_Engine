package me.tom.engine.render.shader;

public interface Shader {
    /**
     *  Binds the {@link ShaderFile} to the program of the given ID
     * @param programId - ID from the program to bind the {@link ShaderFile} to
     * @throws ShaderException Thrown if the {@link Shader} cannot be bound
     */
    void bind(final int programId) throws ShaderException;

    /**
     *  Detaches the {@link ShaderFile} to the program of the given ID
     * @param programId - ID from the program to detach the {@link ShaderFile} to
     * @throws ShaderException Thrown if the {@link Shader} cannot be detached
     */
    void detach(final int programId) throws ShaderException;
}
