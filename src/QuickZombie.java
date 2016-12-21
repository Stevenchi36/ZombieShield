import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 
 * Controls the QuickZombie
 * @author Steven Childrey
 *
 */
public class QuickZombie extends Objects{

	private Image img;
	private Objects main;
	private Operator operator;
	private float health = 100;
	private float ranum;
	
	/**
	 * Adds QuickZombie
	 * @param x
	 * @param y
	 * @param id
	 * @param operator
	 */
	public QuickZombie(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		
		try {
			img = ImageIO.read(new File("QuickZombie.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < operator.players.size(); i++ ){
            if(operator.players.get(i).getIdentity() == Identity.MainPlayer) 
            	main = operator.players.get(i);
		}
		
		
		//Makes the speed of the QuickZombies a little bit different
		ranum = (float)Math.random();
		ranum = (Controller.bounds((float)ranum, (float).3, (float).6));
		
		
		
	}

	/**
	 * Updates constantly.
	 * Makes the QuickZombie follow the main player.
	 * Deletes itself when health hits 0.
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		float diffX = x - main.getX() - 13;
        float diffY = y - main.getY() - 13;
        float distance = (float) Math.sqrt((x-main.getX())*(x-main.getX())+(y-main.getY())*(y-main.getY()));
        if(main.getX() > getX())
        	xRate = (float)(((-1 / distance) * diffX) + ranum);
        else
        	xRate = (float)(((-1 / distance) * diffX) - ranum);
        if(main.getY() > getY())
        	yRate = (float)(((-1 / distance) * diffY) + ranum);
        else
        	yRate = (float)(((-1 / distance) * diffY) - ranum);
        
        collision();
        if(health < 1){
        	operator.deletePlayer(this);
        	Display.SCORE += 50;
        	Display.KILLED += 1;
        }
		
	}
	
	/**
	 * Lowers health when contact is made with bullets or the main player
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.EnemyBullet || temp.getIdentity() == Identity.FastEnemyBullet){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					health -= 50;
				}
			}
			if(temp.getIdentity() == Identity.MainPlayer){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					health -= 6.5;
				}
			}
		}
	}

	/**
	 *Constantly renders the QuickZombie
	 */
	public void render(Graphics g) {
		
		g.drawImage(img, (int)x, (int)y, null);
		
	}
	
	/**
	 * Returns a rectangle in same position as EnemyBullet for collision purposes
	 */
	public Rectangle getBounds(){
		
		return new Rectangle((int)(x), (int)(y), 30, 30);
		
	}
	
}
