package engineTester;

import org.lwjgl.opengl.Display;

import renderingEngine.DisplayMannager;
import renderingEngine.Loader;
import renderingEngine.RawModel;
import renderingEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayMannager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		float[] vertices = { 
				-0.5f, 0.5f,0,
				-0.5f,-0.5f,0,
				0.5f,-0.5f,0,
				0.5f,0.5f,0
		};
		
		int[] indices = {0,1,3,3,1,2};
		RawModel model  = loader.loadtoVAO(vertices,indices);

		while (!Display.isCloseRequested()) {
			renderer.prepare();

			// game loop
			renderer.render(model);
			DisplayMannager.updateDisplay();
		}
		loader.cleanUp();
		DisplayMannager.closeDisplay();

	}

}
