import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Controls the Normal Zombie object
 * @author Steven Childrey
 *
 */
public class NormalZombie extends Objects{

	private Image img;
	private Objects main;
	private Operator operator;
	private int health = 100;
	
	/**
	 * Creates the Normal Zombie
	 * @param x position
	 * @param y position
	 * @param id
	 * @param operator
	 */
	public NormalZombie(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		
		try {
			img = ImageIO.read(new File("NormalZombie.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < operator.players.size(); i++ ){
            if(operator.players.get(i).getIdentity() == Identity.MainPlayer) 
            	main = operator.players.get(i);
		}
		
		//Keeps object from spawing on top of Main player
		this.x = Controller.getPlayerBounds(x, x - 50, x + 50, main.getX());
		this.y = Controller.getPlayerBounds(y, y - 50, y + 50, main.getY());

	}

	/**
	 * Updates constantly.
	 * Makes the zombie follow the main player.
	 * Also deletes the player when health gets to 0.
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
        	operator.deletePlayer(this);
        	Display.SCORE += 50;
        	Display.KILLED += 1;
        }
		
	}

	/**
	 *Constantly renders the normal zombie
	 */
	public void render(Graphics g) {
		
		g.drawImage(img, (int)x, (int)y, null);
		
	}
	
	/**
	 * Checks for collision against bullets and the main player then lowers the health.
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.EnemyBullet || temp.getIdentity() == Identity.FastEnemyBullet){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					health -= 12;
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
