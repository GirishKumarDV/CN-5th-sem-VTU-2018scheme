import java.net.*;
import java.util.Scanner;
import java.io.*;

public class TCPClient {
    public static void main(String args[]){
        Socket s = null;
        while (true) {
            try {
                s = new Socket("127.0.0.1", 3000);
                OutputStream ostream = s.getOutputStream();
                System.out.println("Enter File name to be read");
                Scanner sc = new Scanner(System.in);
                String fname = sc.nextLine();
                PrintWriter pwrite = new PrintWriter(ostream, true);
                pwrite.println(fname);
                InputStream istream = s.getInputStream();
                Scanner contentRead = new Scanner(new InputStreamReader(istream));
                while (contentRead.hasNext())
                    System.out.println(contentRead.nextLine());
                
                pwrite.close();
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
