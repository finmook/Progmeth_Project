package logic.ghost;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class StrongGhost extends Ghost{
	private String classLoaderPath;
	private Image strongGhost;
	private int speed=2;
	private boolean potionDeath=false;
	private boolean swordDeath= false;
	public StrongGhost() {
		classLoaderPath = ClassLoader.getSystemResource("wearwolf.png").toString();
		strongGhost = new Image(classLoaderPath);
	}
	public void drawGhost(GraphicsContext gc, int x, int y,double w,double h) {
    	gc.drawImage(strongGhost, x, y, w, h);
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
		this.potionDeath=false;
	}
	@Override
	public void setSwordDeath(boolean swordDeath) {
		// TODO Auto-generated method stub
		this.swordDeath=false;
	}
	
}
