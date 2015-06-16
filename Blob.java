import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import static processing.core.PApplet.CORNER;
import static processing.core.PApplet.RADIUS;

import java.awt.Color;
import java.util.List;

import processing.core.PGraphics;

public class Blob extends Massed
{
	private static final double HALF_SLICE_ANGLE = PI / 20;
	
	public Blob(double x, double y, double rotation, Color color, Field field, Window window, double mass)
	{
		super(x, y, rotation, color, field, window, mass, 10.0D);
	}
	
	public double getRadius()
	{
		return sqrt(this.mass / PI);
	}
	
	public double getSpeed()
	{
		return 100.0D / this.mass;
	}
	
	public int compareTo(Drawable a)
	{
		if (a instanceof Food)
			return -1;
		if (a instanceof Blob)
		{
			Blob b = (Blob)a;
			if (b.mass != this.mass)
				return (this.mass < b.mass) ? -1 : 1;
		}
		return this.id - a.id;
	}
	
	public void drawGraphic()
	{
		double rad = this.getRadius();
		Color darker = this.color.darker();
		this.window.circle(darker, this.x, this.y, rad);
		this.window.slice(this.color, this.x, this.y, rad * INNER_SIZE, (this.rotation + HALF_SLICE_ANGLE), (this.rotation - HALF_SLICE_ANGLE) + TAU);
	}
	
	public double radiusThrough(double x, double y)
	{
		return this.getRadius();
	}
	
	private void move()
	{
		double speed = this.getSpeed();
		
		double dx = speed * cos(this.rotation);
		double dy = speed * sin(this.rotation);
		
		this.x = max(min(this.x + dx, (double)this.field.getWidth()), 0.0D);
		this.y = max(min(this.y + dy, (double)this.field.getHeight()), 0.0D);
	}
	
	public void act()
	{
		//this.move();
		//this.decay();
		//this.eat();
	}
}
