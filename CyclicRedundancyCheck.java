

import java.util.Scanner;

public class CyclicRedundancyCheck {
    public static int dataword[] = new int[128] ,temp[] = new int[128], checksum[] = new int[128], dataword_len, generator[] = {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1}; //example case 
    public static int generator_len = generator.length;
    static void xor(){
        for(int i = 1 ; i < generator_len ; i++ )
            checksum[i] = (checksum[i]==generator[i]?0:1);
    }

    static void crc(){
        int i,j;
        for(i = 0 ; i < generator_len ; i++)
            checksum[i] = temp[i];
        do{
            if(checksum[0] == 1)
                xor();
            for(j = 0; j < generator_len-1 ; j++)
                checksum[j] = checksum[j+1];
            checksum[j] = temp[i++];
        }while(i <= dataword_len+generator_len-1);
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of Dataword : ");
        dataword_len = sc.nextInt();
        System.out.println("Enter Dataword");
        for(int i = 0; i < dataword_len ; i++){
            dataword[i] = sc.nextInt();
            temp[i] = dataword[i];
        }
        for(int i = dataword_len ; i < dataword_len+generator_len-1; i++)
            temp[i] = 0;
        
        System.out.println("\nModified Dataword");
        for(int i = 0 ; i < dataword_len + generator_len -1 ; i++)
            System.out.print(temp[i]+" ");
        
        crc();
        System.out.println();
        System.out.println("CHECKSUM");
        for(int i = 0; i < generator_len-1 ;i++)
            System.out.print(checksum[i]);
        System.out.println();

        //Attach checksum to dataword to make codeword
        for(int i = dataword_len ; i < dataword_len + generator_len -1 ; i++)
            temp[i] = checksum[i-dataword_len];
        
        System.out.println("CODEWORD");
        for(int i = 0; i < dataword_len + generator_len -1 ; i++)
            System.out.print(temp[i]+" ");
        System.out.println();

        int option;
        System.out.println("\nTest for Error Detection? 0(YES)\t1(NO)");
        option = sc.nextInt();

        if(option == 0){
            System.out.printf("Enter position between '%d' and '%d' where error has to be introduced : ",0,dataword_len-1);
            option = sc.nextInt();

            temp[option] = (temp[option]==0)?1:0;
            System.out.println("Dataword after error");
            for(int i = 0 ; i < dataword_len ;i++)
                System.out.print(temp[i]);
                System.out.println();
        }
        crc();
        int i;
        for(i = 0 ; i < generator_len-1 && checksum[i] != 1 ; i++);
        if(i < generator_len-1)
            System.out.println("ERROR DETECTED");
        else    
            System.out.println("NO ERROR DETECTED");
            sc.close();
    }
}