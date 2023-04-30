package com.example.siechoteli.klient.mainapp;

import java.io.*;
import java.net.*;


/**
 * Here is a Class called Client which is used when we want to
 * get in a conection with a server and then send a request/data to it.
 * We have here 2 necessary variables. We define them using obligatory Constructor -> serverPort and hostName;
 */
public class Client {

        private int serverPort;
        private String hostName;

        public void setServerPort(int serverPort) {
            this.serverPort = serverPort;
        }

        public int getServerPort() {
            return serverPort;
        }

        public void setHostName(String hostName) {
            this.hostName = hostName;
        }

        public String getHostName() {
            return hostName;
        }

        public Client(int serverPort, String hostName) {
            this.serverPort = serverPort;
            this.hostName = hostName;
        }


    /**
     * This is a main
     * @param messageToSend -> This is message of Message object that we send to our server.
     * Please take a look at Message class in {@link com.example.siechoteli.klient.mainapp.Message}
     * Here we are 1. create new socket, 2. create objectOutputStream and ojbectInputStream then
     *                      we create a Message object.
     *                      After that we are trying to create new Socket object with data that we passed via constructor.
     *                      Later we send a message to our Server with objectOutputStream (server is waiting for this message) getInputStream is blocking operation.
     *                      Then for safety purposes we flush our outputStream.
     *                      We are waiting for response from server and save it to replyMessage.
     *                      Then we can use data from relpyMessage as we want
     * @return Message object
     */
    public Message executeRequest(Message messageToSend)
        {
            Socket socket = null;
            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;
            Message replyMessage = null;
            try {
                socket = new Socket(hostName, serverPort);
                objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                objectOutputStream.writeObject(messageToSend);
                objectOutputStream.flush();
                objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                replyMessage = (Message) objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            }
            finally {
                if(socket != null) {
                    try {
                        socket.close();
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return replyMessage;
        }
    }

