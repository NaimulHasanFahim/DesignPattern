package Structural;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
# Provide a surrogate or placeholder for another object to control access to it.Use an extra level of indirection to support distributed,
controlled,or intelligent access. Add a wrapper and delegation to protect the real component from undue complexity.
*/

// 5. To support plug-compatibility between the wrapper and the target, create an interface

interface SocketInterface {
    String readLine();

    void writeLine(String str);

    void dispose();
}

class SocketProxy implements SocketInterface {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ServerSocket server;

    public SocketProxy(String host, int port, boolean wait) {
        try {
            if (wait) {
                server = new ServerSocket(port);
                socket = server.accept();
            } else {
                socket = new Socket(host, port);
            }
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readLine() {
        String str = null;
        try {
            str = in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void writeLine(String str) {
        out.println(str);
    }

    @Override
    public void dispose() {
        try {
            socket.close();
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

public class ProxyDemo {
    public static void main(String[] args) {
        // 3. The client deals with the wrapper
        SocketInterface socket = new SocketProxy("127.0.0.1", 8080, args[0].equals("first") ? true : false);
        String str;
        boolean skip = true;
        while (true) {
            if (args[0].equals("second") && skip) {
                skip = !skip;
            } else {
                str = socket.readLine();
                System.out.println("Receive - " + str);
                if (str.equals(null)) {
                    break;
                }
            }
            System.out.print("Send ---- ");
            str = new Scanner(System.in).nextLine();
            socket.writeLine(str);
            if (str.equals("quit")) {
                break;
            }
        }
        socket.dispose();
    }
}
