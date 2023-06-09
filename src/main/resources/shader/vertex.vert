#version 400 core

in vec3 position;
in vec2 textureCoordinate;

out vec2 fragmentTextureCoordinate;

uniform mat4 transformationMatrix;

void main() {
    gl_Position = transformationMatrix * vec4(position, 1.0);
    fragmentTextureCoordinate = textureCoordinate;
}