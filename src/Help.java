import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Controls the Help menu
 * @author Steven Childrey
 *
 */
public class Help extends MouseAdapter{
	
	Controller control;
	Operator operator;
	
	/**
	 * Creates the help menu
	 * @param control
	 * @param operator
	 */
	public Help(Controller control, Operator operator){
		
		this.control = control;
		this.operator = operator;
		
	}
	
	/**
	 * Controls what happened when the mouse is clicked in a certain area
	 */
	public void mousePressed(MouseEvent e){
		
		int mx = e.getX();
		int my = e.getY();
		
		//Back to Menu
		if(isOver(mx, my, 264, 413, 287, 72) && control.gameState == State.Help){
			control.gameState = State.Menu;
		}
	
	}
	
	/**
	 *Controls what happenes when the mouse is released
	 */
	public void mouseReleased(MouseEvent e){
		
	}
	
	/**
	 * Updates constantly. Not needed for the Help Class
	 */
	public void tick(){
		
		
		
	}
	
	/**
	 * Constantly renders. Is not needed for the Help class
	 * @param g
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
