import java.util.PriorityQueue;
public class heapQuestions
{
    // NlogK
    public static int kthSmallest(int arr[],int k)
    {
        int n=arr.length;
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->
        {
            return b-a;
        });
        for(int i=0;i<n;i++)
        {
            pq.add(arr[i]);
            if(pq.size()>k)
                pq.remove();
        }
        return pq.peek();
    }

    //KLogN
    public static int kthSmallest_down_heapfiy(int arr[],int k)
    {
        int n=arr.length;
        boolean isIncreasing=false;
        int li=n-1;
        // O(N)
        for(int i=n-1;i>=0;i--)
        {
            downHeapify(arr,i,li,isIncreasing);
        }
        
        for(int i=1;i<=k;i++)
        {
            swap(arr,0,li);
            li--;
            downHeapify(arr, 0, li, isIncreasing);
        }

        return arr[n-k];
    }

    private static void downHeapify(int[] arr, int pi, int li, boolean isIncreasing) 
    {
        int lci=2*pi+1;
        int rci=2*pi+2;
        int mxi=pi;

        if(lci<=li && compareTo(arr,lci,mxi,isIncreasing))
            mxi=lci;

        if(rci<=li && compareTo(arr, rci, mxi, isIncreasing))
            mxi=rci;
        if(mxi!=pi)
        {
            swap(arr,mxi,pi);
            downHeapify(arr, mxi, li, isIncreasing);
        }
        
    }

    private static void swap(int[] arr, int mxi, int pi) {
        int t=arr[mxi];
        arr[mxi]=arr[pi];
        arr[pi]=t;
    }

    private static boolean compareTo(int arr[], int ci, int pi, boolean isIncreasing) 
    {
        if(isIncreasing)
        {
            return arr[ci]>arr[pi];
        }
        else
            return arr[pi]>arr[ci];
    }

    public static int kthLargest(int arr[],int k)
    {
        int n=arr.length;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<n;i++)
        {
            pq.add(arr[i]);
            if(pq.size()>k)
                pq.remove();
        }
        return pq.peek();
    }

    // LC 378
    public int kthSmallest_2D(int[][] matrix, int k) 
    {
        int n=matrix.length;
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            return matrix[a/n][a%n]-matrix[b/n][b%n];
        });
        
        for(int i=0;i<n;i++)
            pq.add(i*n);
        
        for(int i=1;i<k;i++)
        {
            int idx=pq.remove();
            int r=idx/n;
            int c=idx%n;
            if(c+1<n)
                pq.add(r*n+c+1);
        }
        int idx=pq.peek();
        return matrix[idx/n][idx%n];
    }
    public static void main(String[] args) {
        int arr[]={7,10,4,3,20,15};
        int k=4;
        System.out.println(kthSmallest_down_heapfiy(arr, k));
    }
}