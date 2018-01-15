package com.templux.server;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by tkint on 14/01/2018.
 */
@ServerEndpoint(value = "/server")
public class TempluxServerEndpoint {

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Serveur démarré ... " + session.getId());
	}

	@OnMessage
	public String onMessage(String message, Session session) {
		System.out.println("---- Serveur -----");
		return "Message reçu: " + message;
	}

	@OnMessage
	public String onMessage(byte[] file, Session session) {
		System.out.println("---- Serveur -----");
		if (file != null) {
			return "Fichier reçu";
		}
		return "Erreur";
	}
}
