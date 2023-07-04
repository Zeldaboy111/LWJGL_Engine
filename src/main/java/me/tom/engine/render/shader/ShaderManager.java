package me.tom.engine.render.shader;

import org.lwjgl.opengl.GL30;

public final class ShaderManager {
    private final int programId;
    private final Uniforms uniforms;
    private Shader vertexShader, fragmentShader;

    public ShaderManager() throws ShaderException {
        programId = GL30.glCreateProgram();
        if(programId == 0) {
            throw new ShaderException("Cannot initialize ShaderManager: no shader could be created");
        }

        this.uniforms = new Uniforms(programId);
    }

    /**
     *  Gets the container for every registered uniform inside the shaders
     * @return {@link Uniforms} containing every registered uniform being used in the shader
     */
    public Uniforms getUniforms() { return uniforms; }


    /**
     *  Creates a new vertex shader with as file source {@param shaderFile}
     * @param shaderFile - Source from the shader file
     * @throws ShaderException
     */
    public void createVertexShader(final String shaderFile) throws ShaderException {
        vertexShader = createShader(shaderFile, GL30.GL_VERTEX_SHADER);
    }

    /**
     *  Creates a new fragment shader with as file source {@param shaderFile}
     * @param shaderFile - Source from the shader file
     * @throws ShaderException
     */
    public void createFragmentShader(final String shaderFile) throws ShaderException {
        fragmentShader = createShader(shaderFile, GL30.GL_FRAGMENT_SHADER);
    }

    /**
     *  Creates a new {@link Shader} from the given parameters
     * @param shaderFile - Source file pointing towards the location of the shaderfile
     * @param shaderType - Type of shader
     * @return {@link Shader} from parameters
     * @throws ShaderException
     */
    private Shader createShader(final String shaderFile, final int shaderType) throws ShaderException {
        if(shaderFile == null || shaderFile.trim().equals("")) {
            throw new ShaderException("Cannot create shader: shaderCode null");
        }

        final Shader shader = new ShaderFile(shaderFile, shaderType);
        shader.bind(programId);
        return shader;
    }

    /**
     *  Links the {@link ShaderManager}
     * @throws ShaderException
     */
    public void link() throws ShaderException {
        GL30.glLinkProgram(programId);
        if(GL30.glGetProgrami(programId, GL30.GL_LINK_STATUS) == 0) {
            throw new ShaderException(String.format("Cannot link shader program. Information: %s",
                    GL30.glGetProgramInfoLog(programId, 1024)));
        }

        if(vertexShader != null) {
            vertexShader.detach(programId);
        }
        if(fragmentShader != null) {
            fragmentShader.detach(programId);
        }

        GL30.glValidateProgram(programId);
        if(GL30.glGetProgrami(programId, GL30.GL_VALIDATE_STATUS) == 0) {
            throw new ShaderException(String.format(" Cannot validate shader program. Information: %s",
                    GL30.glGetProgramInfoLog(programId, 1024)));
        }
    }

    /**
     *  Binds the {@link ShaderManager}
     */
    public void bind() {
        GL30.glUseProgram(programId);
    }

    /**
     *  Unbinds the {@link ShaderManager}
     */
    public void unbind() {
        GL30.glUseProgram(0);
    }

    /**
     *  Cleans up the {@link ShaderManager}
     */
    public void cleanup() {
        unbind();
        if (programId != 0) {
            GL30.glDeleteProgram(programId);
        }
    }
}
