package com.templux;

import com.templux.client.TempluxClientThread;
import com.templux.server.TempluxServerThread;

/**
 * Created by tkint on 14/01/2018.
 */
public class Main {

	private static String serverDomain = "localhost";

	private static int serverPort = 8080;

	private static String serverPath = "/templux";

	public static void main(String... args) {
		initServer();
		initClient();
	}

	private static void initServer() {
		TempluxServerThread templuxServerThread = new TempluxServerThread(serverDomain, serverPort, serverPath);
		templuxServerThread.start();
	}

	private static void initClient() {
		TempluxClientThread templuxClientThread = new TempluxClientThread(serverDomain, serverPort, serverPath);
		templuxClientThread.start();
	}

}
