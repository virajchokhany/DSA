public class lec1
{
    // 0->1 1->1
    public static void OffToOn(int n,int k)
    {
        n=n | (1<<k);
        System.out.println(n);
    }

    // 1->0  0->0
    public static void OnToOff(int n,int k)
    {
        n=(n & ~(1<<k));
        System.out.println(n);
    }

    // log(n) bits
    public static int countSetBits(int n)
    {
        int count=0;

        while(n!=0)
        {
            if((n & 1 )!=0)
            {
                count++;
            }
            n=n>>>1;
        }
        return count;
    }

    // takes no of bits
    public static int countSetBits_02(int n)
    {
        int count=0;
        while(n!=0)
        {
            n=(n & (n-1));
            count++;
        }
        return count;
    }


    public static int rightMostSetBit(int n)
    {
        return (n & (1+(~n)));
    }
    
    public static void main(String[] args) {
        
        System.out.println(countSetBits(0xFFFFFFFF));
    }
}