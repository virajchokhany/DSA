import java.util.HashMap;

public class topKFrequentEls 
{
    public static class pair
    {
        int key;
        int freq;
        pair(int key,int freq)
        {
            this.key=key;
            this.freq=freq;
        }
    }
    public static int[] topKFrequent(int[] nums, int k) 
    {
        HashMap<Integer,Integer> hm=new HashMap<>();
        // O(N)
        for(int ele:nums)
        {
            if(!hm.containsKey(ele))
                hm.put(ele, 1);
            else
                hm.put(ele,hm.get(ele)+1);
        }
        
        pair parr[]=new pair[hm.size()];
        int i=0;
        // O(N)
        for(int key:hm.keySet())
        {
            int rele=hm.get(key);
            pair p=new pair(key, rele);
            //hm.remove(key);
            parr[i++]=p;
        }

        boolean maxHeap=true;
        int n=parr.length;
        int li=n-1;
        i=n-1;
        // O(N)
        for(i=n-1;i>=0;i--)
            downHeapify(parr,i,li,maxHeap);
        int ans[]=new int[k];
        i=0;
        while(k>=1)
        {
            swap(parr,0,li);
            ans[i++]=parr[li].key;
            li--;
            downHeapify(parr, 0, li, maxHeap);
            k--;
        }
        return ans;
    }
    private static void downHeapify(pair[] parr, int pi, int li, boolean maxHeap) 
    {
        int lci=2*pi+1;
        int rci=2*pi+2;
        int mxi=pi;
        if(lci<=li && compareTo(parr,lci,mxi,maxHeap))
        {
            mxi=lci;
        }
        if(rci<=li && compareTo(parr, rci, mxi, maxHeap))
            mxi=rci;
        if(mxi!=pi)
        {
            swap(parr,mxi,pi);
            downHeapify(parr, mxi, li, maxHeap);
        }
    }
    private static void swap(topKFrequentEls.pair[] arr, int mxi, int pi) 
    {
        pair p=arr[mxi];
        arr[mxi]=arr[pi];
        arr[pi]=p;
    }
    private static boolean compareTo(topKFrequentEls.pair[] parr, int lci, int mxi, boolean maxHeap) 
    {
        if(maxHeap)
            return parr[lci].freq>parr[mxi].freq;
        else
            return parr[mxi].freq>parr[lci].freq;
    }
    
    public static void main(String[] args) {
        int[] nums={1};
        int k=1;
        int ans[]=topKFrequent(nums, k);
        for(int ele:ans)
        System.out.println(ele);
    }
}
