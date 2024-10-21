#version 330 core

// Interpolated out color from the vertex shader
vec3 fragColor = vec3(1.0, 1.0, 0.0);
vec3 point1 = vec3(1.0, 2.0, 3.0);
vec2 center = vec2(350.0, 350.0);
float radius = 40.0;

out vec4 outColor;

void main()
{
    vec2 pixelPosition = gl_FragCoord.xy;
    float dist = distance(center, pixelPosition);

    outColor = vec4(fragColor, 1.0);
    if(dist < radius) {
        outColor = vec4(1.0, 0.0, 0.0, 1.0);
    }

}