#version 330

in vec3 fragColor;  // Interpolated color from the vertex shader
out vec4 color;     // Output to the framebuffer

void main() {
    // Set the output color to the interpolated vertex color
    color = vec4(fragColor, 1.0);
}