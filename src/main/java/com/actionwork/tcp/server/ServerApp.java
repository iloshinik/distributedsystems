package com.actionwork.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Simple TCP Server Application Implementation.
 */

public class ServerApp {

    private static int SERVER_PORT = 9999;
    private static int QUEUE_SIZE = 100;
    private static int MIN_POOL_SIZE = 2;
    private static int MAX_POOL_SIZE = 100;
    private static long KEEP_ALIVE_TIME = 20;

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(SERVER_PORT);
        BlockingQueue queue = new ArrayBlockingQueue(QUEUE_SIZE);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(MIN_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, queue);


        System.out.println("\nConfigurations:\n");
        System.out.println("Minimum Pool Size: " + MIN_POOL_SIZE);
        System.out.println("Maximum Pool Size: " + executor.getMaximumPoolSize());
        System.out.println("Queue Size: " + QUEUE_SIZE);
        System.out.println("Keep Alive Time: " + KEEP_ALIVE_TIME + "sec");
        System.out.println("\nServer is running on port " + SERVER_PORT + "...");


        while (true) {

            Socket client = null;
            try {

                client = server.accept();
                executor.execute(new ClientHandler(client, executor));

            } catch (RejectedExecutionException e) {
                client.close();
            }
        }
    }
}
