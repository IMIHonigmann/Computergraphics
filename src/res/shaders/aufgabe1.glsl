#version 330

out vec4 pixelFarbe;

uniform vec2 uAufloesung;

// Checks if a point is within a circle centered at a given position with a specific radius
bool inCircle(vec2 point, float radius, vec2 center) {
    return distance(point, center) <= radius;
}

// Checks if a point is within a rectangle of a given scale centered at (0.5, 0.5)
bool inRect(vec2 point, vec2 scale) {
    vec2 minCorner = vec2(0.5) - scale * 0.5;
    vec2 maxCorner = vec2(0.5) + scale * 0.5;
    return point.x >= minCorner.x && point.x <= maxCorner.x && point.y >= minCorner.y && point.y <= maxCorner.y;
}

// Rotation matrix for 2D transformations
mat2 rotate(float angle) {
    return mat2(cos(angle), -sin(angle), sin(angle), cos(angle));
}

void main() {
    vec2 coord = gl_FragCoord.xy / uAufloesung;
    vec3 color = vec3(0.1); // Background color

    // Draw rectangles and circles with specific colors
    if (inRect(rotate(0.5) * coord, vec2(0.4)))
    color = vec3(0.0, 1.0, 0.0); // Green

    if (inRect(coord, vec2(0.2)))
    color = vec3(1.0, 0.0, 0.0); // Red

    vec2 rotatedCoord = rotate(0.3) * (coord - vec2(0.5)) + vec2(0.5);
    if (inRect(rotatedCoord, vec2(0.1)))
    color = vec3(0.0, 0.0, 1.0); // Blue

    if (inCircle(coord, 0.1, vec2(1.0, 1.0)))
    color = vec3(1.0, 0.0, 0.5); // Magenta

    if (inCircle(coord, 0.3, vec2(1.5, 0.5)))
    color = vec3(1.0, 1.0, 0.5); // Yellowish

    pixelFarbe = vec4(color, 1.0);
}