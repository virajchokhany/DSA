public class stack
{
    private int[] arr;
    private int tos;
    private int maxCapacity;
    private int noOfElements;

    public stack(int size) 
    {
        initialise(size);
    }

    public stack() 
    {
        this(10);
    }

    protected void initialise(int size)
    {
        maxCapacity=size;
        noOfElements=0;
        tos=-1;
        arr=new int[size];
    }

    public void push(int val) throws Exception
    {
        if(noOfElements==maxCapacity)
            throw new Exception("StackOverFlowException");
        arr[++tos]=val;
        noOfElements++;
    }

    public int peek() throws Exception
    {
        if(noOfElements==0)
            throw new Exception("StackUnderFlowException");
        
        return arr[tos];
    }

    public int pop() throws Exception
    {
        if(noOfElements==0)
            throw new Exception("StackUnderFlowException");
        
        int x=arr[tos--];
        noOfElements--;
        return x;
    }
}