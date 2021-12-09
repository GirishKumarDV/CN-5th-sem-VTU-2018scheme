import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UDPServer {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DatagramSocket skt = null;
        try{
            skt = new DatagramSocket(3000);
            System.out.println("Server is Ready");
            while(true){
                byte buffer[] = new byte[1024];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                skt.receive(request);
                String receivedMessage = new String(request.getData());
                System.out.println("Client : "+receivedMessage);
                System.out.println("Server : ");
                String sendMsg = sc.nextLine();
                byte sendBytes[] = sendMsg.getBytes();
                DatagramPacket reply = new DatagramPacket(sendBytes, sendBytes.length, request.getAddress(), request.getPort());
                skt.send(reply);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
