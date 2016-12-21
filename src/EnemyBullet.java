import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * Controls bullet objects that are released from HumanEnemy
 * @author Steven Childrey
 *
 */
public class EnemyBullet extends Objects{

	private Operator operator;
	private Objects main;
	private float mainX;
	private float mainY;
	private float length;
	private int health = 100;
	private Sound sound;
	
	/**
	 * Creates the Enemies Bullet
	 * @param x position
	 * @param y position
	 * @param id
	 */
	public EnemyBullet(int x, int y, Identity id, Operator operator) {
		super(x, y, id);
		this.operator = operator;
		
		for(int i = 0; i < operator.players.size(); i++ ){
            if(operator.players.get(i).getIdentity() == Identity.MainPlayer) 
            	main = operator.players.get(i);
		}
		
		//Makes the bullet go towards mainPlayer
		
		mainX = (int)main.getX() + 20;
		mainY = (int)main.getY() + 20;
		length = (float)(Math.sqrt((mainX - x) * (mainX - x) + (mainY - y) * (mainY - y)));
		xRate = (float)(((mainX - x) / length) * 1.5);
		yRate = (float)(((mainY - y) / length) * 1.5);
		
		//Plays sound as bullet is created
		sound = new Sound("BulletSound.wav");
		
	}

	/**
	 * Updates constantly.
	 * Deletes when outside of frame and when health gets to 0
	 */
	public void tick() {
		
		x += xRate;
		y += yRate;
		
		if(y < 0 || y > 530 || x < 0 || x > 850)
			operator.deletePlayer(this);
		
		collision();
		
		if(health < 1){
			operator.deletePlayer(this);
		}
		
	}

	/**
	 * Renders the bullet constantly
	 */
	public void render(Graphics g) {
		
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 8, 8);
		
	}
	
	/**
	 * Lowers health when collision is made with the zombies or the main player
	 */
	private void collision(){
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.NormalZombie || temp.getIdentity() == Identity.QuickZombie || temp.getIdentity() == Identity.SlowZombie || temp.getIdentity() == Identity.MainPlayer || temp.getIdentity() == Identity.Splitter){ //tempObject is now basic enemy
				if(getBounds().intersects(temp.getBounds())){
					//collision code
					health -= 10;
				}
			}
		}
	}

	/**
	 * Returns a rectangle in same position as EnemyBullet for collision purposes
	 */
	public Rectangle getBounds() {
		
		return new Rectangle((int)(x), (int)(y), 8, 8);
		
	}

	
	
}
