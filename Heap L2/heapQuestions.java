import java.util.PriorityQueue;
public class heapQuestions
{
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

    public static void main(String[] args) {
        int arr[]={7,10,4,3,20,15};
        int k=4;
        System.out.println(kthSmallest(arr, k));
    }
}