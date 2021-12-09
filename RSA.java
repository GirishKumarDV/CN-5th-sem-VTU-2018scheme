import java.util.*;

public class RSA {

    public int gcd(int a, int b){
        if(b==0)
            return a;
        return gcd(b,a%b);
    }

    public static boolean checkPrime(int num){
        if(num <= 0 || num==1)
            return false;
        else if(num == 2)
            return true;
        else
            for(int i = 2; i < num ; i++)
                if(num%i == 0)
                    return false; 
        return true;
        
        
    }

    public static void main(String args[]){
        RSA obj = new RSA();
        int primeNum1 =0,primeNum2=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message to be encrypted");
        String message = sc.nextLine();
        char[] messageArr = message.toCharArray();
        boolean check = false;
        while(!check){
            System.out.println("Enter 2 Prime Numbers");
            primeNum1 = sc.nextInt();
            primeNum2 = sc.nextInt();
            if(checkPrime(primeNum1) && checkPrime(primeNum2))
                check = true;
        }

        int n = primeNum1*primeNum2;
        int z = (primeNum1-1)*(primeNum2-1);
        int e = 0;
        for(int i = 2; i < z ; i++){
            if(obj.gcd(i, z) == 1){
                e = i;
                break;
            }
        }

        System.out.print("The value of E : "+ e);
        System.out.print("\nPublic Key : ("+e+","+n+")\n");
        int d = 0;
        for(d = 2; d<z ; d++)
            if((e*d)%z == 1)
                break;
        
        System.out.println("d : "+d);
        int ch;

        int[] a = new int[message.length()];
        int[] c = new int [message.length()];
        int t = 0;
        
        for(int i = 0; i <message.length();i++){
            ch = (int)messageArr[i];
            a[i] = ch;
            t = 1;
            for(int j = 0 ;j < e ; j++)
                t = (t*ch)%n;
            c[i] = t;
        }

        System.out.println("Message ASCII Cipher");
        for(int i =0 ; i < message.length() ; i++){
            System.out.println(messageArr[i]+"\t"+a[i]+"\t"+c[i]);
        }
        System.out.println("Message Cipher");
        for(int i = 0 ; i < message.length() ; i++)
            System.out.print(c[i]);
        System.out.println();

        char cha[] = new char[message.length()];
        System.out.println("Decrypting...");

        for(int i = 0 ; i < message.length(); i++){
            ch = c[i];
            t = 1;
            for(int j = 0; j <d;j++)
                t = (t*ch)%n;
            cha[i] = (char)t;
        }
        System.out.println(new String(cha));
    }
}
