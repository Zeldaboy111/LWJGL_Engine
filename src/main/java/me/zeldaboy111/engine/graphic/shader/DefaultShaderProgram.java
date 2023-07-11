package me.zeldaboy111.engine.graphic.shader;

import me.zeldaboy111.engine.util.GlProgram;

import static org.lwjgl.opengl.GL20.*;

public class DefaultShaderProgram extends GlProgram implements ShaderProgram {
    private int vertexShader, fragmentShader;

    public DefaultShaderProgram() {
        super();
    }

    @Override
    public void createVertexShader(final String shaderPath) {
        vertexShader = ShaderCreator.create(getProgramId(), shaderPath, GL_VERTEX_SHADER);
    }

    @Override
    public void createFragmentShader(final String shaderPath) {
        fragmentShader = ShaderCreator.create(getProgramId(), shaderPath, GL_FRAGMENT_SHADER);
    }

    @Override
    public void link() {
        glLinkProgram(getProgramId());
        if(glGetProgrami(getProgramId(), GL_LINK_STATUS) == 0) {
            throw new ShaderException(String.format(
                    "Could not link Shader. Code: %s",
                    glGetProgramInfoLog(getProgramId(), 1024)));
        }

        detachShader(vertexShader);
        detachShader(fragmentShader);
    }

    /**
     *  Method used to detach the shader of given id
     * @param shaderId - ID from the shader to be detached
     */
    private void detachShader(final int shaderId) {
        if(shaderId != 0) {
            glDetachShader(getProgramId(), shaderId);
        }
    }

    @Override
    public void bind() {
        super.bind();
    }

    @Override
    public void unbind() {
        super.unbind();
    }

    @Override
    public void cleanup() {
        super.cleanup();
    }


}
