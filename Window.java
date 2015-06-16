import java.awt.Color;
import java.lang.Math;
import java.util.Set;
import java.util.TreeSet;

import processing.core.PApplet;

public class Window extends PApplet
{
	protected static final double PI = Math.PI;
	protected static final double HALF_PI = PI / 2.0D;
	protected static final double QUARTER_PI = PI / 4.0D;
	protected static final double TAU = 2.0D * PI;
	protected static final double SQRT_2 = Math.sqrt(2.0D);
	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;
	
	private double xOffset = 0.0D;
	private double yOffset = 0.0D;
	
	private Field field;
	
	public void setup()
	{
		this.field = new Field(this);
		this.size(Math.min(WINDOW_WIDTH, this.field.getWidth()), Math.min(WINDOW_HEIGHT, this.field.getHeight()));
		this.frameRate(30);
		this.ellipseMode(RADIUS);
		this.rectMode(CENTER);
		this.noStroke();
	}
	
	public int getWidth()
	{
		return WINDOW_WIDTH;
	}
	
	public int getHeight()
	{
		return WINDOW_HEIGHT;
	}
	
	public double getXOffset()
	{
		return this.xOffset;
	}
	
	public double getYOffset()
	{
		return this.yOffset;
	}
	
	public boolean inView(Drawable thing)
	{
		double windowXMin = this.xOffset;
		double windowXMax = (double)WINDOW_WIDTH + windowXMin;
		
		double windowYMin = this.yOffset;
		double windowYMax = (double)WINDOW_HEIGHT + windowYMin;
		
		double rad = thing.getRadius();
		
		double xMin = thing.getX() - rad;
		double xMax = thing.getX() + rad;
		
		double yMin = thing.getY() - rad;
		double yMax = thing.getY() + rad;
		
		return xMax >= windowXMin && xMin <= windowXMax && yMax >= windowYMin && yMin <= windowYMax;
	}
	
	private float relativeX(double x)
	{
		return (float)(x - this.xOffset);
	}
	
	private float relativeY(double y)
	{
		return (float)(y - this.yOffset);
	}
	
	public void fill(Color color)
	{
		this.fill(color.getRGB());
	}
	
	public void circle(Color color, double x, double y, double rad)
	{
		this.fill(color);
		this.ellipse(this.relativeX(x), this.relativeY(y), (float)rad, (float)rad);
	}
	
	public void square(Color color, double x, double y, double rotation, double width)
	{
		this.pushMatrix();
		this.translate(this.relativeX(x), this.relativeY(y));
		this.rotate((float)rotation);
		this.fill(color);
		this.rect(0.0F, 0.0F, (float)width, (float)width);
		this.popMatrix();
	}
	
	public void slice(Color color, double x, double y, double rad, double start, double stop)
	{
		this.fill(color);
		this.arc(this.relativeX(x), this.relativeY(y), (float)rad, (float)rad, (float)start, (float)stop);
	}
	
	public void draw()
	{
		this.background(255);
		Set<Drawable> drawables = new TreeSet<Drawable>();
		
		for (Blob blob : this.field.getBlobs())
		{
			blob.act();
			if (this.inView(blob))
				drawables.add(blob);
		}
		
		for (Food food : this.field.getFoods())
		{
			if (this.inView(food))
				drawables.add(food);
		}
		
		for (Drawable thing : drawables)
			thing.drawGraphic();
	}
	
	public void keyPressed()
	{
		switch (this.keyCode)
		{
			case UP:
				this.yOffset = Math.max(this.yOffset - 10.0D, 0.0D);
				break;
			case DOWN:
				this.yOffset = Math.max(0.0, Math.min(this.yOffset + 10.0D, this.field.getHeight() - WINDOW_HEIGHT));
				break;
			case LEFT:
				this.xOffset = Math.max(this.xOffset - 10.0D, 0.0D);
				break;
			case RIGHT:
				this.xOffset = Math.max(0.0, Math.min(this.xOffset + 10.0D, this.field.getWidth() - WINDOW_WIDTH));
				break;
		}
	}
}