package com.templux.client;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.*;

/**
 * Created by tkint on 14/01/2018.
 */
@ClientEndpoint
public class TempluxClientEndpoint {

	private static final String TEST = "D:\\tkint\\Music\\ACDC\\TNT.mp3";

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

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			String line;
			while ((line = bufferedReader.readLine()) != null) {

			}
			return byteArrayOutputStream.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
