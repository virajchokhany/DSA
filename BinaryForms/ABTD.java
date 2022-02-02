import java.util.Scanner;

public class ABTD 
{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        int base=sc.nextInt();
        int n=sc.nextInt();
        System.out.println(anyBaseToDecimal(base,n));
    }
    private static int anyBaseToDecimal(int base, int n) 
    {
        int pwr=0;
        int ans=0;
        while(n!=0)
        {
            int d=n%10;
            ans+=d*Math.pow(base,pwr);
            pwr++;
            n/=10;
        }
        return ans;
    }    
}
