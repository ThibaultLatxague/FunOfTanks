package serverHost;

import java.io.IOException;

import gamestate.Domination;
import gamestate.GameState;
import gamestate.Playing;
import player.Player;
import player.TypeShot;
import serverClass.ServerBullet;
import utils.Finder;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import client.Game;

/**
 * Listens for UDP packets from the server and updates the local game accordingly.
 * The thread receives datagrams, parses header/body and applies updates depending on the current GameState.
 */
public class UDPServerConnection implements Runnable {

	private DatagramSocket socket;
	
	private Game game;
	
	private boolean running = true;
	
	public UDPServerConnection(Game g) {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.game = g;
	}
	
	@Override
	public void run() {
		// Main loop: receive UDP datagrams and dispatch to the appropriate game update logic
		while (running) {
			try {
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				String res = new String(packet.getData(), 0, packet.getLength());
				if (res != null) {
					String header = ClientHandler.getHeader(res);
					String[] body = ClientHandler.getBody(res);
					switch (GameState.state) {
					case MENU:break;
					case PLAYING:
						Playing play = game.getPlaying();
						if (header.equals("updatetank")) {
							Player p = Finder.findPlayer(body[0], play.getPlayers());
							if (p != null) {
								if (p.getTank() != null) {
									p.getTank().setX(Integer.parseInt(body[1]));
									p.getTank().setY(Integer.parseInt(body[2]));
									p.getTank().setOrientation(Double.parseDouble(body[3]));
									if (!game.getPlayer().equals(p)) {
										p.getTank().getCannon().setShot(TypeShot.parseTypeShot(body[4]));										
									}
								}
							}
						} else if (header.equals("updatebullet")) {
							ServerBullet b = Finder.findServerBullet(body[3], Integer.parseInt(body[0]),
									play.getEnemiesBullets());
							if (b != null) {
								b.update(Integer.parseInt(body[1]), Integer.parseInt(body[2]), Boolean.parseBoolean(body[4]));
							}
						} else if (header.equals("point")) {
							if (play instanceof Domination) {
								Domination dom = (Domination) play;
								dom.getZone().setPoints(Integer.parseInt(body[0]));
							}
						} else if (header.equals("trainee")) {
							play.getPlayer().trainee(Integer.parseInt(body[0]), Integer.parseInt(body[1]));
						}
						break;
					case FINISH:
						break;
					}
				}
			} catch (IOException e) {
				if (running) {
					System.out.println("udp conn pas normal");
				} else {
					System.out.println("server fermé");
				}
			}
		}
	}
	
	/**
	 * Send a UDP message to the provided InetAddress (used for client -> server UDP).
	 */
	public void send(String mes, InetAddress ip) {
		byte[] toSend = mes.getBytes();
		DatagramPacket packet = new DatagramPacket(toSend, toSend.length, ip, UDPServer.PORT);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stop the thread and close the socket.
	 */
	public void close() {
		running = false;
		socket.close();
	}
	
}
