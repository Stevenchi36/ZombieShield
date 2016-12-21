import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Controls everything that happens in the program
 * @author Steven Childrey
 *
 */
public class Controller extends Canvas implements Runnable{

	private static final long serialVersionUID = 437875076891992403L;
	
	private Thread thread;
	private boolean isRun = false;
	private final int W = 850, H = 530;
	private Display display;
	private Image img, img2, img3, img4, img5;
	private Operator operator;
	private Random r = new Random();
	private Generator gen;
	private Menu menu;
	private Help help;
	private Died died;
	private Win win;
	public static State gameState = State.Menu;

	/**
	 * Controller is the main controller for the program
	 */
	public Controller(){
		
		//Import picture before creating an instance of GUI.
		try {
			img = ImageIO.read(new File("RedBG.jpg"));
			img2 = ImageIO.read(new File("Menu.jpg"));
			img3 = ImageIO.read(new File("Help.jpg"));
			img4 = ImageIO.read(new File("Died.jpg"));
			img5 = ImageIO.read(new File("Win.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}	
		//Create instance of GUI which will also call start()
		operator = new Operator();
		display = new Display();
		menu = new Menu(this, operator, display);
		help = new Help(this, operator);
		died = new Died(this, operator, display);
		win = new Win(this, operator, display);
		//Add key and mouse listeners
		this.addKeyListener(new Keyboard(operator));
		this.addMouseListener(menu);
		this.addMouseListener(help);
		this.addMouseListener(died);
		this.addMouseListener(win);
		GUI gui = new GUI(W, H, "Zombie Guards", this);
		gen = new Generator(operator, display, this);
		
		if(gameState == State.Game){
			operator.newPlayer(new MainPlayer(W / 2 - 46, H / 2 - 46, Identity.MainPlayer, operator, this));
			operator.newPlayer(new HumanEnemy(0, 0, Identity.HumanEnemy, operator));			
		}

	}
	
	/**
	 * start starts the thread and sets isRun to true
	 */
	public synchronized void start(){
		
		thread = new Thread(this);
		thread.start();
		isRun = true;
		
	}
	
	/**
	 * stop kills the thread and sets isRun to false
	 */
	public synchronized void stop(){
		
		try{
			thread.join();
			isRun = false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/* 
	 * Run is the game loop that makes the entire game run
	 * popular loop that many people use
	 */
	public void run(){
		
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRun){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(isRun)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	/**
	 * tick method calls all other ticks depending on game State
	 */
	private void tick(){
		
		operator.tick();
		if(gameState == State.Game){
			display.tick();
			gen.tick();			
		}
		else if(gameState == State.Menu){
			menu.tick();
		}
		else if(gameState == State.Help){
			help.tick();
		}
		else if(gameState == State.Died){
			died.tick();
		}
		else if(gameState == State.Win){
			win.tick();
		}
		
	}
	
	/**
	 * render method takes care of the backgrounds and also calls other render methods
	 * depending on the game's state
	 */
	private void render(){
		
		BufferStrategy buff = this.getBufferStrategy();
		if(buff == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buff.getDrawGraphics();
		
		if(gameState == State.Game){
			g.drawImage(img, 0, 0, null);
			operator.render(g);
			display.render(g);
		}
		else if(gameState == State.Menu){
			g.drawImage(img2, 0, 0, null);
			menu.render(g);
		}
		else if(gameState == State.Help){
			g.drawImage(img3, 0, 0, null);
			help.render(g);
		}
		else if(gameState == State.Died){
			g.drawImage(img4, 0, 0, null);
			died.render(g);
		}
		else if(gameState == State.Win){
			g.drawImage(img5, 0, 0, null);
			win.render(g);
		}

		g.dispose();
		buff.show();
	}
	
	/**
	 * Limits a number to a certain range
	 * @param num is the number being tested
	 * @param min is the minimum number in the range
	 * @param max is the maximum number in the range
	 * @return a float within the range
	 */
	public static float bounds(float num, float min, float max){
		
		if( num >= max)
			return num = max;
		else if(num <= min)
			return num = min;
		else
			return num;
				
	}	
	
	/**
	 * Makes sure enemies do not spawn on top of MainPlayer
	 * Does not apply to QuickZombie
	 * @param enemyPos is the postion of enemy
	 * @param min is the minimum position to be spawned
	 * @param max is the maximum postition to be spawned
	 * @param playerPos is the position of the player
	 * @return float a certain distance from the MainPlayer
	 */
	public static float getPlayerBounds(float enemyPos, float min, float max, float playerPos){
		
		if(enemyPos <= max && enemyPos >= playerPos)
			return max + 30;
		else if(enemyPos >= min && enemyPos <= playerPos)
			return min - 30;
		else
			return enemyPos;
	}
	
}
