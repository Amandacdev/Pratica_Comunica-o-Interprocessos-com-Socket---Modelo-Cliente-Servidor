package com.gugawag.so.ipc;

/**
 * Time-of-day server listening to port 6013.
 *
 * Figure 3.21
 *
 * @author Silberschatz, Gagne, and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Ninth Edition
 * Copyright John Wiley & Sons - 2013.
 */
import java.net.*;
import java.io.*;
import java.util.Date;

public class DateServer{
    
    public static void main(String[] args)  {
        try {
            ServerSocket sock = new ServerSocket(6013);

            System.out.println("=== Servidor de Amanda Cruz de Araújo iniciado ===\n");
            // escutando por conexões
            while (true) {
                Socket client = sock.accept();
                // Se chegou aqui, foi porque algum cliente se comunicou
                System.out.println("Servidor recebeu comunicação do ip: " + client.getInetAddress() + "-" + client.getPort());
                
                // Cria uma nova thread para atender o cliente
                Thread clientThread = new Thread(new ClientHandler(client));
                clientThread.start();
            }
        }
        catch (IOException ioe) {
                System.err.println(ioe);
        }
    }
}

// Classe para tratar cada cliente em uma thread separada
class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

            // Escreve a data atual no socket
            pout.println(new Date().toString() + "- AmandaZZ!");

            InputStream in = client.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            String line = bin.readLine();
            System.out.println("O cliente me disse: " + line);

            // Faz o servidor esperar por 40 segundos antes de fechar a comunicação
            System.out.println("Esperando 40 segundos antes de fechar a comunicação...");
            Thread.sleep(40000);  // Espera por 40 segundos

            // Fecha o socket após a espera
            client.close();
            System.out.println("Conexão com o cliente fechada após 40 segundos.");
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro no tratamento do cliente: " + e.getMessage());
        }
    }
}
