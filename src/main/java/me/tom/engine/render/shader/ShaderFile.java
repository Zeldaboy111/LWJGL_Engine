package me.tom.engine.render.shader;

import org.lwjgl.opengl.GL30;

final class ShaderFile implements Shader {
    private final int id;
    ShaderFile(final String shaderFile, final int shaderType) throws ShaderException {
        id = GL30.glCreateShader(shaderType);
        if (id == 0) {
            throw new ShaderException(String.format("Cannot create Shader of type %d", shaderType));
        }

        // Set the source of the Shader and compile
        GL30.glShaderSource(id, shaderFile);
        GL30.glCompileShader(id);
        if(GL30.glGetShaderi(id, GL30.GL_COMPILE_STATUS) == 0) {
            throw new ShaderException(String.format("Cannot compile Shader of type %d. Information: %s",
                    shaderType, GL30.glGetShaderInfoLog(id, 1024)));
        }
    }

    @Override
    public void bind(final int programId) throws ShaderException {
        if(programId < 1) {
            throw new ShaderException("Cannot bind Shader: program ID must be at least one");
        }

        // Attach the shader to the program of the given ID
        GL30.glAttachShader(programId, id);
    }

    @Override
    public void detach(final int programId) throws ShaderException {
        if(programId < 1) {
            throw new ShaderException("Cannot detach Shader: program ID must be at least one");
        }

        // Attach the shader to the program of the given ID
        GL30.glDetachShader(programId, id);
    }

}
