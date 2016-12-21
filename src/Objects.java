import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Controls for all of the other object, such as zombies, bullets, and the main player.
 * @author Steven Childrey
 *
 */
public abstract class Objects {

	protected float x, y;
	protected Identity id;
	protected float xRate, yRate;
	
	/**
	 * Creates a new object
	 * @param x
	 * @param y
	 * @param id
	 */
	public Objects(int x, int y, Identity id){
		
		this.x = x;
		this.y = y;
		this.id = id;
		
	}
	
	
	/**
	 * Sets the value of x
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Sets the value of y
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * Returns the value of x
	 * @return x
	 */
	public float getX(){
		return x;
	}
	
	/**
	 * Returns the value of y
	 * @return y
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * Sets Identity
	 * @param id
	 */
	public void setIdentity(Identity id){
		this.id = id;
	}
	
	/**
	 * Returns Identity
	 * @return Identity
	 */
	public Identity getIdentity(){
		return id;
	}
	
	/**
	 * Sets xRate
	 * @param rateX
	 */
	public void setXRate(float rateX){
		this.xRate = rateX;
	}
	
	/**
	 * Sets yRate
	 * @param rateY
	 */
	public void setYRate(float rateY){
		this.yRate = rateY;
	}
	
	/**
	 * Returns xRate
	 * @return xRate
	 */
	public float getXRate(){
		return xRate;
	}
	
	/**
	 * Returns yRate
	 * @return yRate
	 */
	public float getYRate(){
		return yRate;
	}
	
	//Abstract methods that all classes that extend Objects will have
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
}
