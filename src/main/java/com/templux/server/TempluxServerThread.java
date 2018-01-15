package com.templux.server;

import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;

/**
 * Created by tkint on 14/01/2018.
 */
public class TempluxServerThread extends Thread {

	private final Server server;

	public TempluxServerThread(String domain, int port, String path) {
		this.server = new Server(domain, port, path, null, TempluxServerEndpoint.class);
	}

	@Override
	public synchronized void start() {
		super.start();
		try {
			this.server.start();
			System.out.println("Le serveur Templux a démarré...");
		} catch (DeploymentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
		}
	}
}
