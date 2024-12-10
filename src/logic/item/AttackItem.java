package logic.item;

import game.Model;
import item.usage.Attackable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AttackItem extends BaseItem implements Attackable{
	private String classLoaderPath = ClassLoader.getSystemResource("sword.png").toString();
	Image attackItem=new Image(classLoaderPath);
	private boolean isFight;
	public AttackItem() {
		
		setIsFight(false);
	}
	@Override
	public void drawItem(GraphicsContext gc, int x, int y, double width, double height) {
		// TODO Auto-generated method stub
		gc.drawImage(attackItem, x, y, width, height);
	}

	
	public void setIsFight(boolean isFight) {
		this.isFight=isFight;
	}
	
	public boolean isFIGHT() {
		return isFight;
	}
	
	
}
