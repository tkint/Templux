package com.templux.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

/**
 * Created by tkint on 14/01/2018.
 */
@ClientEndpoint
public class TempluxClientEndpoint {

	private static final String TEST = "D:\\Thomas\\Music\\ACDC\\TNT.mp3";

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Client démarré ... " + session.getId());
		try {
			session.getBasicRemote().sendText("connexion");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@OnMessage
	public byte[] onMessage(String message, Session session) {
		System.out.println("----- Client -----");
		System.out.println(message);
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Choisir un fichier à envoyer");
			String userInput = bufferRead.readLine();
			return getByteArrayFromFile(TEST);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] getByteArrayFromFile(String path) {
		File file = new File(path);
		byte[] bytes = null;
		try {
			bytes = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
}
