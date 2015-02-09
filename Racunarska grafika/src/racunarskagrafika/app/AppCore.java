package racunarskagrafika.app;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AppCore extends JFrame{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private static AppCore instance = null;
	private Display display;
	public AppCore() {
		super();
		display = new Display();
		add(display);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setTitle("Racunarska grafika");
		setResizable(false);
		display.start();
	}
	
	public static AppCore getInstance() {
		if(instance == null){
			instance = new AppCore();
		}
		return instance;
	}
	

}
