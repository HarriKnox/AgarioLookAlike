import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sqrt;

import java.awt.Color;
import processing.core.PGraphics;

public class Food extends Massed
{
	public Food(double x, double y, double rot, Color col, Field field, Window window, double mass)
	{
		super(x, y, rot, col, field, window, mass, 1.0D);
	}
	
	public int compareTo(Drawable a)
	{
		if (a instanceof Blob)
			return -1;
		if (a instanceof Food)
		{
			Food f = (Food)a;
			if (f.mass != this.mass)
				return (this.mass < f.mass) ? -1 : 1;
		}
		return this.id - a.id;
	}
	
	public void drawGraphic()
	{
		double width = this.getWidth();
		this.window.square(this.color.darker(), this.x, this.y, this.rotation, width);
		this.window.square(this.color, this.x, this.y, this.rotation, width * INNER_SIZE);
	}
	
	public double radiusThrough(double x, double y)
	{
		double minRadius = this.getWidth() / 2.0D;
		if (x == this.x && y == this.y) return minRadius;
		double angle = atan2(y - this.y, x - this.x) - this.rotation; // Get relative angle
		angle += QUARTER_PI; // Add an eighth of a circle to offset the limits of the modulo to -PI/8..+PI/8 instead of 0..PI/4
		angle %= HALF_PI; // Modulo to get angle in first quadrant
		if (angle < 0.0D) angle += HALF_PI; // Add a quarter of a circle if the remainder from the modulo was negative to make the remainder positive
		angle -= QUARTER_PI; // Remove the extra eighth added to offset the limits
		return minRadius / cos(angle); // Trigonometry
	}
	
	public double getWidth()
	{
		return sqrt(this.mass);
	}
	
	public double getRadius()
	{
		return this.getWidth() * SQRT_2 / 2.0D;
	}
}
