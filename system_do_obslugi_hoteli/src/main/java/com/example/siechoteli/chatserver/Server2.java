package com.example.siechoteli.chatserver;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class is used for server and gives implementation for our sending and receiving methods.
 */
public class Server2 {

private ServerSocket serverSocket;
private Socket socket;
private BufferedReader bufferedReader;
private BufferedWriter bufferedWriter;

    /**
     * Here is basic constructor to our Server2 class.
     * We
     * @param serverSocket
     */
    public Server2(ServerSocket serverSocket) {
        try{
            this.serverSocket = serverSocket;
            this.socket = serverSocket.accept();
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("LiveChatSerwer is running");
        }catch (IOException e){
            System.out.println("ERROR WITH SERVER CREATING");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Here we are writing new message to buffer and then
     * flushing it (cleaning)
     * @param messageToClient
     */
    public void sendMessageToClient(String messageToClient){
        try{
            bufferedWriter.write(messageToClient);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error sending message to the client");
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Here is a blocking function which make a seperate thred and then
     * waits for message from client.
     * @param vBox
     */
    public void receiveMessageFromClient(VBox vBox){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        //blocking operation !!
                        String messageFromClient = bufferedReader.readLine();
                        Controller.addLabel(messageFromClient, vBox);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error receiving message from the client");
                        closeEverything(socket, bufferedReader, bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    /**
     * This functions closes everything to make sure, that we "cleaned after job". :)
     * @param socket
     * @param bufferedReader
     * @param bufferedWriter
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(bufferedWriter!= null){
                bufferedWriter.close();
            }
            if(socket!=null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
