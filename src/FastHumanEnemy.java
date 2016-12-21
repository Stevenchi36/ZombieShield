import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * Controls the FastHumanEnemy Object
 * @author Steven Childrey
 *
 */
public class FastHumanEnemy extends Objects{

	private Image img;
	private Objects main;
	private Operator operator;
	private int ranum = 0;
	private long startTime;
	
	/**
	 * Creates the FastHumanEnemy
	 * @param x position
	 * @param y position
	 * @param id
	 * @param operator
	 */
	public FastHumanEnemy(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		startTime = System.currentTimeMillis();
		
		try {
			img = ImageIO.read(new File("HumanEnemy.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < operator.players.size(); i++ ){
            if(operator.players.get(i).getIdentity() == Identity.MainPlayer)
            	main = operator.players.get(i);
		}
	}

	/**
	 * Updates constantly.
	 * Creates a FastEnemyBullet every few seconds.
	 * Deletes player after 30 seconds
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		
		ranum++;
		if(ranum > 50){
			operator.newPlayer(new FastEnemyBullet((int)x + 21, (int)y + 21, Identity.EnemyBullet, operator));
			ranum = 0;
		}
		
		if(startTime + 30000 < System.currentTimeMillis()){
			operator.deletePlayer(this);
		}
	}

	/**
	 * Renders the FastHumanEnemy constantly
	 */
	public void render(Graphics g) {
		
		g.drawImage(img, (int)x, (int)y, null);
		
	}

	/**
	 * Returns a rectangle in same position as EnemyBullet for collision purposes
	 */
	public Rectangle getBounds(){
		
		return new Rectangle((int)(x), (int)(y), 46, 46);
		
	}
	
}
