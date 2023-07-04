#version 400 core

in vec2 fragmentTextureCoordinate;

out vec4 fragmentColor;

uniform sampler2D textureSampler;

void main() {
    fragmentColor = texture(textureSampler, fragmentTextureCoordinate);
}