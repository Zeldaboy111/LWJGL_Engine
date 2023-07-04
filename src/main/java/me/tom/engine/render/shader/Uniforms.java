package me.tom.engine.render.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

public class Uniforms {
    private final Map<String, Integer> uniforms;
    private final int programId;
    Uniforms(final int programId) throws ShaderException {
        if(programId < 1) {
            throw new ShaderException("Cannot initialize Uniforms: invalid program id");
        }

        this.programId = programId;
        uniforms = new HashMap<>();
    }


    /**
     *  Used to create a new uniform of the given name
     * @param uniformName - Name of the uniform to be created
     * @throws ShaderException Thrown if the uniform cannot be loaded due to the name being null or empty or
     * because of LWJGL not being able to find the uniform of the given name
     */
    public void create(final String uniformName) throws ShaderException {
        // Run checks
        if(uniformName == null || uniformName.trim().equals("")) {
            throw new ShaderException("Cannot create uniform: name null");
        }

        // Create the uniform
        int uniformLocation = GL30.glGetUniformLocation(programId, uniformName);
        if(uniformLocation < 0) {
            throw new ShaderException(String.format("Could not find Uniform " + uniformName));
        }

        uniforms.put(uniformName, uniformLocation);
    }

    /**
     *  Method used to set the value from the uniform of the given name to a {@link Matrix4f}
     * @param uniformName - Name of the uniform to set the value of
     * @param value - Value from the uniform ({@link Matrix4f})
     * @throws ShaderException Thrown if no uniform of the given name can be found
     */
    public void set(final String uniformName, final Matrix4f value) throws ShaderException {
        final int uniformLocation = getUniformLocation(uniformName);

        // Set the uniform
        try(MemoryStack stack = MemoryStack.stackPush()) {
            // Ease the eyes
            final FloatBuffer uniformValue = value.get(stack.mallocFloat(16));

            // Set the uniform
            GL30.glUniformMatrix4fv(uniformLocation, false, uniformValue);
        }
    }

    /**
     *  Method used to set the value from the uniform of the given name to an int
     * @param uniformName - Name of the uniform to set the value of
     * @param value - Value from the uniform (int)
     * @throws ShaderException Thrown if no uniform of the given name can be found
     */
    public void set(final String uniformName, final int value) throws ShaderException {
        final int uniformLocation = getUniformLocation(uniformName);

        GL30.glUniform1i(uniformLocation, value);
    }

    /**
     *  Method used to set the value from the uniform of the given name to a float
     * @param uniformName - Name of the uniform to set the value of
     * @param value - Value from the uniform (float)
     * @throws ShaderException Thrown if no uniform of the given name can be found
     */
    public void set(final String uniformName, final float value) throws ShaderException {
        final int uniformLocation = getUniformLocation(uniformName);

        GL30.glUniform1f(uniformLocation, value);
    }

    /**
     *  Method used to set the value from the uniform of the given name to a {@link Vector3f}
     * @param uniformName - Name of the uniform to set the value of
     * @param value - Value from the uniform ({@link Vector3f})
     * @throws ShaderException Thrown if no uniform of the given name can be found
     */
    public void set(final String uniformName, final Vector3f value) throws ShaderException {
        final int uniformLocation = getUniformLocation(uniformName);

        GL30.glUniform3f(uniformLocation, value.x, value.y, value.z);
    }

    /**
     *  Method used to set the value from the uniform of the given name to a {@link Vector4f}
     * @param uniformName - Name of the uniform to set the value of
     * @param value - Value from the uniform ({@link Vector4f})
     * @throws ShaderException Thrown if no uniform of the given name can be found
     */
    public void set(final String uniformName, final Vector4f value) throws ShaderException {
        final int uniformLocation = getUniformLocation(uniformName);

        GL30.glUniform4f(uniformLocation, value.x, value.y, value.z, value.w);
    }

    /**
     *  Method used to set the value from the uniform of the given name to a boolean
     * @param uniformName - Name of the uniform to set the value of
     * @param value - Value from the uniform (boolean)
     * @throws ShaderException Thrown if no uniform of the given name can be found
     */
    public void set(final String uniformName, final boolean value) throws ShaderException {
        final int uniformLocation = getUniformLocation(uniformName);

        GL30.glUniform1f(uniformLocation, value ? 1 : 0);
    }



    /**
     *  Gets the location from the uniform of the given name
     * @param uniformName - Name of uniform to retrieve the location of
     * @return Location from the uniform of the given name
     * @throws ShaderException Thrown if the uniform of the given name cannot be found
     */
    private int getUniformLocation(final String uniformName) throws ShaderException {
        if (!uniforms.containsKey(uniformName)) {
            throw new ShaderException(String.format("Could not set the uniform '%s': uniform not found", uniformName));
        }

        return uniforms.get(uniformName);
    }

}
