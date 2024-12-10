package sharedObject;

import java.net.URL;


import javafx.scene.canvas.Canvas;


import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RenderableHolder {
		private static final RenderableHolder instance = new RenderableHolder();
	 	private static Image heart;
	    private static Image up,left,right,down;
	    private static Image background;
	    private static Image tree,tangmo;
	    private static MediaPlayer crashSound;
	    private static MediaPlayer loseSound;
	    private List<IRenderable> entities;
	    private Comparator<IRenderable> comparator;
	    public static Image mainmenuBackground,croppedMenuBackground,playButton,playButtonHover,mapButton1,mapButton2,mapButton3,pauseMenu,mapselectionMenu,mapBackButton;
	    public static Image[] state;
	    
	    private static MediaPlayer gameBackgroundMusic,menuBackgroundMusic,freeze,click,pickItem;
	    static {
	        
	        //loadAnimation();
	        loadMenuResource();
	        loadPauseResource();
	        loadMapselectionResource();
	           
	        //loadSound();
	        //loadEndResource();
	    }
	    public RenderableHolder() {
	        entities = new ArrayList<IRenderable>();
	        comparator = (IRenderable o1, IRenderable o2) -> {
	            if (o1.getZ() > o2.getZ()) {
	                return 1;
	            }
	            return -1;
	        };
	    }
	    public static RenderableHolder getInstance() {
	        return instance;
	    }
	    public static void loadMenuResource(){
	        playButton = new Image(ClassLoader.getSystemResource("playButton.png").toString());
	        playButtonHover = new Image(ClassLoader.getSystemResource("playButtonHover.png").toString());
	        mainmenuBackground = new Image(ClassLoader.getSystemResource("mainmenuBackground.png").toString());
	        croppedMenuBackground = new WritableImage(mainmenuBackground.getPixelReader(),0,0,360,400);
	        gameBackgroundMusic = setSoundByPath("gameBackgroundMusic.wav");
	        menuBackgroundMusic = setSoundByPath("menuBackgroundMusic.wav");
	        freeze = setSoundByPath("freeze.mp3");
	        click = setSoundByPath("click.mp3");
	        pickItem = setSoundByPath("pickItemSound.mp3");
	    }
	    public static void loadMapResource(){
	        mapButton1 = new Image(ClassLoader.getSystemResource("mapButton1.png").toString());
	        mapButton2 = new Image(ClassLoader.getSystemResource("mapButton2.png").toString());
	        mapButton3 = new Image(ClassLoader.getSystemResource("mapButton3.png").toString());
	    }
	    public static void loadPauseResource(){
	        pauseMenu = new Image(ClassLoader.getSystemResource("game-background.jpg").toString());
	        
	    }
	    public static void loadMapselectionResource(){
	    	mapselectionMenu = new Image(ClassLoader.getSystemResource("mapselectedMenu.png").toString());
	        mapBackButton = new Image(ClassLoader.getSystemResource("backButton.png").toString());
	    }

	    public static Image setImageByPath(String imagePath) {
	        try {
	            String classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
	            return new Image(classLoaderPath);
	        } catch (Exception e) {
	            String classLoaderPath = ClassLoader.getSystemResource("heart.png").toString();
	            return new Image(classLoaderPath);
	        }
	    }
	    
	    public  static MediaPlayer setSoundByPath(String soundPath) {
	        URL soundUrl = ClassLoader.getSystemResource(soundPath);
	        if (soundUrl == null) {
	            // Log or print the error message for missing resource
	            System.out.println("Sound not found: " + soundPath + ", using default sound.");
	            soundUrl = ClassLoader.getSystemResource("crash.wav");  // Fallback to default sound
	        }
	        Media media= new Media(soundUrl.toString());
	        return new MediaPlayer(media);  // Use the URL directly
	    }
	    
	    public void add(IRenderable entity) {
	        entities.add(entity);
	        Collections.sort(entities, comparator);
	    }

	    public void update() {
	        for (int i = entities.size() - 1; i >= 0; i--) {
	            if (entities.get(i).isDestroyed())
	                entities.remove(i);
	        }
	    }

	    public List<IRenderable> getEntities() {
	        return entities;
	    }

	    		
	    public static void loadResources() {
	    	down = setImageByPath("down.gif");
	    	up = setImageByPath("up.gif");
	    	left = setImageByPath("left.gif");
	    	right = setImageByPath("right.gif");
	        heart = setImageByPath("heart.png");
	        tree = setImageByPath("tree.png");
	        background = setImageByPath("game-background.jpg");
	        tangmo =setImageByPath("tangmo.png");
	        crashSound= setSoundByPath("crash.wav");
	        loseSound= setSoundByPath("lose.wav");
	        
	    }
	    public static Image getHeart() {
	    	return heart;
	    }
	    
	    public static Image getUp() {
	    	return up;
	    }
	    public static Image getLeft() {
	    	return left;
	    }
	    public static Image getRight() {
	    	return right;
	    }
	    public static Image getDown() {
	    	return down;
	    }
	    public static Image getBackground() {
	    	return background;
	    }
	    public static Image getTree() {
	    	return tree;
	    }
	    public static Image getTangmo() {
	    	return tangmo;
	    }
	    public static MediaPlayer getCrash() {
	    	return crashSound;
	    }
	    public static MediaPlayer getLose() {
	    	return loseSound;
	    }
	    public static MediaPlayer getGameBackgroundMusic() {
	    	return gameBackgroundMusic;
	    }
	    public static MediaPlayer getMenuBackgroundMusic() {
	    	return menuBackgroundMusic;
	    }
	    public static MediaPlayer getFreeze() {
	    	return freeze;
	    }
	    public static MediaPlayer getClick() {
	    	return click;
	    }
	    public static MediaPlayer getPickItem() {
	    	return pickItem;
	    }
	    
	    
	    
	    
}
