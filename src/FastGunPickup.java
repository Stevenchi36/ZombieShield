import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Controls the pickup to get a faster shooter
 * @author Steven Childrey
 *
 */
public class FastGunPickup extends Objects{

	private Image img;
	private Operator operator;
	private long startTime;
	
	/**
	 * Creates the FastGunPuckup
	 * @param x position
	 * @param y position
	 * @param id
	 * @param operator
	 */
	public FastGunPickup(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		startTime = System.currentTimeMillis();
		
		try {
			img = ImageIO.read(new File("FastEnemyBullet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Updates constantly.
	 * Deletes if not picked up after 7 seconds
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		
		collision();
		
		if(startTime + 7000 < System.currentTimeMillis()){
			operator.deletePlayer(this);
		}
	}
	
	
	/**
	 * Creates new FastHumanEnemy if collision is detected with the main player
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.MainPlayer){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					operator.newPlayer(new FastHumanEnemy(798, 0, Identity.FastHumanEnemy, operator));
					operator.deletePlayer(this);
				}
			}
		}
	}

	/**
	 * Renders the FastGunPickup constantly
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
