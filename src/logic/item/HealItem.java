package logic.item;

import game.Model;
import item.usage.Healable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
public class HealItem extends BaseItem implements Healable {
	private final int RECOVER = 1;
	private String classLoaderPath = ClassLoader.getSystemResource("heart.png").toString();
	Image healItem=new Image(classLoaderPath);
	
	public HealItem() {
		
		
	}
	public void heal() {
		// TODO Auto-generated method stub
		Model.lives+=RECOVER;
	}
	@Override
	public void drawItem(GraphicsContext gc, int x, int y,double width,double height) {
		// TODO Auto-generated method stub
		gc.drawImage(healItem, x, y);
	}
	
	
	
	
}
