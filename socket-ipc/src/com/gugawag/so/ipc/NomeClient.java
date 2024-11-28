package com.gugawag.so.ipc;

/**
 * Client program requesting current date from server.
 *
 * Figure 3.22
 *
 * @author Silberschatz, Gagne and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Eighth Edition
 */

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class NomeClient {
	public static void main(String[] args)  {
		try {
			// this could be changed to an IP name or address other than the localhost
			Socket servidorSock = new Socket("10.0.72.67",6013);
			InputStream in = servidorSock.getInputStream();
			BufferedReader bin = new BufferedReader(new InputStreamReader(in));

			System.out.println("=== Cliente iniciado ===\n");

			String line = bin.readLine();
			System.out.println("O servidor me disse:" + line);

			PrintWriter pout = new PrintWriter(servidorSock.getOutputStream(), true);
			// TODO Altere abaixo para enviar seu nome ao servidor
			pout.println("Amanda Cruz de Araújo");
			servidorSock.close();
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
