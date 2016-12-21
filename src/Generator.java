import java.util.Random;


/**
 * Controls when the objects, such as Zombies, are generated.
 * @author Steven Childrey
 *
 */
public class Generator {

	private Operator operator;
	private Display display;
	private Controller control;
	public static int count = 0;
	private Random r = new Random();
	public static int killCount = 0;
	private Sound sound;
	
	/**
	 * @param operator
	 * @param display
	 * @param control
	 */
	public Generator(Operator operator, Display display, Controller control){
		
		this.operator = operator;
		this.display = display;
		this.control = control;
		
	}
	
	/**
	 * Called constantly.
	 * Checks what level you are at and generates the enemies and pickups based on that
	 */
	public void tick(){
		
		if(display.getKills() == killCount && count == 0){ //Wave 2
			sound = new Sound("StartGame.wav");
			operator.newPlayer(new HumanEnemy(798, 215, Identity.HumanEnemy, operator)); //right middle - medium
			//operator.newPlayer(new HumanEnemy(0, 455, Identity.HumanEnemy, operator)); //bottom left - hard
			//operator.newPlayer(new HumanEnemy(399, 0, Identity.HumanEnemy, operator)); //top middle - expert
			Normal(1);
			count++;//count = 1
		}
		else if(display.getKills() == killCount && count == 1){
			nextRound();
			Normal(2);
			Quick(1);
			count++;//count = 2
		}
		else if(display.getKills() == killCount && count == 2){
			nextRound();
			Normal(3);
			Quick(2);
			count++;//count = 2
		}
		else if(display.getKills() == killCount && count == 3){
			nextRound();
			Normal(3);
			Quick(2);
			Slow(1);
			Splitter(1);
			count++;//count = 3
		}
		else if(display.getKills() == killCount && count == 4){
			nextRound();
			Normal(3);
			Quick(2);
			Slow(2);
			Splitter(2);
			count++;//count = 4
		}
		else if(display.getKills() == killCount && count == 5){
			nextRound();
			Normal(5);
			Quick(2);
			Slow(2);
			Splitter(2);
			Health(1);
			count++;
		}
		else if(display.getKills() == killCount && count == 6){
			nextRound();
			Normal(5);
			Quick(2);
			Slow(2);
			Splitter(1);
			count++;
		}
		else if(display.getKills() == killCount && count == 7){
			nextRound();
			Normal(6);
			Quick(3);
			Slow(3);
			Splitter(1);
			GunUp();
			count++;
		}
		else if(display.getKills() == killCount && count == 8){
			nextRound();
			operator.newPlayer(new HumanEnemy(798, 215, Identity.HumanEnemy, operator));
			HealthFull(1);
			Normal(7);
			Quick(3);
			Slow(3);
			Splitter(2);
			count++;
		}
		else if(display.getKills() == killCount && count == 9){
			nextRound();
			Normal(8);
			Quick(1);
			Slow(3);
			Splitter(3);
			count++;
		}
		else if(display.getKills() == killCount && count == 10){
			nextRound();
			operator.newPlayer(new HumanEnemy(0, 455, Identity.HumanEnemy, operator));
			Health(1);
			Normal(10);
			Quick(1);
			Slow(3);
			Splitter(4);
			count++;
		}
		else if(display.getKills() == killCount && count == 10){
			nextRound();
			Health(1);
			Normal(12);
			Quick(1);
			Slow(3);
			Splitter(5);
			count++;
		}
		else if(display.getKills() == killCount && count == 11){
			control.gameState = State.Win;
			operator.deleteAll();
			sound = new Sound("YouWin.wav");
		}
	}
	
	/**
	 * Adds the NormalZombie to the game
	 * @param num - the number of NormalZombies to add
	 */
	private void Normal(int num){
		for(int i = 0; i < num; i++){
			operator.newPlayer(new NormalZombie(r.nextInt(750), r.nextInt(450), Identity.NormalZombie, operator));
			killCount++;
		}
	}
	
	/**
	 * Adds the QuickZombie to the game
	 * @param num - the number of QuickZombies to add
	 */
	private void Quick(int num){
		for(int i = 0; i < num; i++){
			operator.newPlayer(new QuickZombie(r.nextInt(750), r.nextInt(450), Identity.QuickZombie, operator));
			killCount++;
		}
	}
	
	/**
	 * Adds the SlowZombie to the game
	 * @param num - the number of SlowZombies to add
	 */
	private void Slow(int num){
		for(int i = 0; i < num; i++){
			operator.newPlayer(new SlowZombie(r.nextInt(750), r.nextInt(450), Identity.SlowZombie, operator));
			killCount++;
		}
	}
	
	/**
	 * Adds the Splitter to the game
	 * @param num - the number of Splitters to add
	 */
	private void Splitter(int num){
		for(int i = 0; i < num; i++){
			operator.newPlayer(new Splitter(r.nextInt(750), r.nextInt(450), Identity.Splitter, operator));
			killCount += 3;
		}
	}
	
	/**
	 * Adds the regular health to the game
	 * @param num - the number of regular health to add
	 */
	private void Health(int num){
		for(int i = 0; i < num; i++){
			operator.newPlayer(new HealthBoost(r.nextInt(750), r.nextInt(450), Identity.HealthBoost, operator));			
		}
	}
	
	/**
	 * Adds the full boost to the game
	 * @param num - the number of full boosts to add
	 */
	private void HealthFull(int num){
		for(int i = 0; i < num; i++){
			operator.newPlayer(new HealthBoostFull(r.nextInt(750), r.nextInt(450), Identity.HealthBoostFull, operator));			
		}
	}
	
	/**
	 * Adds the gun upgrade to the game
	 */
	private void GunUp(){
		operator.newPlayer(new FastGunPickup(r.nextInt(750), r.nextInt(450), Identity.FastGunPickup, operator));
	}
	
	/**
	 * Makes the next round sound
	 */
	private void nextRound(){
		sound = new Sound("NextRound.wav");
	}
}
