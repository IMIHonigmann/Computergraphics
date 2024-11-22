package projekt;

public class Matrix4 {

	private float[] matrix;

	public Matrix4() {
		// Initialize with identity matrix
		this.matrix = new float[]{
				1.0f, 0.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f, 0.0f,
				0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		};
	}

	public Matrix4 rotateX(float angle) {
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);

		float[] rotX = new float[] {
				1.0f, 0.0f, 0.0f, 0.0f,
				0.0f, cos, -sin,  0.0f,
				0.0f, sin,  cos,  0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		};

		multiply(rotX);
		return this;
	}

	public Matrix4 rotateZ(float angle) {
		// Compute rotation matrix for the Z axis
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);

		float[] rotY = new float[]{
				cos, -sin, 0.0f, 0.0f,
				sin,  cos, 0.0f, 0.0f,
				0.0f, 0.0f, 1.0f, 0.0f,
				0.0f, 0.0f, 0.0f, 1.0f
		};

		multiply(rotY);
		return this;
	}

	public Matrix4 distortByPerspective(float aspectRatio, float fov, float farClipP, float nearClipP) {
		float fovRad = (float) Math.toRadians(fov);

		float val11 = (float) (1 / (aspectRatio * Math.tan(fovRad/2)));
		float val22 = (float) (1 / Math.tan(fovRad/2));
		float val33 = 		  -((farClipP+nearClipP)/(farClipP-nearClipP));
		float val34 = 		  -((2*farClipP*nearClipP)/(farClipP-nearClipP));

		float[] perspectiveMatrix = new float[]{
				val11, 0.0f,  0.0f,  0.0f,
				0.0f, val22,  0.0f,  0.0f,
				0.0f,  0.0f, val33, val34,
				0.0f,  0.0f, -1.0f,  0.0f
		};

		multiply(perspectiveMatrix);
		return this;
	}

	private void multiply(float[] otherMatrix) {
		float[] result = new float[16];

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				result[row * 4 + col] =
						matrix[row * 4 + 0] * otherMatrix[0 * 4 + col] +
						matrix[row * 4 + 1] * otherMatrix[1 * 4 + col] +
						matrix[row * 4 + 2] * otherMatrix[2 * 4 + col] +
						matrix[row * 4 + 3] * otherMatrix[3 * 4 + col];
			}
		}

		matrix = result;
	}

	public float[] getValuesAsArray() {
		return this.matrix.clone(); // Ensure immutability
	}
}