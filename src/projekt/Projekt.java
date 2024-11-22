package projekt;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Projekt extends AbstractOpenGLBase {

	private ShaderProgram shaderProgram;
	private int vaoId;
	private Matrix4 modelMatrix;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		// VAO
		vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		// Pos VBO
		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);

		float[] vertices = {
				-0.5f, -0.5f, 0.0f,
				0.5f, -0.5f, 0.0f,
				0.0f,  0.5f, 0.0f
		};

		float[] vertexColor = {
				0.0f, 0.482f, 1.0f,  // Electric Blue
				1.0f, 0.843f, 0.0f,  // Brilliant Yellow
				1.0f, 0.078f, 0.576f // Hot Pink
		};

		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
		// Define structure of the vertex input to the vertex shader
		int posAttrib = glGetAttribLocation(shaderProgram.getId(), "position");
		glEnableVertexAttribArray(posAttrib);
		glVertexAttribPointer(posAttrib, 3, GL_FLOAT, false, 0, 0);

		int colorVBOid = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, colorVBOid);
		glBufferData(GL_ARRAY_BUFFER, vertexColor, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1); // dont forget to pass the index here to enable it


		glEnable(GL_DEPTH_TEST);
//		glEnable(GL_CULL_FACE);
//		glCullFace(GL_BACK); DISABLED BACKFACE CULLING

		// Unbind VAO
		glBindVertexArray(0);

		// Initialize model matrix
		modelMatrix = new Matrix4();
	}

	@Override
	public void update() {
		// Update model matrix to rotate around Z axis
		float frequencyInverse = 250.0f;
		float amplitude = 20f;
		float time = (System.currentTimeMillis() % 3600 / frequencyInverse);
		float angle = (float) (Math.sin(time) * amplitude);
		modelMatrix = new Matrix4().rotateZ(angle).rotateX(time * amplitude / 2);

		// Upload this matrix to the shader as a uniform
		int loc = glGetUniformLocation(shaderProgram.getId(), "model");
		glUniformMatrix4fv(loc, false, modelMatrix.getValuesAsArray());
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glUseProgram(shaderProgram.getId());

		// Bind VAO and draw
		glBindVertexArray(vaoId);
		glDrawArrays(GL_TRIANGLES, 0, 3);
		glBindVertexArray(0);
	}
}