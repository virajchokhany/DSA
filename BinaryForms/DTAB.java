import java.util.Scanner;

public class DTAB 
{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        int base=sc.nextInt();
        int n=sc.nextInt();
        System.out.println(decimalToAnyBase(base,n));
    }
    private static int decimalToAnyBase(int base, int n) 
    {
        int pwr=1;
        int ans=0;
        while(n!=0)
        {
            int r=n%base;
            n/=base;
            ans+=pwr*r;
            pwr*=10;
        }
        return ans;
    }    
}
