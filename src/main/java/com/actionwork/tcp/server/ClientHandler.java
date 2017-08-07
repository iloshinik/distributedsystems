package com.actionwork.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Client Handler Thread Implementation.
 */

public class ClientHandler implements Runnable {

    private Socket client;
    private ThreadPoolExecutor executor;

    public ClientHandler(Socket client, ThreadPoolExecutor executor) {
        this.client = client;
        this.executor = executor;
    }

    @Override
    public void run() {

        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.write("------------------\n");
            out.write("Active Tasks: " + executor.getActiveCount() + "\n");
            out.write("Completed Tasks: " + executor.getCompletedTaskCount() + "\n");
            out.write("Total Number of Tasks: " + executor.getTaskCount() + "\n");
            out.write("Min Pool Size: " + executor.getPoolSize() + "\n");
            out.write("Max Pool Size: " + executor.getMaximumPoolSize() + "\n");
            out.write("Queue Size: " + executor.getQueue().size() + "\n");
            out.write("------------------\n");

            out.write("Server: Enter your name: ");
            out.flush();

            out.write("Server: Hello, " + in.readLine() + "\n\n");

            out.flush();

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
