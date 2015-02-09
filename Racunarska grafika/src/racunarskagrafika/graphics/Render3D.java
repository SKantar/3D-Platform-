package racunarskagrafika.graphics;

import racunarskagrafika.app.Game;


public class Render3D extends Render{

	private double[] zBuffer;
	private double renderDistance = 10000; 
	
	public Render3D(int width, int height) {
		super(width, height);
		zBuffer = new double[width * height];
	}
	
	public void floor(Game game){
		double floorPosition = 4;
		double cellingPosition = 8;
		double forwoard = game.time;
		double right = 0;
		double rotation = 0;
		double cosine = Math.cos(rotation);
		double sine = Math.sin(rotation);
		
		for(int y = height/2; y < height; y++){
			double celling = (y - height / 2.0) / height;
		
			double z = floorPosition / celling;
			
			if(celling < 0)
				z = cellingPosition / -celling;
			
			for(int x = 0; x < width; x++){
				double depth = (x - width  / 2.0) / height;
				depth *= z;
				double xx = depth;
				double yy = z + forwoard;
				int xPix = (int) (xx);
				int yPix = (int) (yy);
				
				zBuffer[x+y*width] = z;
				pixels[x+y*width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8; 
				if ( z > 300)
					pixels[x+y*width] = 0;
			}
		}
		
	}
	
	
	public void renderDistanceLimiter(){
		for(int i = 0; i < width * height; i++){
			int color = pixels[i];
			int brightness = (int)(renderDistance / (zBuffer[i]));
			if(brightness < 0)
				brightness = 0;
			if(brightness > 255)
				brightness = 255;
			
			int r = (color >> 16) & 0xff;
			int g = (color >> 8) & 0xff;
			int b = (color) & 0xff;
			
			r = r * brightness  / 255;
			g = g * brightness  / 255;
			b = b * brightness  / 255;
			
			pixels[i] = r << 16 | g << 8 | b;
		}
	}

}
