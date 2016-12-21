import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Display controls all extras while playing the game, 
 * such as health, score, and kills
 * 
 * @author Steven Childrey
 *
 */
public class Display {

	private Font fnt = new Font("arial", 1, 30);
	public static float HEALTH = 100;
	public static int SCORE = 0;
	public static int KILLED = 0;
	
	/**
	 * Makes sure health stays within 0 - 100
	 */
	public void tick(){
		
		HEALTH = (int)(Controller.bounds(HEALTH, 0, 100));
		
	}
	
	/**
	 * Controls what is constantly being rendered on display
	 * @param g
	 */
	public void render(Graphics g){
		
		
		//health bar
		g.setColor(Color.white);
		g.fillRect(530, 450, 300, 40);
		g.setColor(Color.green);
		g.fillRect(530, 450, (int)(HEALTH * 3), 40);
		g.setFont(fnt);
		g.setColor(Color.black);
		g.drawString("Health: " + Math.round(HEALTH), 615, 480);
		g.setColor(Color.white);
		//Score
		g.drawString("Score: " + SCORE, 10, 480);
		//Killed
		g.drawString("Killed: " + KILLED, 250, 480);
	}
	
	/**
	 * Returns score
	 * @return int score
	 */
	public int getScore(){
		
		return SCORE;
		
	}
	
	/**
	 * Returns amount of kills
	 * @return int KILLED
	 */
	public int getKills(){
		return KILLED;
	}
	
	/**
	 * Resets all variables back to starting position
	 */
	public void reset(){
		
		SCORE = 0;
		KILLED = 0;
		HEALTH = 100;
		
	}
}
