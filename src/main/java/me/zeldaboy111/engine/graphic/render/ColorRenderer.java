package me.zeldaboy111.engine.graphic.render;

import me.zeldaboy111.engine.graphic.shader.DefaultShaderProgram;
import me.zeldaboy111.engine.util.Util;

public class ColorRenderer extends BasicRenderer {
    @Override
    public void init() {
        shaderProgram = new DefaultShaderProgram();
        shaderProgram.createVertexShader(Util.readFile("./src/main/resources/shader/color/vertex.vert"));
        shaderProgram.createFragmentShader(Util.readFile("./src/main/resources/shader/color/fragment.frag"));
        shaderProgram.link();
    }
}
