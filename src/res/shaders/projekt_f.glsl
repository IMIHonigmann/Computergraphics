#version 330 core

in vec3 fragColor;
out vec4 displayColor;

void main() {
    displayColor = vec4(fragColor, 1.0);
}