package logic.ghost;

import game.Model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NormalGhost extends Ghost{
	private String classLoaderPath;
	private Image normalGhost;
	protected int speed=2;
    private boolean potionDeath=false;
    private boolean swordDeath= false;
	
	public NormalGhost() {
		classLoaderPath = ClassLoader.getSystemResource("normalghost.png").toString();
		normalGhost = new Image(classLoaderPath);
	}
	
	public void drawGhost(GraphicsContext gc, int x, int y,double w,double h) {
    	gc.drawImage(normalGhost, x, y, w, h);
    }

	@Override
	public int getSpeed() {
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
