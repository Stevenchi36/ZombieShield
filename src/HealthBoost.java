import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


/**
 * Controls the 25 Health Boost pickup
 * @author Steven Childrey
 *
 */
public class HealthBoost extends Objects{

	private Image img;
	private Objects main;
	private Operator operator;
	private int ranum = 0;
	private Random random = new Random();
	private long startTime;
	
	/**
	 * Creates the 25 health boost pickup
	 * @param x position
	 * @param y position
	 * @param id
	 * @param operator
	 */
	public HealthBoost(int x, int y, Identity id, Operator operator) {
		
		super(x, y, id);
		this.operator = operator;
		startTime = System.currentTimeMillis();
		
		try {
			img = ImageIO.read(new File("HealthBoost.png"));
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
	 * Updates constantly
	 * Checks for collision and deletes itself after 8 seconds
	 *
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		
		collision();
		
		if(startTime + 8000 < System.currentTimeMillis()){
			operator.deletePlayer(this);
		}
	}
	
	/**
	 * Increases health and deletes itself if contact is made with the main player
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.MainPlayer){
				if(getBounds().intersects(temp.getBounds())){
					Display.HEALTH += 25;
					operator.deletePlayer(this);
				}
			}
		}
	}

	/**
	 *Renders the Health Boost constantly
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
