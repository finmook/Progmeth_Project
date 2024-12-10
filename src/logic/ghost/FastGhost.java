package logic.ghost;

import game.Model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class FastGhost extends Ghost{
	private String classLoaderPath;
	private Image fastGhost;
	private int speed;
	private boolean potionDeath=false;
	private boolean swordDeath= false;
	
	public FastGhost() {
		classLoaderPath = ClassLoader.getSystemResource("fastghost.png").toString();
		fastGhost = new Image(classLoaderPath);
		speed= 6;
	}

	@Override
	public void drawGhost(GraphicsContext gc, int x, int y, double w,double h) {
		// TODO Auto-generated method stub
		gc.drawImage(fastGhost, x, y, w, h);
	}


	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}
	public void setSpeed(int speed) {
		this.speed=speed;
	}

	@Override
	public boolean isPOTIONDEATH() {
		// TODO Auto-generated method stub
		return this.potionDeath;
	}

	@Override
	public boolean isSWORDDEATH() {
		// TODO Auto-generated method stub
		return this.swordDeath;
	}

	@Override
	public void setPotionDeath(boolean potionDeath) {
		// TODO Auto-generated method stub
		this.potionDeath=potionDeath;
		
	}

	@Override
	public void setSwordDeath(boolean swordDeath) {
		// TODO Auto-generated method stub
		this.swordDeath=swordDeath;
	}
	
	

}
