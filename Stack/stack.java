public class stack
{
    private int arr[];
    private int size;
    private int tos;
    private int maxSize;

    private void initialise(int len)
    {
        arr=new int[len];
        size=0;
        tos=-1;
        maxSize=len;
    }
    public stack()
    {
        this(5);
    }
    public stack(int len)
    {
        initialise(len);
    }

    public boolean isEmpty()
    {
        return this.size==0;
    }

    public int size()
    {
        return size;
    }
    private void stackOverflowException() throws Exception
    {
        if(size==maxSize)
            throw new Exception("StackOverflowException");
    }
    private void push_(int data)
    {
        arr[++tos]=data;
        size++;
    }
    public void push(int data) throws Exception
    {
        stackOverflowException();
        push_(data);
    }
    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("[");

        int x=tos;
        while(x>=0)
        {
            sb.append(arr[x]);
            if(x!=0)
            {
                sb.append(", ");
            }
            x--;
        }
        sb.append("]");
        return sb.toString();
    }
    private void stackUnderFlowException() throws Exception
    {
        if(size==0)
            throw new Exception("StackUnderflowException");
    }
    public int peek() throws Exception
    {
        stackUnderFlowException();
        return arr[tos];
    }

    private int pop_()
    {
        int x=arr[tos];
        arr[tos--]=0;
        size--;
        return x;
    }
    public int pop() throws Exception
    {
        stackUnderFlowException();
        return pop_();
    }
}