package exercise.connections;

public interface Connection {
    void connect();

    String getCurrentState();

     void write(String one);

    void disconnect();


    //boolean send(String phone, String message);
}
