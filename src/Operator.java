import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Controls all of the objects inside of the frame and stores them in an ArrayList
 * @author Steven Childrey
 *
 */
public class Operator {

	ArrayList<Objects> players = new ArrayList<Objects>();
	
	/**
	 * Updates constantly.
	 * Calls the tick method for all other Objects
	 */
	public void tick(){
		
		for (int i = 0; i < players.size(); i++) {
			
			Objects temp = players.get(i);
			temp.tick();
			
        }
		
	}
	
	/**
	 * Calls the render method for all other Objects
	 * @param Graphics g
	 */
	public void render(Graphics g){
		
		for (int i = 0; i < players.size(); i++) {
			
			Objects temp = players.get(i);
			temp.render(g);
			
        }
		
	}
	
	/**
	 * Adds a new player
	 * @param obj
	 */
	public void newPlayer(Objects obj){
		
		this.players.add(obj);
		
	}
	
	/**
	 * Removes player
	 * @param obj
	 */
	public void deletePlayer(Objects obj){
		
		this.players.remove(obj);
		
	}
	
	/**
	 * Removes all players
	 */
	public void deleteAll(){
		
		players.clear();
		
	}
	
}
