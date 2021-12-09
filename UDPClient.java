import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPClient {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DatagramSocket skt;
        try{
            skt = new DatagramSocket();
            InetAddress host = InetAddress.getByName("127.0.0.1");
            int port = 3000;
            while(true){
                System.out.println("Client");
                String msg = sc.nextLine();
                byte sendBytes[] = msg.getBytes();
                DatagramPacket request = new DatagramPacket(sendBytes, sendBytes.length,host,port);
                skt.send(request);
                byte buffer[] = new byte[1024];
                DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
                skt.receive(reply);
                System.out.println("Server :"+new String(reply.getData()));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
