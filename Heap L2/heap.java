import java.util.ArrayList;
public class heap
{
    private ArrayList<Integer> arr;

    public heap() 
    {
        this.arr=new ArrayList<>();
    }

    // O(n)
    public heap(int arr[])
    {
        this();
        for(int ele:arr)
            this.arr.add(ele);
        
        for(int i=this.arr.size()-1;i>=0;i--)
        {
            downHeapify(i);
        }
    }

    private boolean compareTo(int i,int j) 
    {
        return this.arr.get(i)>this.arr.get(j);
    }

    // Log(n)
    private void downHeapify(int pi) 
    {
        int lci=2*pi+1;
        int rci=2*pi+2;
        int mxi=pi;

        if(lci<this.arr.size() && compareTo(lci,mxi))
            mxi=lci;
        
        if(rci<this.arr.size() && compareTo(rci,mxi))
            mxi=rci;
        
        if(pi!=mxi)
        {
            swap(pi,mxi);
            downHeapify(mxi);
        }
    }

    private void swap(int pi, int mxi) 
    {
        int v1=this.arr.get(pi);
        int v2=this.arr.get(mxi);
        this.arr.set(pi, v2);
        this.arr.set(mxi, v1);
    }

    public int peek()
    {
        return this.arr.get(0);
    }
    
    // Log(n)
    public int remove()
    {
        int re=this.arr.get(0);
        swap(0,this.arr.size()-1);
        this.arr.remove(this.arr.size()-1);
        downHeapify(0);
        return re;
    }

    public int size()
    {
        return this.arr.size();
    }

    public void add(int x)
    {
        this.arr.add(x);
        upHeapify(this.arr.size()-1);
    }

    private void upHeapify(int idx) 
    {
        int pi=(idx-1)/2;
        if(compareTo(idx, pi))
        {
            swap(idx,pi);
            upHeapify(pi);
        }

    }
}