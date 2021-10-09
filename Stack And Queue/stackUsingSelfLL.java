public class stackUsingSelfLL 
{
    private class Node
    {
        int data;
        Node next;
        Node(int data)
        {
            this.data=data;
            next=null;
        }
    }    
    private Node head=null,tail=null;
    private int size=0;

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size()==0;
    }

    private void addFirst(int data)
    {
        if(head==null)
        {
            head=tail=new Node(data);
        }
        else
        {
            Node node =new Node(data);
            node.next=head;
            head=node;
        }
        size++;
    }
    public void add(int data)
    {
        addFirst(data);
    }

    private int removeFirst()
    {
        int x;
        if(head==null)
            x=-1;
        else if(head==tail)
        {
            x=head.data;
            head=tail=null;
        }
        else
        {
            x=head.data;
            head=head.next;
        }
        size--;
        return x;
    }
    public int remove()
    {
        return removeFirst();
    }

    private int getFirst()
    {
        if(head==null)
            return -1;
        else    
            return head.data;
    }
    public int top()
    {
        return getFirst();
    }

    public static void main(String[] args) {
        stackUsingSelfLL st=new stackUsingSelfLL();

        st.add(10);
        System.out.println(st.remove());
        System.out.println(st.size() );
    }
}
