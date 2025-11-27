package serverClass;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import player.Player;

/**
 * Server-side bullet model used for broadcasting to clients.
 * Contains drawing and hitbox update helpers, and a die hook to apply effects to a Player.
 */
public class ServerBullet {
	
	public int id;
	public int x, y;
	public double orientation;
	public Color color = Color.gray;
	
	public Rectangle hitbox;
	
	public String owner;
	
	public boolean holding = true;
	
	public ServerBullet(int x, int y, double o, String owner, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.orientation = o;
		this.owner = owner;
		hitbox = new Rectangle(x, y, 20, 10);
	}
	
	public void draw(Graphics g) {
		// Draw the bullet rotated by its orientation
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(orientation), x, y);
		
	    Shape rotated = transform.createTransformedShape(hitbox);
	    
		g2.draw(rotated);
		g2.fill(rotated);
	}
	
	public void update(int x, int y, boolean holding) {
		// Update server-side position and holding flag
		this.x = x;
		this.y = y;
		this.holding = holding;
		updateHitbox();
	}
	
	public void updateHitbox() {
		// Update the rectangle hitbox to the current coordinates
		hitbox.setBounds((int)x, (int)y, 20, 10);
	}
	
	public void die(Player p) {
		// Default die effect: small blowup on the specified player
		p.blowup(x, y, .2);
	}
}
