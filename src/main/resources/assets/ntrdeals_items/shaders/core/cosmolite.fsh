#version 330
#extension GL_ARB_separate_shader_objects : require

#include <minecraft:fog.glsl>
#include <minecraft:matrix.glsl>
#include <minecraft:globals.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler1;

#ifdef MASK
uniform sampler2D Sampler2;
#endif

layout(location = 1) in float sphericalVertexDistance;
layout(location = 2) in float cylindricalVertexDistance;

layout(location = 0) out vec4 fragColor;

const vec3[] COLORS = vec3[](
        vec3(0.022087, 0.098399, 0.110818),
        vec3(0.011892, 0.095924, 0.089485),
        vec3(0.027636, 0.101689, 0.100326),
        vec3(0.046564, 0.109883, 0.114838),
        vec3(0.064901, 0.117696, 0.097189),
        vec3(0.063761, 0.086895, 0.123646),
        vec3(0.084817, 0.111994, 0.166380),
        vec3(0.097489, 0.154120, 0.091064),
        vec3(0.106152, 0.131144, 0.195191),
        vec3(0.097721, 0.110188, 0.187229),
        vec3(0.133516, 0.138278, 0.148582),
        vec3(0.070006, 0.243332, 0.235792),
        vec3(0.196766, 0.142899, 0.214696),
        vec3(0.047281, 0.315338, 0.321970),
        vec3(0.204675, 0.390010, 0.302066),
        vec3(0.080955, 0.314821, 0.661491)
);

#ifdef FLAT
layout(location = 0) in vec2 texCoord0;

vec2 rotate2D(vec2 v, float radiansValue) {
    float s = sin(radiansValue);
    float c = cos(radiansValue);
    return vec2(v.x * c - v.y * s, v.x * s + v.y * c);
}

vec2 end_portal_layer_uv(vec2 uv, float layer) {
    vec2 scaled = uv * (4.5 - layer / 4.0) * 2.0;
    vec2 rotated = rotate2D(scaled, radians((layer * layer * 4321.0 + layer * 9.0) * 2.0));
    vec2 drifted = rotated + vec2(17.0 / layer, (2.0 + layer / 1.5) * (GameTime * 1.5));
    return drifted;
}

void main() {
    #ifdef MASK
    if (texture(Sampler2, texCoord0).a < 1) discard;
    #endif

    vec2 uv = texCoord0 * 0.25f;

    vec3 color = texture(Sampler0, uv).rgb * COLORS[0];
    for (int layer = 0; layer < PORTAL_LAYERS; layer++) {
        color += texture(Sampler1, end_portal_layer_uv(uv, layer + 1)).rgb * COLORS[layer];
    }

    fragColor = apply_fog(vec4(color, 1), sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart, FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd, FogColor);
}
#else
layout(location = 0) in vec4 texProj0;

const mat4 SCALE_TRANSLATE = mat4(
        0.5, 0.0, 0.0, 0.25,
        0.0, 0.5, 0.0, 0.25,
        0.0, 0.0, 1.0, 0.0,
        0.0, 0.0, 0.0, 1.0
);

mat4 end_portal_layer(float layer) {
    mat4 translate = mat4(
            1.0, 0.0, 0.0, 17.0 / layer,
            0.0, 1.0, 0.0, (2.0 + layer / 1.5) * (GameTime * 1.5),
            0.0, 0.0, 1.0, 0.0,
            0.0, 0.0, 0.0, 1.0
    );

    mat2 rotate = mat2_rotate_z(radians((layer * layer * 4321.0 + layer * 9.0) * 2.0));

    mat2 scale = mat2((4.5 - layer / 4.0) * 2.0);

    return mat4(scale * rotate) * translate * SCALE_TRANSLATE;
}

void main() {
    #ifdef MASK
    if (textureProj(Sampler2, texProj0).a < 1) discard;
    #endif

    vec3 color = textureProj(Sampler0, texProj0).rgb * COLORS[0];
    for (int i = 0; i < PORTAL_LAYERS; i++) {
        color += textureProj(Sampler1, texProj0 * end_portal_layer(float(i + 1))).rgb * COLORS[i];
    }
    fragColor = apply_fog(vec4(color, 1.0), sphericalVertexDistance, cylindricalVertexDistance, FogEnvironmentalStart, FogEnvironmentalEnd, FogRenderDistanceStart, FogRenderDistanceEnd, FogColor);
}
#endif