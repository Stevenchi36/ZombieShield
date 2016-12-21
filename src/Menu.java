import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * Menu controls the menu of the game
 * @author Steven Childrey
 *
 */
public class Menu extends MouseAdapter{

	private Controller control;
	private Operator operator;
	private Display display;
	
	/**
	 * Sets all of the parameters to private instances
	 * @param control
	 * @param operator
	 * @param display
	 */
	public Menu(Controller control, Operator operator, Display display){
		
		this.control = control;
		this.operator = operator;
		this.display = display;
		
	}
	
	/**
	 * MousePressed controls the mouse presses
	 */
	public void mousePressed(MouseEvent e){
		
		//Saves mouse positions
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		//Also resets in case entrance after death
		if(isOver(mx, my, 40, 200, 768, 70) && control.gameState == State.Menu){
			operator.deleteAll();
			Generator.killCount = 0;
			Generator.count = 0;
			display.reset();
			control.gameState = State.Game;
			operator.newPlayer(new MainPlayer(850 / 2 - 46, 530 / 2 - 46, Identity.MainPlayer, operator, control));
			operator.newPlayer(new HumanEnemy(0, 0, Identity.HumanEnemy, operator));
		}
		//Help guide with button back to menu
		if(isOver(mx, my, 40, 295, 768, 70) && control.gameState == State.Menu){
			control.gameState = State.Help;
		}
		//Quit button
		if(isOver(mx, my, 40, 390, 768, 70)  && control.gameState == State.Menu){
			System.exit(1);
		}
	
	}
	
	/**
	 * tick method updates many times per second
	 */
	public void tick(){
		
		
		
	}
	
	/**
	 * Renders all things other than background.
	 * Nothing needed in this case
	 * @param g graphics
	 */
	public void render(Graphics g){
		
		
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
