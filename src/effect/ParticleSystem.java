package effect;

import java.awt.Graphics;
import java.util.ArrayList;

import utils.Calcul;
import utils.Delay;
import utils.Vector;

/**
 * Manages a pool of Particle instances used as a particle emitter.
 * Provides methods to emit particles at once or at intervals and to update/draw them.
 */
public class ParticleSystem {

	protected ArrayList<Particle> particles;
	private double spawnSize;
	private int offset;
	
	public ParticleSystem(Particle particle, int nb) {
		// Create a pool of particles cloned from a template particle
		spawnSize = particle.getSize();
		particles = new ArrayList<>(nb);
		for (int i = 0; i < nb; i++) {
			particles.add(new Particle(particle));
		}
		offset = 0;
	}
	
	public ParticleSystem(Particle particle, int nb, int offset) {
		// Same as above but with a scheduling offset between particle spawns
		spawnSize = particle.getSize();
		particles = new ArrayList<>(nb);
		for (int i = 0; i < nb; i++) {
			particles.add(new Particle(particle));
		}
		this.offset = offset;
	}
	
	/**
	 * Emit all particles immediately (but possibly staggered by offset).
	 * min/max are degrees range for random emission direction.
	 */
	public void emit(int x, int y, int min, int max) {
		double alpha = Math.toRadians(min);
		double beta  = Math.toRadians(max);
		for (int i = 0; i < particles.size(); i++) {
			double angle = alpha + Calcul.r.nextDouble() * (beta - alpha);
			Particle p = particles.get(i);
			// faut faire gaffe je sais pas a quelle point c'est gourmand de recreer un thread a chaque fois, a revoir si le jeu est lent
			new Delay(offset*i, () -> p.reset(x, y, new Vector(Math.cos(angle), Math.sin(angle)), spawnSize));
		}
	}
	
	/**
	 * Emit particles only when dead, allowing continuous interval-style emission.
	 */
	public void interval(int x, int y, int min, int max) {
		double alpha = Math.toRadians(min);
		double beta  = Math.toRadians(max);
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			if (p.isDead()) {				
				double angle = alpha + Calcul.r.nextDouble() * (beta - alpha);
				new Delay(offset*i, () -> p.reset(x, y, new Vector(Math.cos(angle), Math.sin(angle)), spawnSize));
			}
		}
	}
	
	/**
	 * Update active particles.
	 */
	public void update() {
		if (particles.stream().filter(p -> !p.isDead()).count() > 0) {
			for (Particle p : particles) {
				p.update();
			}			
		}
	}
	
	/**
	 * Draw all particles (active and inactive - draw method handles visibility).
	 */
	public void draw(Graphics g) {
		for (Particle p : particles) {
			p.draw(g);
		}
	}
	
	/**
	 * Replace pool with new template copies.
	 */
	public void setTemplate(Particle p, int nb) {
		particles.clear();
		for (int i = 0; i < nb; i++) {
			particles.add(new Particle(p));
		}
	}
	
	/**
	 * Set lifetime for all particles (adjusts their size decrement).
	 */
	public void setLifetime(double lifetime) {
		for (Particle p : particles)  {
			p.setLifetime(spawnSize, lifetime);
		}
	}
}
