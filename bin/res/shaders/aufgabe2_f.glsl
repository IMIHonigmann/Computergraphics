#version 330

vec3 fragColor = vec3(1.0, 1.0, 0.0);
vec3 point1 = vec3(1.0, 2.0, 3.0);
vec2 center = vec2(350.0, 350.0);

out vec4 outColor;

bool isInCircle(vec2 point, float radius) {
    float dist = distance(center, point);
    if(dist < radius) {
        return true;
    }
    return false;
}

void main()
{
    vec2 pixelPosition = gl_FragCoord.xy;

    outColor = vec4(fragColor, 1.0);
    if(isInCircle(pixelPosition, 40.0)) {
        outColor = vec4(1.0, 0.0, 0.0, 1.0);
    }

}