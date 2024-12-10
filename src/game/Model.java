package game;

import java.net.URL;
import java.util.ArrayList;
import static game.Main.getMainStage;


import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import javafx.geometry.Dimension2D;
import sharedObject.RenderableHolder;
import logic.ghost.*;
import logic.item.AttackItem;
import logic.item.HealItem;
import logic.item.SlowItem;
import logic.item.SpecialItem;
import javafx.scene.media.MediaPlayer;

public class Model extends Canvas {
	
	private Dimension2D d;
    private final Font smallFont = Font.font("Book Antiqua",FontWeight.BOLD, 14);
    private boolean inGame = false;
    private boolean dying = false;
    private boolean healUse = true;
    private boolean slowUse = true;
    private boolean attackAppear = true;
    private boolean specialAppear = true;
    private boolean gameOver = false;
    private boolean isPaused = false;
    
    private final int CANVAS_WIDTH = 360;
    private final int CANVAS_HEIGHT = 400;
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 6;
    private final int FRAME_WIDTH = 24;
    private final int FRAME_HEIGHT= 24;
    private final int FRAME_COUNT = 4;
    

    public static int N_GHOSTS = 6;
    public static ArrayList<Ghost> ghosts=new ArrayList<Ghost>();
    private int score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy;
    public static int[] ghostSpeed;
    public static int lives;
    

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;
    
    private PauseScreen pauseScreen = new PauseScreen(CANVAS_WIDTH, CANVAS_HEIGHT); 
    private short[] levelData;
   
//    
//    private final short levelData1[] = {
//        	19, 18, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 26, 18, 22,
//            17, 20,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0, 17, 20,
//            17, 20,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0, 17, 20,
//            17 ,16, 18, 18, 18, 18,  18, 16, 18, 18, 18, 18, 18, 16, 20,
//            17, 16, 16, 16, 16, 16,  24, 24, 24, 16, 16, 16, 16, 16, 20,
//            17, 16, 16, 16, 16, 20,   0,  0,  0, 17, 16, 16, 16, 16, 20,
//            17, 16, 16, 128, 16, 20,   0, 263,  0, 17, 16, 64, 16, 16, 20,
//            17, 16, 16, 16, 16, 20,   0, 21,  0, 17, 16, 16, 16, 16, 20,
//            17, 16, 16, 16, 16, 20,   0, 21,  0, 17, 16, 16, 16, 16, 20,
//            17, 16, 24, 16, 16, 20,   0, 21,  0, 17, 16, 16, 16, 16, 20,
//            17, 20,  0, 17, 16, 16,  18, 16, 18, 16, 16, 16, 24, 16, 20,
//            17, 20,  0, 17, 16, 16,  16, 16, 16, 16, 16, 20,  0, 17, 20,
//            17, 20,  0, 25, 24, 24,  24, 24, 24, 24, 24, 28,  0, 17, 20,
//            17, 20,  0,  0,  0,  0,   0,  0,  0,  0,  0,  0,  0, 17, 20,
//            25, 24, 26, 26,  26,26,  26, 42, 26, 26, 26, 26, 26, 24, 28
//        };
//    
//    
//    
//    private final short levelData2[] = {
//        	19, 26, 26, 26, 26, 26, 26, 18, 26, 26, 26, 26, 26, 26, 22,
//            21,  0,  0,  0,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0, 21,
//            17, 18, 22,  0, 35, 18, 18, 16, 18, 18, 22,  0, 259, 18, 20,
//            17, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 20,
//            17, 16, 20,  0, 17, 16, 16, 24, 16, 16, 20,   0, 17,16, 20,
//            17, 16, 20,  0, 17, 16, 20,  0, 17, 16, 20,   0, 17, 16, 20,
//            17, 16, 16, 18, 16, 16, 20,  0, 17, 16, 16, 18, 16, 16, 20,
//            17, 16, 16, 16, 16, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,
//            17, 16, 16, 24, 16, 16, 20,  0, 17, 16, 16, 24, 16, 16, 20,
//            17, 16, 20,  0, 17, 16, 20,  0, 17, 16, 20,  0, 17, 16,  20,
//            17, 16, 20,  0, 17, 16, 16, 18, 16, 16, 20,  0, 17, 16,  20,
//            17, 16, 20,  0, 17, 16, 16, 16, 16, 16, 20,  0, 17, 16,  20,
//            17, 24, 76,  0, 25, 24, 24, 16, 24, 24, 140,  0, 25, 24,  20,
//            21, 0,  0,   0,  0,  0,  0, 21,  0,  0,  0,  0,  0,  0,  21,
//            25, 26, 26, 26, 26, 26, 26, 24, 26, 26, 26, 26, 26, 26, 28
//        };
    
    
    
