import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controls the keyboard button presses
 * @author Steven Childrey
 *
 */
public class Keyboard extends KeyAdapter{

	private Operator operator;
	private boolean press0 = false, press1 = false, press2 = false, press3 = false;
	
	/**
	 * Starts the Keyboard class
	 * @param operator
	 */
	public Keyboard(Operator operator){
		
		this.operator = operator;
		
	}
	
	/**
	 * Controls what happens when the W, A, S, or D key are pressed
	 */
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < operator.players.size(); i++){
			
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.MainPlayer){
				
				if(key == KeyEvent.VK_W){ 
					temp.setYRate((float)(-2.5)); 
					press0 = true;
				}
				if(key == KeyEvent.VK_S){ 
					temp.setYRate((float)(2.5)); 
					press1 = true;
				}
				if(key == KeyEvent.VK_D){ 
					temp.setXRate((float)(2.5)); 
					press2 = true;
				}
				if(key == KeyEvent.VK_A){ 
					temp.setXRate((float)(-2.5)); 
					press3 = true;
				}
				
			}
			
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	
	/**
	 * Controls what happens when the W, A, S, or D keys are released.
	 */
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < operator.players.size(); i++){
			Objects temp = operator.players.get(i);
			
			if(temp.getIdentity() == Identity.MainPlayer){
				//key events for player 1
				
				if(key == KeyEvent.VK_W) press0 = false;
				if(key == KeyEvent.VK_S) press1 = false;
				if(key == KeyEvent.VK_D) press2 = false;
				if(key == KeyEvent.VK_A) press3 = false; 

				//vertical movement
				if(!press0 && !press1) temp.setYRate(0);
				if(!press2 && !press3) temp.setXRate(0);
			}
		}
		
	}
}
