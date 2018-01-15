package com.templux.client;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URI;

/**
 * Created by tkint on 14/01/2018.
 */
public class TempluxClientThread extends Thread {

	private ClientManager clientManager;

	private String targetURI;

	public TempluxClientThread(String domain, int port, String path) {
		if (!path.substring(0, 1).equals("/")) {
			path = "/" + path;
		}
		clientManager = ClientManager.createClient();
		targetURI = "ws://" + domain + ":" + port + path + "/server";
	}

	@Override
	public synchronized void start() {
		super.start();
		try {
			clientManager.connectToServer(TempluxClientEndpoint.class, URI.create(targetURI));
			System.out.println("Le client Templux a démarré...");
		} catch (DeploymentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
		}
	}
}
