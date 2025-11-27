package serverHost;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import client.Game;

/**
 * Client-side helper used when running a local host: opens TCP and UDP connections to the local server.
 * Provides send/sendUDP and close helpers used by Player to communicate with the server.
 */
public class Client {

	private Socket socket;
	private PrintWriter out;
	private InetAddress serverIP;
	
	private ServerConnection serverConn;
	private UDPServerConnection udpConn;

	public Client(String ip, int port, Game game) throws IOException {
		socket = new Socket(ip, port);
		serverIP = InetAddress.getByName(ip);
		serverConn = new ServerConnection(socket, game);
		out = new PrintWriter(socket.getOutputStream(), true);
		udpConn = new UDPServerConnection(game);
		new Thread(serverConn).start();
		new Thread(udpConn).start();
	}
	
	/** Send a TCP command to the server. */
	public void send(String msg) {
		out.println(msg);
	}
	
	/** Send a UDP message to the server (real-time updates). */
	public void sendUDP(String msg) {
		udpConn.send(msg, serverIP);
	}
	
	public void close() {
		try {
			socket.close();
			out.close();
			serverConn.close();
			udpConn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
