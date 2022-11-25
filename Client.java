import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter the port number to connect to: <7777>");
        int port = sc.nextInt();
        System.out.print("Enter the host address to connect to: <localhost> ");
        String host = sc.next();
        sc.nextLine();

        try {
            // Connect to the ServerSocket at host:port
            Socket socket = new Socket(host, port);
            System.out.println("Connected to " + host + ":" + port);

            // Output stream socket.
            OutputStream outputStream = socket.getOutputStream();

            // Create object output stream from the output stream to send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = socket.getInputStream();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Message msg = new Message("login", "", "");
            objectOutputStream.writeObject(msg);
            msg = (Message) objectInputStream.readObject();
            if (msg != null) {
                System.out.println(msg.getType() + " " + msg.getStatus());
                if ("login".equalsIgnoreCase(msg.getType()) && "success".equalsIgnoreCase(msg.getStatus())) {
                    boolean running = true;
                    while (running) {
                        System.out.print("Enter text to send to the server: ");
                        String text = sc.nextLine();
                        if ("logout".equalsIgnoreCase(text)) {
                            msg = new Message("logout", "", "");
                            objectOutputStream.writeObject(msg);
                        } else {
                            msg = new Message("text", "", text);
                            objectOutputStream.writeObject(msg);
                        }
                        msg = (Message) objectInputStream.readObject();
                        if (msg != null) {
                            if ("logout".equalsIgnoreCase(msg.getType()) && "success".equalsIgnoreCase(msg.getStatus())) {
                                System.out.println(msg.getType() + " " + msg.getStatus());
                                running = false;
                            } else {
                                System.out.println("Server replied: " + msg.getText());
                            }
                        }
                    }
                    System.out.println("Closing socket");
                    socket.close();
                    sc.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
