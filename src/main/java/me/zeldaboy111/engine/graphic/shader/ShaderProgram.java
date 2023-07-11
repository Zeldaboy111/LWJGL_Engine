package me.zeldaboy111.engine.graphic.shader;

public interface ShaderProgram {
    /**
     *  Method used to create a new vertex shader
     * @param shaderPath - Path towards shader file
     */
    void createVertexShader(final String shaderPath);

    /**
     *  Method used to create a new fragment shader
     * @param shaderPath - Path towards shader file
     */
    void createFragmentShader(final String shaderPath);

    /**
     *  Used to link every shader from the {@link ShaderProgram}
     */
    void link();

    /**
     *  Used to bind the {@link ShaderProgram}
     */
    void bind();

    /**
     *  Used to unbind the {@link ShaderProgram}
     */
    void unbind();

    /**
     *  Used to clean up the {@link ShaderProgram}
     */
    void cleanup();

}
