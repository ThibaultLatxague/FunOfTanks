package utils;

/**
 * Simple 2D vector container with basic operations used for particle and movement math.
 */
public class Vector {
	
	public double x, y;
	
	public Vector() {
		x = 0;
		y = 0;
	}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double norm() {
		return Math.sqrt(x*x + y*y);
	}
	
	public void normalize() {
	    double n = norm();
	    if (n != 0) {
	       x /= n;
	       y /= n;
	    }
	}

	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
	
}
