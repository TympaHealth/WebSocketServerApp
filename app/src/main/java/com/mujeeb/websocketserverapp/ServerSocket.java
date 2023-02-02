package com.mujeeb.websocketserverapp;


import android.util.Log;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;


public class ServerSocket extends WebSocketServer {

    private static String TAG = ServerSocket.class.getName();
    public static final int SERVERPORT = 8080;

    public ServerSocket() {
        super(new InetSocketAddress(SERVERPORT));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (message == null)
            return;
        Log.d(TAG, conn + ": " + message);
        if (conn != null) {
            Log.i("ServerSocket", "====client ip=====" + conn.getRemoteSocketAddress());
            try {
                while (true) {
                    conn.send("Server response!");
                    Thread.sleep(1000 / 15);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.i("ServerSocket", "===socket is null===");
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();

    }

    @Override
    public void onStart() {
        Log.d(TAG, "Server started!");
        setConnectionLostTimeout(0);
    }


}


