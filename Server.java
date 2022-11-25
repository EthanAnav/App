import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

    public static void main(String[] args) {
        ServerSocket server = null;
        int port = 7777;
        try {
            server = new ServerSocket(port);
            server.setReuseAddress(true);
            System.out.println("ServerSocket awaiting connections...");
            boolean running = true;
            while (running) {
                Socket client = server.accept();
                ClientHandler clientSocket = new ClientHandler();
                clientSocket.socket = client;
                System.out.println("Connection start: " + client);
                Thread thread = new Thread(clientSocket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ClientHandler implements Runnable {

        Socket socket;

        public void run() {

            ObjectOutputStream objectOutputStream = null;
            ObjectInputStream objectInputStream = null;

            try {
                // get the outputstream of client
                OutputStream outputStream = socket.getOutputStream();
                objectOutputStream = new ObjectOutputStream(outputStream);

                // get the inputstream of client
                InputStream inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);

                Message msg = null;
                // loop until login message not received
                boolean running = true;
                while (running) {
                    msg = (Message) objectInputStream.readObject();
                    if (msg != null && "login".equalsIgnoreCase(msg.getType())) {
                        running = false;
                    }
                }
                msg.setStatus("success");
                objectOutputStream.writeObject(msg);

                running = true;
                while (running) {
                    msg = (Message) objectInputStream.readObject();
                    if (msg != null) {
                        // writing the received message from client
                        if (msg.getType().equalsIgnoreCase("logout")) {
                            msg.setStatus("success");
                            objectOutputStream.writeObject(msg);
                            running = false;
                        } else {
                            msg.setText(msg.getText().toUpperCase());
                            objectOutputStream.writeObject(msg);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Connection close: " + socket);
        }
    }
}
