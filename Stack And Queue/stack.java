
public class stack
{
    private int arr[];
    private int maxSize;
    private int size;
    private int tos;

    // constructors

    protected void initialise(int len)
    {
        arr=new int[len];
        maxSize=len;
        size=0;
        tos=-1;
    }

    stack()
    {
        this(5);
    }

    stack(int len)
    {
        initialise(len);
    }

    // basic  functions

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size()==0;
    }

    public int maxsize()
    {
        return maxSize;
    }

    // Exceptions

    private void StackUnderFlowException() throws Exception
    {
        if(size()==0)
        {
            throw new Exception("StackUnderFlowException");
        }
    }

    private void StackOverFlowException() throws Exception
    {
        if(size()==maxSize)
        {
            throw new Exception("StackOverFlowException");
        }
    }

    // ds functions

    public void push(int data) throws Exception
    {
        StackOverFlowException();
        tos++;
        size++;
        arr[tos]=data;
    }

    public int top() throws Exception
    {
        StackUnderFlowException();
        return arr[tos];
    }

    public int pop() throws Exception
    {
        StackUnderFlowException();
        int rn=arr[tos];
        tos--;
        size--;
        return rn;
    }

    @Override
    public String toString()
    {
        int x=tos;
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        while(x>=0)
        {
            sb.append(arr[x]);
            if(x!=0)
                sb.append(", ");
            x--;
        }
        sb.append("]");
        return sb.toString();
    }
}