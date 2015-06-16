import static java.lang.Math.hypot;
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;

import java.awt.Color;
import java.util.Comparator;
import java.util.Random;

import processing.core.PGraphics;

public abstract class Drawable implements Comparable<Drawable>
{
	protected static final double PI = Math.PI;
	protected static final double HALF_PI = PI / 2.0D;
	protected static final double QUARTER_PI = PI / 4.0D;
	protected static final double TAU = 2.0D * PI;
	protected static final double SQRT_2 = sqrt(2.0D);
	protected static final double INNER_SIZE = 0.85D;
	protected static int ID = 0;
	
	protected double x;
	protected double y;
	protected double rotation;
	protected Color color;
	protected Field field;
	protected Window window;
	protected final int id;
	
	protected Drawable(double x, double y, double rot, Color col, Field field, Window window)
	{
		this.x = x;
		this.y = y;
		this.rotation = rot % TAU;
		this.color = col;
		this.field = field;
		this.window = window;
		this.id = ID++;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void changeX(double diff)
	{
		this.x += diff;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void changeY(double diff)
	{
		this.y += diff;
	}
	
	public void setRotation(double r)
	{
		this.rotation = r % TAU;
	}
	
	public double getRotation()
	{
		return this.rotation;
	}
	
	public abstract double getRadius();
	
	/**
		Compares this drawable to another drawable. Should never return 0 because of Window's use of TreeSet.
		If two drawables are otherwise equal (compareTo would normally return 0), return `this.id - a.id`.
	**/
	public abstract int compareTo(Drawable a);
	
	/** Draws the image of the Drawable onto the window using the provided offset methods of `circle` and `square`. **/
	public abstract void drawGraphic();
	
	/** Returns whether the passed drawable and this shape share area. **/
	public boolean intersects(Drawable s)
	{
		return this.radiusThrough(s.x, s.y) + s.radiusThrough(this.x, this.y) < hypot(s.x - this.x, s.y - this.y);
	}
	
	/** Returns whether this shape has the coordinate inside of it. **/
	public boolean contains(double x, double y)
	{
		return hypot(x - this.x, y - this.y) <= this.radiusThrough(x, y);
	}
	
	/**
		Returns the length of the line from the center to the edge that passes through the specified coordinate.
		If the passed coordinates are equal to this.x and this.y, either throw an error or return the shortest radius of the shape.
	**/
	public abstract double radiusThrough(double x, double y);
}