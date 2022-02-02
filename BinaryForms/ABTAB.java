import java.util.Scanner;

public class ABTAB 
{
    public static Scanner scn=new Scanner(System.in);

    public static void main(String[] args) 
    {
        int n = scn.nextInt();
        int sourceBase = scn.nextInt();
        int  destBase= scn.nextInt();    

        System.out.println(anyBaseToAnyBase(n,sourceBase,destBase));
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
    private static int anyBaseToAnyBase(int n, int sourceBase, int destBase) 
    {
        int tans=anyBaseToDecimal(sourceBase, n);
        int ans=decimalToAnyBase(destBase, tans);
        return ans;
    }    
}
