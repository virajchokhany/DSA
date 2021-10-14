public class heapSort
{
    
    public static void main(String[] args) 
    {
        int arr[]={10,-22,100,-10,6,55,11,14,9,6,8,-100};
        boolean isIncreasing=!true;
        int n=arr.length;

        // create a max heap
        //O(n)
        for(int i=n-1;i>=0;i--)
            downHeapify(arr,i,n-1,isIncreasing);
        
        int li=n-1;
        // O(n*logn)
        while(li>0)
        {
            swap(arr,0,li);
            li--;
            downHeapify(arr, 0, li, isIncreasing);
        }
    }

    private static void downHeapify(int[] arr, int pi, int li, boolean isIncreasing) 
    {
        int lci=2*pi+1;
        int rci=2*pi+2;
        int mxi=pi;

        if(lci<=li && compareTo(arr,lci,mxi,isIncreasing))
        {
            mxi=lci;
        }
        if(rci<=li && compareTo(arr, rci, mxi, isIncreasing))
        {
            mxi=rci;
        }

        if(mxi!=pi)
        {
            swap(arr,mxi,pi);
            downHeapify(arr, mxi, li, isIncreasing);
        }

    }

    private static void swap(int[] arr, int mxi, int pi) 
    {
        int x=arr[mxi];
        arr[mxi]=arr[pi];
        arr[pi]=x;
    }

    private static boolean compareTo(int arr[],int lci, int mxi, boolean isIncreasing) 
    {
        if(isIncreasing)
            return arr[lci]>arr[mxi];
        else
            return arr[mxi]>arr[lci];
    }
}