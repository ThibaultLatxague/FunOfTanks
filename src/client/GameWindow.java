package client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import input.PlayerInputs;
import player.Skill;

/**
 * Top-level game window.
 * Responsible for creating the JFrame and handling lifecycle events:
 * - on open: load input bindings
 * - on close: save inputs and player skills
 */
public class GameWindow {
	private JFrame jframe;
	
	public GameWindow(GamePanel panel) {
		jframe = new JFrame();
		jframe.setTitle("FunOfTanks");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(panel);
		// Listen for window open/close to load and save player input and skills
		jframe.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// Load saved player input bindings when window is shown
				PlayerInputs.loadInputs();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// Persist input bindings and player skills before quitting
				PlayerInputs.saveInputs();
				if (panel.getGame().getPlayer() != null) {
					Skill.saveSkills(panel.getGame().getPlayer());
				}
				jframe.dispose();
			}
		});
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
	}
}