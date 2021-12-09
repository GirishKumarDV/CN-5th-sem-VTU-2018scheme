import java.util.Scanner;

public class LeakyBucketAlgorithm {
    public static void main(String args[]){
        int bucketSize,outputRate,memoryUsedAfterDrop, memoryUsed = 0,arrivalTime = 0, lastConformedPacketTime = 0,packetSize;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Bucket Size");
        bucketSize = sc.nextInt();
        System.out.println("Enter the output rate");
        outputRate = sc.nextInt();
        while(true){
            System.out.println("Enter time of arrival");
            arrivalTime = sc.nextInt();
            if(arrivalTime < 0){
                System.out.println("EXIT");
                System.exit(0);
            }
            System.out.println("Enter the packet Size");
            packetSize = sc.nextInt();
            if(packetSize > bucketSize){
                System.out.println("Non Conformal Packet");
                continue;
            }
            else{
                memoryUsedAfterDrop = memoryUsed - (arrivalTime-lastConformedPacketTime)*outputRate;
                if(memoryUsedAfterDrop <0){
                    memoryUsedAfterDrop = 0;
                    memoryUsed = memoryUsedAfterDrop + packetSize;
                    lastConformedPacketTime = arrivalTime;
                    System.out.println("Conformal Packet");
                }
                else{
                    if(memoryUsedAfterDrop+packetSize <= bucketSize){
                        memoryUsed = memoryUsedAfterDrop+packetSize;
                        lastConformedPacketTime = arrivalTime;
                        System.out.println("Conformal Packet");
                    }
                    else
                        System.out.println("Non-Conformal Packet");
                }
            }
        }
        
    }
}
