
public class dynamicQueue extends queue
{
    
    dynamicQueue()
    {
        super();
    }

    dynamicQueue(int len)
    {
        super(len);
    }

    @Override
    public void add(int data) throws Exception
    {
        if(size()==maxSize())
        {
            int narr[]=new int[size()];
            int i=0;
            while(size()>0)
            {
                narr[i++]=remove();
            }
            initialise(2*maxSize());
            for(int ele:narr)
            {
                super.add(ele);
            }
        }
        super.add(data);
    }


}
