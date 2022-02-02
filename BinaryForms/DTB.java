import java.util.Scanner;

public class DTB
{
    public static Scanner sc=new Scanner(System.in);
    
    public static int decimalToBinary(int n)
    {
        int pwr=1;
        int ans=0;
        while(n!=0)
        {
            int r=n%2;
            ans+=pwr*r;
            pwr*=10;
            n/=2;
        }
        return ans;
    }
    public static void main(String[] args) 
    {
        System.out.println(decimalToBinary(1000));
    }
}