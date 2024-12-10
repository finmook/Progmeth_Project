package logic.ghost;

import javafx.scene.canvas.GraphicsContext;

public abstract class Ghost {
	public Ghost() {};
	public abstract int getSpeed();
	public abstract void drawGhost(GraphicsContext gc, int x, int y,double w,double h);
    public abstract void setSpeed(int speed);
    public abstract boolean isPOTIONDEATH();
    public abstract boolean isSWORDDEATH();
    public abstract void setPotionDeath(boolean potionDeath);
    public abstract void setSwordDeath(boolean swordDeath);
    
}
