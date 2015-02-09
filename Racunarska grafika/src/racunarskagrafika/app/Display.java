package racunarskagrafika.app;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import racunarskagrafika.graphics.Render;
import racunarskagrafika.graphics.Screen;

@SuppressWarnings("serial")
public class Display extends Canvas implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private Render render;
	private Screen screen;
	private Game game;
	private BufferedImage img;
	private int[] pixels;
	
	public Display() {
		screen = new Screen(AppCore.WIDTH, AppCore.HEIGHT);
		img = new BufferedImage(AppCore.WIDTH, AppCore.HEIGHT,BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		game = new Game();
	}
	
	public void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void stop(){
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(running){
			tick();
			render();
		}
		
	}
	
	private void tick() {
		game.tick();

	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		screen.render(game);
		
		
		for(int i = 0 ; i < AppCore.HEIGHT  * AppCore.WIDTH; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(img, 0, 0, AppCore.WIDTH, AppCore.HEIGHT, null);
		g.dispose();
		bs.show();
		
	}
}
