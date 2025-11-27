package serverHost;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.net.*;

/**
 * TCP game server that accepts client connections and dispatches ClientHandler tasks.
 * Holds a ServerPlaying instance representing authoritative game state.
 */
public class Server implements Runnable {
	public static final int PORT = 4551;

	private ArrayList<ClientHandler> clients;
	private ServerSocket server;

	private ExecutorService pool;
	
	private boolean running = true;
	
	
	//Game
	private ServerPlaying playing;

	public Server() throws IOException {
		clients = new ArrayList<>();
		server = new ServerSocket(PORT, 0, InetAddress.getByName("0.0.0.0"));
		pool = Executors.newFixedThreadPool(8);
		playing = new ServerPlaying();
		
		pool.execute(this);
	}

	@Override
	public void run() {
		// Accept loop: accept new TCP clients and create handlers
		while (running) {
			try {
				Socket client = server.accept();
				ClientHandler ch = new ClientHandler(client, clients, this);
				clients.add(ch);
				pool.execute(ch);
			} catch (IOException e) {
				if (running) {
					e.printStackTrace();					
				} else {
					System.out.println("Serveur arreté proprement");
				}
			}
		}
	}
	
	public void close() {
		// Shut down the server socket, client handlers and thread pool
		running = false;
	    try {
	        if (server != null && !server.isClosed()) {
	            server.close(); // Cela lève une SocketException dans le thread `run()`
	        }
	        for (ClientHandler ch : clients) {
	            ch.close();
	        }
	        pool.shutdownNow();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public ServerPlaying getPlaying() {
		return playing;
	}
}
