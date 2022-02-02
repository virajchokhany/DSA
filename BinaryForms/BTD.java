import java.util.Scanner;

public class BTD
{
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {

        int n=sc.nextInt();
        System.out.println(binaryToDecimal(n));
    }
    private static int binaryToDecimal(int n) 
    {
        int pwr=0;
        int ans=0;
        while(n!=0)
        {
            int d=n%10;
            n/=10;
            ans+=Math.pow(2,pwr)*d;
            pwr++;
        } 
        return ans;
    }
}