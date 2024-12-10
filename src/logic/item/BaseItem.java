package logic.item;

import javafx.scene.canvas.GraphicsContext;

public abstract class BaseItem {
	public abstract void drawItem(GraphicsContext gc,int x,int y,double width,double height);
	
}
