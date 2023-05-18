package exercise;
import exercise.connections.CONNECTION_STATE;
import exercise.connections.Connection;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection implements Connection {
    private String ip;
    private int port;

    private CONNECTION_STATE connectionState = CONNECTION_STATE.disconnected;


    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }


    public void connect() {
        if (connectionState == CONNECTION_STATE.connected) {
            System.out.println("Error! Connection already connected");
        } else {
            connectionState = CONNECTION_STATE.connected;
        }


    }

    public String getCurrentState() {
        return connectionState.name();
    }

    public void write(String one) {
        if (connectionState == CONNECTION_STATE.disconnected) {
            System.out.println("Error! not connected");
        }
    }

    public void disconnect() {
        if (connectionState == CONNECTION_STATE.disconnected) {
            System.out.println("Error! Connection already disconnected");
        } else {
            connectionState = CONNECTION_STATE.disconnected;
        }
    }
}
