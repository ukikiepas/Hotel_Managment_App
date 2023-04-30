package com.example.siechoteli.klient.mainapp;

import java.io.*;
import java.util.*;


/**
 * As the name of this class says, we use it as a Message.
 * It is used to send data and information from client to server
 * and from server to client.
 *
 */
public final class Message implements Serializable
{

    public String header;
    public List<String> arguments;
    public Message(String header, List<String> arguments)
    {
        this.header = header;
        this.arguments = arguments;
    }
}