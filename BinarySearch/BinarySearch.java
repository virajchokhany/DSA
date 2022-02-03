public class BinarySearch
{
    // since the algorithm converges
    public static void ceilAndFloor(int a[],int d)
    {
        int n=a.length;
        int si=0,ei=n-1;
    
        while(si<=ei)
        {
            int m=(ei-si)/2+si;
            if(a[m]==d)
                break;
            else if(a[m]>d)
                ei=m-1;
            else
                si=m+1;
        }
        System.out.println("Ceil is "+ a[si]);
        
        System.out.println("Floor is "+ a[ei]);
    }
}