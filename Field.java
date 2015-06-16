import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import static java.lang.Math.PI;

import java.awt.Color;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import processing.core.*;

public class Field
{
	private static final int FIELD_WIDTH = 1000;
	private static final int FIELD_HEIGHT = 700;
	
	private List<Food> foods;
	private List<Blob> blobs;
	private Window window;
	
	private static Random random = new Random();
	
	public Field(Window window)
	{
		this.window = window;
		this.foods = new LinkedList<Food>();
		this.blobs = new LinkedList<Blob>();
		
		/*this.foods.add(new Food(50.0, 50.0D, 0.0D, this.randomColor(), this, window, 100.0D));
		this.foods.add(new Food(50.0, 150.0D, PI / 4.0D, this.randomColor(), this, window, 0.0D));
		this.foods.add(new Food(150.0, 50.0D, PI / 24.0D, this.randomColor(), this, window, 0.0D));
		int num = 128;
		this.blobs.add(new Blob(050.0D, 150.0D, 0.0D, new Color(num, 0, 0), this, window, 400.0D));
		this.blobs.add(new Blob(050.0D, 250.0D, 0.0D, new Color(0, num, 0), this, window, 400.0D));
		this.blobs.add(new Blob(050.0D, 350.0D, 0.0D, new Color(0, 0, num), this, window, 400.0D));
		this.blobs.add(new Blob(150.0D, 250.0D, 0.0D, new Color(num / 3, num / 3, num / 3), this, window, 400.0D));
		this.blobs.add(new Blob(250.0D, 150.0D, 0.0D, new Color(num / 2, num / 2, 0), this, window, 400.0D));
		this.blobs.add(new Blob(250.0D, 250.0D, 0.0D, new Color(num / 2, 0, num / 2), this, window, 400.0D));
		this.blobs.add(new Blob(250.0D, 350.0D, 0.0D, new Color(0, num / 2, num / 2), this, window, 400.0D));
		num = 255;
		this.blobs.add(new Blob(350.0D, 150.0D, 0.0D, new Color(num, 0, 0), this, window, 400.0D));
		this.blobs.add(new Blob(350.0D, 250.0D, 0.0D, new Color(0, num, 0), this, window, 400.0D));
		this.blobs.add(new Blob(350.0D, 350.0D, 0.0D, new Color(0, 0, num), this, window, 400.0D));
		this.blobs.add(new Blob(450.0D, 250.0D, 0.0D, new Color(num / 3, num / 3, num / 3), this, window, 400.0D));
		this.blobs.add(new Blob(550.0D, 150.0D, 0.0D, new Color(num / 2, num / 2, 0), this, window, 400.0D));
		this.blobs.add(new Blob(550.0D, 250.0D, 0.0D, new Color(num / 2, 0, num / 2), this, window, 400.0D));
		this.blobs.add(new Blob(550.0D, 350.0D, 0.0D, new Color(0, num / 2, num / 2), this, window, 400.0D));
		
		for (int y = 0; y <= window.getHeight(); y += 100)
			for (int x = 0; x <= window.getWidth(); x += 100)
				this.blobs.add(new Blob(x, y, random.nextDouble() * PI * 2.0D, randomColor(), this, window, 0.0D));// + (200.0D * random.nextDouble())));
	*/
	}
	
	private Color randomColor()
	{
		int red = 0;
		int green = 0;
		int blue = 0;
		while (red + green + blue < 128)
		{
			red = random.nextInt(256);
			green = random.nextInt(256);
			blue = random.nextInt(256);
		}
		return new Color(red, green, blue);
	}
	
	public int getWidth()
	{
		return FIELD_WIDTH;
	}
	
	public int getHeight()
	{
		return FIELD_HEIGHT;
	}
	
	public List<Food> getFoods()
	{
		return this.foods;
	}
	
	public List<Blob> getBlobs()
	{
		return this.blobs;
	}
	
	public static void main(String[] arg)
	{
		PApplet.main("Window");
	}
}
