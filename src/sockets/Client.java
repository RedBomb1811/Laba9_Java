package sockets;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Client side");

        Socket fromserver = null;
        String serverName;

        if (args.length == 0) {
            serverName = "localhost";
            System.out.println("use: localhost");
//            System.exit(-1);
        }
        else
            serverName = args[0];

        System.out.println("Connecting to... " + (args.length == 0 ? serverName : args[0]));

        fromserver = new Socket(serverName, 1811);
        BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
        PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);
        BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

        String fuser, fserver;

        while ((fuser = inu.readLine()) != null) {
            out.println(fuser);
            fserver = in.readLine();
            System.out.println(fserver);
            if (fuser.equalsIgnoreCase("close")) break;
            if (fuser.equalsIgnoreCase("exit")) break;
        }

        out.close();
        in.close();
        inu.close();
        fromserver.close();
    }
}
