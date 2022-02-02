import java.util.Scanner;

public class questions
{
    public static Scanner sc=new Scanner(System.in);

    public static void digitFreq(long n,int query[])
    {
        int digit[]=new int[10];
        while(n!=0){
            digit[(int)n%10]++;
            n/=10;
        }
        for(int i=0;i<query.length;i++)
        {
            System.out.println(digit[query[i]]);
        }
    }
    public static void main(String[] args) 
    {
        long n=sc.nextLong();
        int query[]=new int[sc.nextInt()];
        for(int i=0;i<query.length;i++)
            query[i]=sc.nextInt();
        digitFreq(n, query);
    }
}