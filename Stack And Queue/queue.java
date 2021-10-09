public class queue
{
    // data members

    private int arr[];
    private int size;
    private int maxSize;
    private int front;
    private int back;

    // constructors

    protected void initialise(int len)
    {
        arr=new int[len];
        size=0;
        maxSize=len;
        front=0;
        back=0;
    }
    public queue()
    {
        this(10);
    }

    public queue(int len)
    {
        initialise(len);
    }

    protected int maxSize()
    {
        return maxSize;
    }
    
    // basic functions

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return this.size==0;
    }

    // Exceptions

    private void queueUnderflowException() throws Exception
    {
        if(size==0)
            throw new Exception("QueueUnderFlowException");
    }

    private void queueOverflowException() throws Exception
    {
        if(size==maxSize)
            throw new Exception("QueueOverFlowException");
    }

    // ds functions
    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        int x=front;
        sb.append("[");

        for(int i=1;i<=size;i++)
        {
            sb.append(arr[x]);
            x=(x+1)%maxSize;
            if(i!=size())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void add_(int data)
    {
        arr[back]=data;
        back=(back+1)%maxSize;
        size++;
    }
    public void add(int data) throws Exception
    {
        queueOverflowException();
        add_(data);
    }


    public int peek() throws Exception
    {
        queueUnderflowException();
        return arr[front];
    }


    private int remove_()
    {
        int x=arr[front];
        front=(front+1)%maxSize;
        size--;
        return x;
    }

    public int remove() throws Exception
    {
        queueUnderflowException();
        return remove_();
    }


    
}