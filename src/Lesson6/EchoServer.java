package Lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Scanner scan;

    public EchoServer() {
        try {
            System.out.println("Сервер запущен");
            ServerSocket serverSocket = new ServerSocket(EchoConstants.SERVER_PORT);
            System.out.println("Сервер ожидает подключения");
            Socket socket = serverSocket.accept(); // перевод основного потока в режим ожидания, пока кто-нибудь не подключится
            System.out.println("Клиент подключился.");
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            this.scan = new Scanner(System.in);

            Thread thread1 = new Thread(() -> {
                readMessages();
            });

            Thread thread2 = new Thread(() -> {
                sendMessage();
            });
            thread1.start();
            thread2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        while(true) {
            String messageFromServer = scan.nextLine();
            if(messageFromServer == null) break;
            try {
                outputStream.writeUTF("Сервер: " + messageFromServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readMessages() {
        String messageFromClient = null;
        while(true) {
            try {
                messageFromClient = inputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert messageFromClient != null;
            if(messageFromClient.equals(EchoConstants.STOP_WORD)) break;
            System.out.println("Клиент: " + messageFromClient);
        }
    }

    public static void main(String[] args) {
        new EchoServer();
    }
}
