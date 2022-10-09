package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TextureModel;
import renderingEngine.DisplayMannager;

public class Player extends Entity{
	
	private static final float speed = 20;
	private static final float turnSpeed = 160;
	private static final float gravity = -50;
	private static final float jumpPower = 50;
	
	private static final float terrainHeight = 0;
	
	private boolean isAir = false;
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;

	public Player(TextureModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
	}
	
	public void move() {
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayMannager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayMannager.getFrameTimeSeconds();
		float dx = (float) (distance * Math.sin(Math.toRadians(getRotY())));
		float dz = (float) (distance * Math.cos(Math.toRadians(getRotY())));
		super.increasePosition(dx, 0, dz);
		upwardsSpeed += gravity * DisplayMannager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayMannager.getFrameTimeSeconds() , 0);
		if(super.getPosition().y <= terrainHeight) {
			upwardsSpeed = 0;
			super.getPosition().y = terrainHeight;
			isAir = false;
		}
	}
	
	private void jump() {
		if(!isAir) {
			this.upwardsSpeed = jumpPower;
			isAir = true;
		}
	}
	
	private void checkInputs() {
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = speed;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -speed;
		}else {
			currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			currentTurnSpeed = -turnSpeed;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			currentTurnSpeed = turnSpeed;
		}else { 
			currentTurnSpeed = 0;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			jump();
		}
	}

}
