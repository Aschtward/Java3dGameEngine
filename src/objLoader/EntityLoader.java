package objLoader;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Player;
import models.RawModel;
import models.TextureModel;
import renderingEngine.Loader;
import textures.ModelTexture;

public class EntityLoader {
	
	static Loader loader = new Loader();
	
	public Entity loadEntity(String objPath, String objTexturePath,boolean fakeLight, boolean hasTransperency,
			float reflectivity, float shineDamper, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		
		ModelData stallData = OBJFileLoader.loadOBJ(objPath);
		RawModel model  = loader.loadtoVAO(stallData.getVertices(), stallData.getTextureCoords(), stallData.getNormals(), stallData.getIndices());
		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture(objTexturePath)));
		ModelTexture texture = staticModel.getTexture();
		texture.setReflectivity(reflectivity);
		texture.setShineDamper(shineDamper);
		texture.setHasTransparency(hasTransperency);
		texture.setUseFakeLighting(fakeLight);
		Entity entity = new Entity(staticModel, position,rotX,rotY,rotZ,scale);
		return entity;
	}
	
	public Player loadPlayer(String objPath, String objTexturePath,boolean fakeLight, boolean hasTransperency,
			float reflectivity, float shineDamper, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		
		ModelData stallData = OBJFileLoader.loadOBJ(objPath);
		RawModel model  = loader.loadtoVAO(stallData.getVertices(), stallData.getTextureCoords(), stallData.getNormals(), stallData.getIndices());
		TextureModel staticModel = new TextureModel(model, new ModelTexture(loader.loadTexture(objTexturePath)));
		ModelTexture texture = staticModel.getTexture();
		texture.setReflectivity(reflectivity);
		texture.setShineDamper(shineDamper);
		texture.setHasTransparency(hasTransperency);
		texture.setUseFakeLighting(fakeLight);
		Player entity = new Player(staticModel, position,rotX,rotY,rotZ,scale);
		return entity;
	}
}
