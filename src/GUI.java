import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * GUI Class creates the frame that all the graphics show up in
 * @author Steven Childrey
 *
 */
public class GUI extends Canvas{

	private static final long serialVersionUID = 6411499808530678723L;

	/**
	 * Creates the frame that everything fits inside
	 * @param w width
	 * @param h height
	 * @param name frame title
	 * @param control 
	 */
	public GUI(int w, int h, String name, Controller control){
		
		JFrame frame = new JFrame(name);
		
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(control);
		frame.setVisible(true);
		frame.requestFocus();
		control.start();
		
	}
	
}
