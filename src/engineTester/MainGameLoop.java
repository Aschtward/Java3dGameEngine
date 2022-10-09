package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TextureModel;
import objLoader.EntityLoader;
import objLoader.ModelData;
import objLoader.OBJFileLoader;
import renderingEngine.DisplayMannager;
import renderingEngine.Loader;
import renderingEngine.MasterRenderer;
import terrain.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {

		
		DisplayMannager.createDisplay();
		Loader loader = new Loader();
		EntityLoader entiLoader = new EntityLoader();
		
	    Entity entity = entiLoader.loadEntity("stall", "stallTexture", false, false, 0, 1, new Vector3f(0,0,-35), 0, 180, 0, 2);
		Player player = entiLoader.loadPlayer("fern", "fern", false, true, 0, 0, new Vector3f(0,0,-23), 0, 0, 0, 1);
		
		TerrainTexture backgroundTexture  = new TerrainTexture(loader.loadTexture("grass1"));
		TerrainTexture rTexture  = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture  = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture  = new TerrainTexture(loader.loadTexture("path"));
		TerrainTexture blendMap  = new TerrainTexture(loader.loadTexture("blendMap"));
		
		TerrainTexturePack pack =new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);
		
		Terrain terrain = new Terrain(-1,-1,loader,pack,blendMap);
		Terrain terrain2 = new Terrain(0,-1,loader,pack,blendMap);
		
		Light light = new Light(new Vector3f(0,50,0), new Vector3f(1,1,0.95f));
		Camera camera = new Camera();
		camera.setPosition(new Vector3f(0,20,30));
		
		MasterRenderer mr = new MasterRenderer();
		
		while (!Display.isCloseRequested()) {
			camera.move();	
			// game loop
			mr.processTerrain(terrain2);
			mr.processTerrain(terrain);
			player.move();
			mr.processEntity(entity);
			mr.processEntity(player);
			mr.render(light, camera);
			DisplayMannager.updateDisplay();
		}
		mr.cleanUp();
		loader.cleanUp();
		DisplayMannager.closeDisplay();

	}

}
