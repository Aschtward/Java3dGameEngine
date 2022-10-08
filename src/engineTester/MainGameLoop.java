package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TextureModel;
import renderingEngine.DisplayMannager;
import renderingEngine.Loader;
import renderingEngine.MasterRenderer;
import renderingEngine.ObjLoader;
import renderingEngine.EntityRenderer;
import shaders.StaticShaders;
import terrain.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		
		DisplayMannager.createDisplay();
		Loader loader = new Loader();
		RawModel model  = ObjLoader.loadObject("stall", loader);
		RawModel rawFern = ObjLoader.loadObject("fern", loader);
		
		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture("stallTexture")));
		TextureModel staticFern = new TextureModel(rawFern, new ModelTexture(loader.loadTexture("fern")));
		
		ModelTexture texture = staticModel.getTexture();
		texture.setReflectivity(1);
		texture.setShineDamper(10);
		
		ModelTexture fernTexture = staticFern.getTexture();
		fernTexture.setReflectivity(0);
		fernTexture.setShineDamper(100);
		
		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,-35),0,0,0,1);
		Entity entity2 = new Entity(staticFern, new Vector3f(0,0,-5),0,0,0,1);
		fernTexture.setHasTransparency(true);
		fernTexture.setUseFakeLighting(true);
		Light light = new Light(new Vector3f(5,-12,-33), new Vector3f(1,1,1));
		Camera camera = new Camera();
		
		MasterRenderer mr = new MasterRenderer();
		
		while (!Display.isCloseRequested()) {
			camera.move();	
			// game loop
			mr.processTerrain(terrain2);
			mr.processTerrain(terrain);
			mr.processEntity(entity);
			mr.processEntity(entity2);
			mr.render(light, camera);
			DisplayMannager.updateDisplay();
		}
		mr.cleanUp();
		loader.cleanUp();
		DisplayMannager.closeDisplay();

	}

}