    HealItem healItem = new HealItem();
    SlowItem slowItem = new SlowItem();
    AttackItem attackItem= new AttackItem();
    SpecialItem specialItem = new SpecialItem();
//    public static final int validSpeeds[] = {1, 2, 3, 4, 6, 7};
    private final int maxSpeed = 6;
    
    private int currentFrame = 0;
    private long lastUpdate = 0;
    public static int currentSpeed = 3;
    private short[] screenData;
    private boolean gameLoopRunning = false;
    
    AnimationTimer gameLoop;

    public void startGameLoop(Scene scene,GraphicsContext gc) {
    		
    		RenderableHolder.loadResources();
    		initVariables();
    		setFocusTraversable(true);
    		initGame();
    		renderGame(gc);
    		Thread t=new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	
					@Override
					public void handle(KeyEvent e) {
						// TODO Auto-generated method stub
						handleKeyPressed(e);
					}
	    			
	    		});
				}
    			
    		}) ;
    		t.start();
    			
    		
    		
    		
    		gameLoop = new AnimationTimer() {
    		    @Override
    		    public void handle(long now) {
    		    	if(gameOver) {
    		    		showGameOverScreen(gc);
    		    	}
    		    	else if(isPaused) {
    		    		pauseScreen.render(gc);
    		    	}
    		    	else if (inGame) {
    		            renderGame(gc); // Render the game    
    		        } else {
    		            showIntroScreen(gc);
    		            setGameLoopRunning(false);
    		        }
    		    }
    		};
    		gameLoop.start();
    }
    
    public void setGameLoopRunning(boolean status) {
    	this.gameLoopRunning = status;
    }
    
    
    
    public int getCanvasWidth() {
    	return CANVAS_WIDTH;
    }
    
    public int getCanvasHeight() {
    	return CANVAS_HEIGHT;
    }
    
    public int getCurrentFrame() {
    	return this.currentFrame;
    }
    
    public long getLastUpdate() {
    	return this.lastUpdate;
    }

    public boolean isGAMELOOPRUNNING() {
    	return gameLoopRunning;
    }
			
    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension2D(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];
        

    }

    private void playGame(GraphicsContext gc) {

        if (dying) {

            death();

        } else {

            movePacman();
            drawPacman(gc);
            moveGhosts(gc);
            checkMaze();
        }
    }

    private void showIntroScreen(GraphicsContext gc) {
    	
    	String start = "Press SPACE to start";
    	
        gc.setFill(Color.CYAN);
        gc.fillText(start, (SCREEN_SIZE)/3, (SCREEN_SIZE)/2);
    }

    private void drawScore(GraphicsContext gc) {
        gc.setFont(smallFont);
        gc.setFill(new Color(0.0196, 0.7098, 0.3098, 1.0));
        String s = "Score: " + score;
        gc.fillText(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) {
            gc.drawImage(RenderableHolder.getHeart(), i * 28 + 8, SCREEN_SIZE + 1);
        }
    }

    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) {

            score += 50;

            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel();
        }
    }

    private void death() {

    	lives--;
    	
    	
        if (lives == 0) {
        	RenderableHolder.getLose().stop();
        	RenderableHolder.getCrash().stop();
        	
        	RenderableHolder.getLose().setVolume(1.0);
        	RenderableHolder.getLose().seek(Duration.ZERO);
        	RenderableHolder.getLose().play();
			
        	
            inGame = false;
            gameOver= true;
            slowUse= true;
            healUse= true;
            attackAppear= true;
            specialAppear= true;
            attackItem.setIsFight(false);
            specialItem.setIsFight(false);
            N_GHOSTS=6;
            ghosts.clear();
        }

        continueLevel();
    }

    private void moveGhosts(GraphicsContext gc) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) {
        	
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }
            if(!(ghosts.get(i).isPOTIONDEATH() || ghosts.get(i).isSWORDDEATH())) {
            	ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            	ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            	ghosts.get(i).drawGhost(gc, ghost_x[i] + 1, ghost_y[i] + 1,BLOCK_SIZE,BLOCK_SIZE);

            }

            
            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {
            	if((attackItem.isFIGHT()) &&  !(ghosts.get(i) instanceof StrongGhost)) {

            		ghost_x[i]=-1000;
            		ghost_y[i]=-1000;
            		ghosts.get(i).setSpeed(0);
            		ghosts.get(i).setSwordDeath(true);
            		


            		attackItem.setIsFight(false);
            		

            		
            	}
            	else if((specialItem.isFIGHT()) &&  !(ghosts.get(i) instanceof StrongGhost)) {
            		ghost_x[i]=-1000;
            		ghost_y[i]=-1000;
            		ghosts.get(i).setSpeed(0);
            		ghosts.get(i).setPotionDeath(true);
            		specialItem.setIsFight(false);
            	}
            	else {
            		
            		RenderableHolder.getCrash().stop();
					RenderableHolder.getLose().stop();
					
					RenderableHolder.getCrash().setVolume(1.0);
					RenderableHolder.getCrash().seek(Duration.ZERO);
					RenderableHolder.getCrash().play();
					
					
            		
            		dying=true;
            	}
            	

               
            }
        }
    }


    private void movePacman() {

        int pos;
        short ch;

        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }
            
            if ((ch & 32) != 0 && healUse) {
            	screenData[pos] = (short) (ch & 31);
            	healItem.heal();
            	healUse = false;
            }
            
            if((ch & 64)!= 0 && slowUse) {
            	screenData[pos] = (short) (ch & 63);
            	slowItem.slowGhosts();
            	slowUse = false;
            }
            
            if((ch & 128)!= 0 && attackAppear) {
            	screenData[pos] = (short) (ch & 127);
            	attackItem.setIsFight(true);
            	attackAppear = false;
            }
            
            if((ch & 256)!= 0 && specialAppear) {
            	screenData[pos] = (short) (ch & 255);
            	specialItem.setIsFight(true);
            	specialItem.heal();
            	specialAppear = false;
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        } 
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    private void drawPacman(GraphicsContext gc) {
    	
        if (req_dx == -1) {
        	gc.drawImage(RenderableHolder.getLeft(), pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        } else if (req_dx == 1) {
        	gc.drawImage(RenderableHolder.getRight(), pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        } else if (req_dy == -1) {
        	gc.drawImage(RenderableHolder.getUp(), pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        } else {
        	gc.drawImage(RenderableHolder.getDown(), pacman_x + 1, pacman_y + 1, BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    private void drawMaze(GraphicsContext gc) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

//                gc.setFill(new Color(0,0.2824,0.9843,1));
//                gc.setLineWidth(5.0);
                
                if ((levelData[i] == 0)) { 
//                	gc.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                	gc.drawImage(RenderableHolder.getTree(), x, y, BLOCK_SIZE, BLOCK_SIZE );
                 }

                if ((screenData[i] & 1) != 0) { 
                    gc.strokeLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    gc.strokeLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    gc.strokeLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    gc.strokeLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 

                	gc.drawImage(RenderableHolder.getTangmo(), x, y, BLOCK_SIZE/2, BLOCK_SIZE/2);
               }
                if ((screenData[i] & 32) != 0 && healUse) {
            		healItem.drawItem(gc, x,y,0,0);
                }
                if((screenData[i] & 64)!= 0 && slowUse ) {
                	slowItem.drawItem(gc, x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                if((screenData[i] & 128)!= 0 && attackAppear ) {
                	attackItem.drawItem(gc, x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                if((screenData[i] & 256)!= 0 && specialAppear ) {
                	specialItem.drawItem(gc, x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                

                i++;
            }
        }
    }

    private void initGame() {

    	lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;
    }

    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    private void continueLevel() {

    	int dx = 1;

    		
    		for (int i = 0; i < N_GHOSTS; i ++) {
    			
    			if(i==0 && (lives==0 || lives==3)) ghosts.add(new StrongGhost());
    			else if((i==1||i==2)&& (lives==0 || lives==3)) ghosts.add(new FastGhost());
    			else if(lives==0||lives==3) {
    				ghosts.add(new NormalGhost());
    			}
    			ghost_y[i] = 4 * BLOCK_SIZE; //start position
    			ghost_x[i] = 4 * BLOCK_SIZE;
    			ghost_dy[i] = 0;
    			ghost_dx[i] = dx;
    			dx = -dx;

         
    			
    			ghostSpeed[i] = ghosts.get(i).getSpeed();
    			
    			
    		}

        
        pacman_x = 7 * BLOCK_SIZE;  //start position
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        dying = false;
    }
    
    public Dimension2D getD() {
    	return d;
    }

 
    public void renderGame(GraphicsContext gc) {
        

        


    	gc.drawImage(RenderableHolder.getBackground(), 0, 0, getCanvasWidth(),getCanvasHeight() );
        drawMaze(gc);
        drawScore(gc);

        if (inGame) {
            playGame(gc);
        } else {
            showIntroScreen(gc);
        }


    }
    
    private void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop(); // Stop the AnimationTimer
        }
        requestFocus();
    }
    
    private void resumeGameLoop() {
        if (gameLoop != null) {
            gameLoop.start(); // Resume the AnimationTimer
        }
        requestFocus();
    }
    
    public void handleKeyPressed(KeyEvent event) {
    	KeyCode key = event.getCode();

        // Handle game over state
        if (gameOver) {
            if (key == KeyCode.R) {
                gameOver = false;
                inGame = true;
                initGame(); // Restart the game
            } else if (key == KeyCode.Q) {
            	PageChanger.changeToMapSelection(getMainStage());
            }
            return; // Exit after handling game over state
        }

        // Handle pause and resume
        else if (key == KeyCode.E && inGame) {
            if (isPaused) {
                resumeGameLoop(); // Resume the game
                isPaused = false;
            } else {
                
                isPaused = true;
            }
            return; // Exit after handling pause/resume
        }

        if (isPaused) {
            if (key == KeyCode.E) {
                resumeGameLoop(); // Resume the game
                isPaused = false;
                System.out.println("Game Resumed");
            } else if (key == KeyCode.R) {
            	// Stop the current game loop
                stopGameLoop();
                // Reinitialize game state
                inGame = true;
                isPaused = false;
                gameOver = false;
                initGame(); // Reset variables and level
                // Restart the game loop
                gameLoop.start();
                System.out.println("Game Restarted");
            } else if (key == KeyCode.Q) {
            	this.gameOver = true;

            }
            return; // Exit after handling paused state
        }

        // Handle in-game controls
        else if (inGame) {
            
            if (key == KeyCode.A) {
              req_dx = -1;
              req_dy = 0;
          } else if (key == KeyCode.D) {
              req_dx = 1;
              req_dy = 0;
          } else if (key == KeyCode.W) {
              req_dx = 0;
              req_dy = -1;
          } else if (key == KeyCode.S) {
              req_dx = 0;
              req_dy = 1;
          } else if (key == KeyCode.ESCAPE && isGAMELOOPRUNNING()) {
              inGame = false;
          } 
            }
        

        // Handle game start from intro screen
        else if (!inGame && key == KeyCode.SPACE) {
            inGame = true;
            initGame();
        }
        }
    
    /////////////////////////////////////////////////////////////////////////////////////////////
    
    private void showGameOverScreen(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, getCanvasWidth(), getCanvasHeight());

        String gameOverText = "GAME OVER";
        String scoreText = "Your Score: " + score;
        String restartText = "Press R to Restart or Q to Quit";
        

        gc.setFill(Color.RED);
        gc.setFont(Font.font("Book Antiqua", FontWeight.BOLD, 36));
        gc.fillText(gameOverText, SCREEN_SIZE / 5.25, SCREEN_SIZE / 3);

        gc.setFont(Font.font("Book Antiqua", FontWeight.NORMAL, 24));
        gc.setFill(Color.WHITE);
        gc.fillText(scoreText, SCREEN_SIZE / 3.25, SCREEN_SIZE / 2);
        gc.fillText(restartText, SCREEN_SIZE / 16, SCREEN_SIZE / 1.5);
        
    }
    public void setLevelData(short[] levelData) {
        this.levelData = levelData;
    }
    
    
    
    
    
    
    
    
    
	}


    //controls
   

    

	
   
    
		
	