import static java.lang.Math.max;

public abstract class Massed extends Drawable
{
	protected double mass;
	private double minMass;
	
	protected Massed(double x, double y, double rot, java.awt.Color col, Field field, Window win, double mass, double minMass)
	{
		super(x, y, rot, col, field, win);
		this.minMass = max(minMass, 0.0D);
		this.mass = limitMass(mass);
	}
	
	private double limitMass(double val)
	{
		return max(val, this.minMass);
	}
	
	public double getMass()
	{
		return this.mass;
	}
	
	public void setMass(double val)
	{
		this.mass = limitMass(val);
	}
	
	public void changeMass(double diff)
	{
		this.mass = limitMass(this.mass + diff);
	}
}