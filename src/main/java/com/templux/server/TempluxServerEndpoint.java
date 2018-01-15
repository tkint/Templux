package com.templux.server;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by tkint on 14/01/2018.
 */
@ServerEndpoint(value = "/server")
public class TempluxServerEndpoint {

	private final String TEMP_DIR = "D:/tmp";

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
	public String onMessage(byte[] bytes, Session session) {
		System.out.println("---- Serveur -----");
		if (bytes != null) {
			playMp3(bytes);
			return "Fichier reçu";
		}
		return "Erreur";
	}

	private void playMp3(byte[] bytes) {
		File directory = new File(TEMP_DIR);
		try {
			Files.createDirectories(directory.toPath());
			File file = Files.createTempFile(directory.toPath(), "templux-", ".mp3").toFile();

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			fileOutputStream.close();

			new javafx.embed.swing.JFXPanel();

			Media media = new Media(file.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
