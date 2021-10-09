public class dynamicStack extends stack{
    
    public dynamicStack()
    {
        super();
    }

    public dynamicStack(int len)
    {
        super(len);     // constructor
    }

    @Override
    public void push(int data) throws Exception
    {
        if(super.maxsize()==super.size())
        {
            int narr[]=new int[size()];
            int i=0;
            while(size()>0)
            {
                narr[i++]=pop();
            }
            initialise(2*maxsize());
            
            while(i>0)
            {
                i--;
                push(narr[i]);
            }
        }

        super.push(data);

    }
}
