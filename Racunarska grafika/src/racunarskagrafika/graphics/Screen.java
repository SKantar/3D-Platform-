package racunarskagrafika.graphics;

import java.util.Random;

import racunarskagrafika.app.Game;

public class Screen extends Render{

	private Render test;
	private Render3D render;
	public Screen(int width, int height) {
		super(width, height);
		test = new Render(256, 256);
		render = new Render3D(width, height);
		Random random = new Random();
		for(int i = 0 ; i < 256 * 256; i++){
			test.pixels[i] = random.nextInt();
		}
	}
	
	public void render(Game game){
		for(int i = 0 ; i < width * height; i ++){
				pixels[i] = 0;
			}
		
		//for(int i = 0; i < 50; i++){
			//int anim = (int)(Math.sin((game.time + i * 2) % 1000.0 / 100)*200);
			//int anim2 = (int)(Math.cos((game.time + i * 2) % 1000.0 / 100)*200);
			//draw(test, (width-256)/2 + anim , (height-256)/2 - anim2);
			render.floor(game);
			render.renderDistanceLimiter();
			draw(render, 0, 0);
		//}
	}
	

}
