package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	float[] dreiecksKoordinaten = {
			 0.0f,  0.5f,  // Spitze des Dreiecks
			-0.5f, -0.5f,  // Linke untere Ecke
			 0.5f, -0.5f   // Rechte untere Ecke
	};
	float[] vertexColors = {
			0.0f, 0.482f, 1.0f,  // Electric Blue
			1.0f, 0.843f, 0.0f,  // Brilliant Yellow
			1.0f, 0.078f, 0.576f // Hot Pink
	};

	int anzEcken = 3;

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// folgende Zeile laed automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		// passing time ;)
		float time = 3.0f;
		int uniformLocation = glGetUniformLocation(shaderProgram.getId(), "uTime");
		glUniform1f(uniformLocation, time);
	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenflaeche leeren

		// hier vorher erzeugte VAOs zeichnen

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int posVBOid = glGenBuffers();
		int colorVBOid = glGenBuffers(); // dont forget to pass two different VBOids
		createVBO(posVBOid, dreiecksKoordinaten, 0, 2);
		createVBO(colorVBOid, vertexColors, 1, 3);

		//Welches VAO soll gezeichnet werden
		glBindVertexArray(vaoId);
		//zeichnet Dreiecke, beginnt bei Ecke 0
		//und verarbeitet gegebene Anzahl Ecken
		glDrawArrays(GL_TRIANGLES, 0, anzEcken);
	}

	public void createVBO(int vboId, float[] values, int location, int size) {
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, values, GL_STATIC_DRAW);
		glVertexAttribPointer(location, size, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(location); // dont forget to pass the index here to enable it
	}
}
