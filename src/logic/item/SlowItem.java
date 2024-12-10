package logic.item;

import game.Model;
import item.usage.Slowable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import logic.ghost.StrongGhost;

public class SlowItem extends BaseItem implements Slowable{
	private String classLoaderPath = ClassLoader.getSystemResource("ice.png").toString();
	Image slowItem=new Image(classLoaderPath);
	public SlowItem() {
		
	}
	@Override
	public void drawItem(GraphicsContext gc, int x, int y,double width,double height) {
		// TODO Auto-generated method stub
		gc.drawImage(slowItem, x, y, width, height);
		
	}

	@Override
	public void slowGhosts() {
		// TODO Auto-generated method stub
		for(int i=0;i<Model.N_GHOSTS;i++) {
			if(!(Model.ghosts.get(i) instanceof StrongGhost))
			Model.ghostSpeed[i]-=4;
			if(Model.ghostSpeed[i]<=0) {
				Model.ghostSpeed[i]=1;
			}
		}
		
	}
	
	
	
}
