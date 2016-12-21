import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;



/**
 * Controls the Human Enemy object
 * @author Steven Childrey
 *
 */
public class HumanEnemy extends Objects{

	Image img;
	private Objects main;
	Operator operator;
	int ranum = 0;
	Random random = new Random();
	
	/**
	 * Creates the HumanEnemy
	 * @param x position
	 * @param y position
	 * @param id
	 * @param operator
	 */
	public HumanEnemy(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		
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
	 * Updates constantly,
	 * Creates a EnemyBullet objects at a constant rate.
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		
		ranum++;
		if(ranum > 90){
			operator.newPlayer(new EnemyBullet((int)x + 21, (int)y + 21, Identity.EnemyBullet, operator));
			ranum = 0;
		}
	}

	/**
	 * Renders the Human Enemy constantly 
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
