package com.example.siechoteli.server;

import com.example.siechoteli.klient.mainapp.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *This class is a main class for our main server.
 */
public class MainServer {




    /**
     * Prints simple message
     * In our program usually with serverPort number
     * @param message
     */
    private static void log(String message){
        System.out.println("[MainServer]:" + message );
    }

    /**
     * Main function of our MainServer class
     * In try catch block we do
     * try:
     * We create a new serverSocker instance with serverPort 25565
     * Then we have a while loop in which we
     * Create new Socker instance then log simple message, after that we create new objectInputStream and objectOutputStream
     * Later we read Message from our object input stream (which is waiting for client message)
     * Then we create new Thread which will handle a specific request.
     * This server can process multiple requests simultaneously - multithreading
     * @param args
     */
    public static void main(String[] args) {
        int serverPort = 25565;
        ServerSocket serverSocket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            log("MainServer running on port " + serverPort);
            serverSocket = new ServerSocket(serverPort);
            while (true) {
                // this line of code makes the MainServer to wait for connection
                Socket socket = serverSocket.accept();
                log("Connection open with " + socket.getRemoteSocketAddress());
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Message msgRequest = (Message) objectInputStream.readObject();
                new Thread(new ServerThread(msgRequest.header, msgRequest.arguments, objectOutputStream,
                        socket.getRemoteSocketAddress())).start();
                log("Connection closed with " + socket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            closeEverything(serverSocket, objectOutputStream, objectInputStream);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * This function closes (if they are not already closed)
     * objectOutputStream, objectInputStream and socket
     */
    public static void  closeEverything(ServerSocket socket, ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream){
        try{
            if(objectOutputStream != null){
                objectOutputStream.close();
            }if(objectInputStream != null){
                objectInputStream.close();
            }if(socket != null){
                socket.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
