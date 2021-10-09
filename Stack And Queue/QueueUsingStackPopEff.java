import java.util.LinkedList;
public class QueueUsingStackPopEff {
    
    LinkedList<Integer> st,temp;
    QueueUsingStackPopEff()
    {
        st=new LinkedList<>();
        temp=new LinkedList<>();
    }

    public boolean isEmpty()
    {
        return size()==0;
    }

    public int size()
    {
        return temp.size();
    }

    private void transfer(int x)
    {
        while(temp.size()>0)
        {
            st.addFirst(temp.removeFirst());
        }
        st.addFirst(x);
        while(st.size()>0)
        {
            temp.addFirst(st.removeFirst());
        }
    }

    public void add(int x)
    {
        transfer(x);

    }

    public int peek()
    {
        return temp.getFirst();
    }

    public int remove()
    {
        return temp.removeFirst();
    }
}
