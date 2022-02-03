import java.util.Scanner;

public class AbAddition 
{
    public static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) 
    {
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        int base=sc.nextInt();

        // System.out.println(anyBaseAddition(n1,n2,base));
        // System.out.println(anyBaseSubtraction(n1, n2, base));
        System.out.println(anyBaseMultiplication(n1, n2, base));
    }

    private static int anyBaseSubtraction(int n,int m,int base)
    {
        int b=0;
        int pwr=1;
        int ans=0;
        while(n!=0)
        {
            int d1=n%10;
            int d2=m%10;
            if(d1-(d2+b)>=0)
            {
                ans+=pwr*(d1-d2-b);
                b=0;
            }
            else
            {
                ans+=pwr*(base+d1-d2-b);
                b=1;
            }
            n/=10;
            m/=10;
            pwr*=10;
        }
        return ans;
    }

    private static int anyBaseMultiplication(int n,int m, int base)
    {
        int ans=0;
        int pwr=1;
        while(m!=0)
        {
            int d=m%10;
            int res=pwr*mutlipleWithDigit(n,d,base);
            ans=anyBaseAddition(ans, res, base);
            pwr*=10;
            m/=10;
        }
        return ans;
    }

    private static int mutlipleWithDigit(int n, int d, int base) 
    {
        int ans=0;
        int pwr=1;
        int carry=0;
        while(n!=0 || carry!=0)
        {
            int digit=n%10;
            int prd=digit*d+carry;

            ans+=pwr*(prd%base);
            carry=prd/base;
            n/=10;
            pwr*=10;
        }
        return ans;
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
