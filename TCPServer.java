import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        Socket s = null;
        try {
            ss = new ServerSocket(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Server Ready ...");
                s = ss.accept();
                System.out.println("Client Connected!");
                InputStream istream = s.getInputStream();
                Scanner fread = new Scanner(new InputStreamReader(istream));
                String fileName = fread.nextLine();
                System.out.println("Reading contents of " + fileName);
                Scanner contentRead = new Scanner(new FileReader(fileName));
                OutputStream ostream = s.getOutputStream();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                while (contentRead.hasNext())
                    pwrite.println(contentRead.next());
                pwrite.close();
                s.close();
                
            } catch (Exception e) {
                System.out.println("File Not Found!");
                OutputStream ostream = s.getOutputStream();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                pwrite.println("File Not Found!");
                pwrite.close();
            }
        }
    }
}
