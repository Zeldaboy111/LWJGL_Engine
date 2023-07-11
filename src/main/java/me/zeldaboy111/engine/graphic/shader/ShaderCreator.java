package me.zeldaboy111.engine.graphic.shader;

import me.zeldaboy111.engine.util.Util;

import static org.lwjgl.opengl.GL20.*;

final class ShaderCreator {
    private ShaderCreator() { }

    /**
     *  Method used to create a new shader
     * @param shaderProgramId - Program id from the {@link ShaderProgram}
     * @param shaderPath - Path towards the shader file
     * @param shaderType - Type of the shader to be created
     * @return ID of created shader
     */
    static int create(final int shaderProgramId, final String shaderPath, final int shaderType) {
        if (Util.isNullOrEmpty(shaderPath)) {
            throw new ShaderException("Cannot create Shader: shaderPath null");
        }

        // Create new shader
        final int shaderId = glCreateShader(shaderType);
        if(shaderId == 0) {
            throw new ShaderException(String.format("Could not create Shader. Type: %d", shaderType));
        }

        // Compile shader
        glShaderSource(shaderId, shaderPath);
        glCompileShader(shaderId);
        if(glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new ShaderException(String.format(
                    "Error compiling Shader. Code: %s",
                    glGetShaderInfoLog(shaderId, 1024)));
        }

        // Attach shader & return new Shader from the created id
        glAttachShader(shaderProgramId, shaderId);
        return shaderId;
    }
}
