package me.zeldaboy111.engine.util;

import static org.lwjgl.opengl.GL20.*;

public class GlProgram {
    private final int programId;
    protected GlProgram() {
        programId = glCreateProgram();
        if(programId == 0) {
            throw new UtilException("Could not create GlProgram");
        }
    }

    /**
     *  Method used to retrieve the id from the {@link GlProgram}
     * @return ID from the {@link GlProgram}
     */
    protected final int getProgramId() { return programId; }

    /**
     *  Used to bind the {@link GlProgram}
     */
    protected void bind() {
        glUseProgram(programId);
    }

    /**
     *  Used to unbind the {@link GlProgram}
     */
    protected void unbind() {
        glUseProgram(0);
    }

    /**
     *  Used to clean up the {@link GlProgram}
     */
    protected void cleanup() {
        unbind();
        if(programId != 0) {
            glDeleteProgram(programId);
        }
    }

}
