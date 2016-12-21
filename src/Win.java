import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Controls the screen after a losing death
 * @author Steven Childrey
 *
 */
public class Win extends MouseAdapter{
	
	private Controller control;
	private Operator operator;
	private Display display;
	private Font fnt = new Font("impact", 1, 40);
	
	/**
	 * @param control
	 * @param operator
	 * @param display
	 */
	public Win(Controller control, Operator operator, Display display){
		
		this.control = control;
		this.operator = operator;
		this.display = display;
		
		operator.deleteAll();
		
	}
	
	/**
	 * Controls the mouse clicks if they are over the buttons
	 */
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		//Exit
		if(isOver(mx, my, 40, 412, 770, 70) && control.gameState == State.Win){
			System.exit(1);
		}
		//How to play
		else if(isOver(mx, my, 40, 341, 770, 70) && control.gameState == State.Win){
			control.gameState = State.Help;
		}
		//Play
		else if(isOver(mx, my, 40, 270, 770, 70) && control.gameState == State.Win){
			operator.deleteAll();
			Generator.killCount = 0;
			Generator.count = 0;
			display.reset();
			control.gameState = State.Game;
			operator.newPlayer(new MainPlayer(850 / 2 - 46, 530 / 2 - 46, Identity.MainPlayer, operator, control));
			operator.newPlayer(new HumanEnemy(0, 0, Identity.HumanEnemy, operator));
			
		}
	
	}
	
	/**
	 * Controls what happens
	 */
	public void mouseReleased(MouseEvent e){
		
	}
	
	/**
	 * Updates constantly.
	 * Not needed for this class
	 */
	public void tick(){
		
		
		
	}
	
	/**
	 * Constantly renders the score and number of kills
	 * @param g
	 */
	public void render(Graphics g){
		
		g.setColor(Color.white);
		g.setFont(fnt);
		g.drawString(Integer.toString(Display.SCORE), 200, 250);
		g.drawString(Integer.toString(Display.KILLED), 600, 250);
		
	}
	
	/**
	 * isOver returns true if your mouse is over the button
	 * @param mx is mouses x position
	 * @param my is mouses y position
	 * @param x is position on x axis
	 * @param y is position on y axis
	 * @param w is width of button
	 * @param h is height of button
	 * @return boolean depending on location of click
	 */
	private boolean isOver(int mx, int my, int x, int y, int w, int h){
		
		if(mx > x && mx < x+ w){
			if(my > y && my < y + h)
				return true;
			else
				return false;
		}
		else 
			return false;
		
		
	}

}
