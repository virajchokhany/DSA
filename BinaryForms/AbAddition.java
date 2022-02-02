import java.util.Scanner;

public class AbAddition 
{
    public static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) 
    {
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        int base=sc.nextInt();

        System.out.println(anyBaseAddition(n1,n2,base));
    }
    private static int anyBaseAddition(int n1, int n2, int base) 
    {
        int ans=0;
        int pwr=0;
        int c=0;
        while(n1!=0 || n2!=0 || c!=0)
        {
            int d1=n1%10;
            int d2=n2%10;
            n1/=10;
            n2/=10;
            int sum=d1+d2+c;
            c=sum/base;
            ans+=Math.pow(10,pwr)*(sum%base);
            pwr++;
        }
        return ans;    
    }    
}
