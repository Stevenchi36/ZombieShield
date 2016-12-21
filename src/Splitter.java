import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Controls the Splitter
 * @author Steven Childrey
 *
 */
public class Splitter extends Objects{

	private Image img;
	private Objects main;
	private Operator operator;
	private int health = 100;
	private int ranum;
	private Random r = new Random();
	
	/**
	 * Sends Location to Objects Class. Also adds in the picture
	 * @param x
	 * @param y
	 * @param id
	 * @param operator
	 */
	public Splitter(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		
		try {
			img = ImageIO.read(new File("Splitter.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < operator.players.size(); i++ ){
            if(operator.players.get(i).getIdentity() == Identity.MainPlayer) 
            	main = operator.players.get(i);
		}
		
		//Keeps the Splitter from spawning on the main player
		this.x = Controller.getPlayerBounds(x, x - 50, x + 50, main.getX());
		this.y = Controller.getPlayerBounds(y, y - 50, y + 50, main.getY());

	}

	/**
	 * Updates constantly.
	 * Deletes when outside of frame and when health gets to 0
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		float diffX = x - main.getX();
        float diffY = y - main.getY();
        float distance = (float) Math.sqrt((x-main.getX())*(x-main.getX())+(y-main.getY())*(y-main.getY()));
        xRate = ((-1 / distance) * diffX);
        yRate = ((-1 / distance) * diffY);
        
        collision();
        if(health < 1){
    		operator.newPlayer(new QuickZombie((int)(getX() - 30 + 10), (int)(getY() + 30 + 10), Identity.QuickZombie, operator));  //(((main.getX() - 50) + 1) + (main.getX() + 50)))
    		operator.newPlayer(new QuickZombie((int)(getX() + 30 + 10), (int)(getY() - 30 + 10), Identity.QuickZombie, operator));
        	operator.deletePlayer(this);
        	Display.SCORE += 50;
        	Display.KILLED += 1;
        }
		
	}

	/**
	 * Constantly renders the Splitter
	 */
	public void render(Graphics g) {
		
		g.drawImage(img, (int)x, (int)y, null);
		
	}
	
	/**
	 * Checks for contact with bullets and the main player.
	 * If contact is made, the health will lower.
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.EnemyBullet || temp.getIdentity() == Identity.FastEnemyBullet){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					health -= 16;
				}
			}
			if(temp.getIdentity() == Identity.MainPlayer){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					health -= 3.5;
				}
			}
		}
	}
	
	/**
	 * Returns a rectangle in same position as EnemyBullet for collision purposes
	 */
	public Rectangle getBounds(){
		
		return new Rectangle((int)(x), (int)(y), 46, 46);
		
	}
	
}
