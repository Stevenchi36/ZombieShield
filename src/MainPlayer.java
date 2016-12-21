import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * MainPlay class has all the attributes for the main playable character
 * @author Steven Childrey
 *
 */
public class MainPlayer extends Objects{

	private Image img;
	private Operator operator;
	private Controller control;
	private Sound sound;
	
	/**
	 * Creates the main player
	 * @param x position on x axis
	 * @param y position on y axis
	 * @param id character identification
	 */
	public MainPlayer(int x, int y, Identity id, Operator operator, Controller control) {
		
		super(x, y, id);
		this.operator = operator;
		this.control = control;
		
		try {
			img = ImageIO.read(new File("MainPlayer.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//xRate = 1;
		
		
	}

	/**
	 * Updates constantly.
	 * Deletes all players and changes game state when main player dies.
	 */
	public void tick() {
	
		x += xRate;
		y += yRate;
		
		x = Controller.bounds(x, 0, 850 - 52);
		y = Controller.bounds(y, 0, 530 - 75);
		
		collision();
		
		if(Display.HEALTH <= 0){
			operator.deleteAll();
			control.gameState = State.Died;
			sound = new Sound("GameOver.wav");
		}
		
	}
	
	/**
	 * Controls what happens when when collision is made with bullets or zombies.
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.NormalZombie || temp.getIdentity() == Identity.QuickZombie || temp.getIdentity() == Identity.SlowZombie || temp.getIdentity() == Identity.EnemyBullet){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					Display.HEALTH -= 1;
				}
			}
		}
	}
	
	/**
	 * Renders the main player constantly
	 */
	public void render(Graphics g) {
	
		g.drawImage(img, (int)x,(int) y, null);
		
	}
	
	/**
	 * Returns a rectangle in same position as EnemyBullet for collision purposes
	 */
	public Rectangle getBounds(){
		
		return new Rectangle((int)(x), (int)(y), 46, 46);
		
	}

}
