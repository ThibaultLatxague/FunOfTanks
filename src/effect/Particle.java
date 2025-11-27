package effect;

import java.awt.Color;
import java.awt.Graphics;

import client.Game;
import utils.Vector;

/**
 * Represents a single particle instance.
 * Particles have a size that decreases over time (lifetime), a movement direction and a color.
 * When size reaches zero the particle is considered dead.
 */
public class Particle {
	
	// Current size of the particle (also used to determine when it's dead)
	private double size;
	// Amount to subtract from size each frame (calculated from lifetime)
	private double decrement;
	// Shape used for rendering
	private Shape shape;
	// Movement direction vector (units per frame)
	private Vector dir;
	// Position in world/panel coordinates
	public double x, y;
	// Rendering color
	private Color color;

	public Particle(double size, Shape shape, double lifetime, Vector dir, Color c) {
		// Initialize particle template fields; decrement is computed from desired lifetime (in seconds)
		this.size = size;
		this.decrement = size/(Game.FPS * lifetime);
		this.shape = shape;
		this.dir = dir;
		this.x = 0;
		this.y = 0;
		this.color = c;
	}
	
	public Particle(Particle other) {
		// Copy constructor to clone templates into pool members
		this.size = other.size;
		this.decrement = other.decrement;
		this.shape = other.shape;
		this.dir = new Vector(other.getDir().x, other.getDir().y);
		this.x = other.x;
		this.y = other.y;
		this.color = other.color;
	}



	// Reduce the size each update, mark dead when below threshold
	private void disapear() {
		size -= decrement;
        if (size <= 0.1f) {
           size = 0;
        }
	}
	
	// Move particle according to its direction
	private void move() {
		x += dir.x;
		y += dir.y;
	}
	
	/**
	 * Returns true when particle reached end of its lifetime/size.
	 */
	public boolean isDead() {
		return size <= 0.;
	}
	
	/**
	 * Reset particle to given position/direction and spawn size (used when emitting).
	 */
	public void reset(int x, int y, Vector newdir, double s) {
		this.x = x;
		this.y = y;
		this.dir = newdir;
		size = s;
	}
	
	public void update() {
		disapear();
		move();
	}
	
	/**
	 * Draw particle using its configured shape and color.
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		switch (shape) {
		case RECTANGLE: g.fillRect((int) x, (int) y, (int) size, (int) size);break;
		case CIRCLE: g.fillOval((int) x, (int) y, (int) size, (int) size);break;
		}
	}
	
	public double getSize() {
		return size;
	}
	
	public Vector getDir() {
		return dir;
	}

	/**
	 * Adjust the decrement logic when spawn size or lifetime changes.
	 */
	public void setLifetime(double spawnSize, double lifetime) {
		this.decrement = spawnSize/(Game.FPS * lifetime);
	}
	
	@Override
	public String toString() {
		return "Particle [size=" + size + ", dir=" + dir + ", x=" + x + ", y=" + y + "]";
	}
}
