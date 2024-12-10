package logic.item;

import game.Model;
import item.usage.Attackable;
import item.usage.Healable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpecialItem extends BaseItem implements Healable,Attackable {
	private final int RECOVER = 1;
	private boolean isFight;
	private String classLoaderPath = ClassLoader.getSystemResource("superpotion.png").toString();
	Image superPotion=new Image(classLoaderPath);
	@Override
	public void drawItem(GraphicsContext gc, int x, int y, double width, double height) {
		// TODO Auto-generated method stub
		gc.drawImage(superPotion, x, y, width, height);
	}

	
	@Override
	public void heal() {
		// TODO Auto-generated method stub
		Model.lives+=RECOVER;
	}


	@Override
	public void setIsFight(boolean isFight) {
		// TODO Auto-generated method stub
		this.isFight=isFight;
	}


	@Override
	public boolean isFIGHT() {
		// TODO Auto-generated method stub
		return isFight;
	}

}
