#version 330

layout(location=0) in vec2 leckenAusJava; // Empfängt die 2D-Position des Vertex
layout(location=1) in vec3 vertexColor;
//uniform in vec3 uTime;
out vec3 fragColor;
const float PI = 3.14159265358979323846;

mat2 rot(float angle) {
    return mat2(cos(angle), -sin(angle), sin(angle), cos(angle));
}

void main() {
    // Wandle die 2D-Position in eine 4D-Position um, indem wir z und w hinzufügen
    gl_Position = vec4(rot(78 * 180/PI)*leckenAusJava, 0.0, 1.0);
    fragColor = vertexColor;
}