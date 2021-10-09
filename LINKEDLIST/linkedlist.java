
public class linkedlist
{
    private class Node
    {
        int data;
        Node next;
        Node(int data)
        {
            this.data=data;
        }
    }

    private Node head=null;
    private Node tail=null;
    private int size=0;

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return this.size==0;
    }

    private void addLastNode(Node node)
    {
        if(this.size==0)
        {
            this.head=this.tail=node;
        }
        else
        {
            this.tail.next=node;
            this.tail=node;
        }
        this.size++;
    }

    public void addLast(int data)
    {
        Node node=new Node(data);
        addLastNode(node);
    }

    private void addFirstNode(Node node)
    {
        if(this.size==0)
        {
            this.head=this.tail=node;
        }
        else
        {
            node.next=this.head;
            this.head=node;
        }
        this.size++;
    }

    public void addFirst(int data)
    {
        Node node=new Node(data);
        addFirstNode(node);
    }
    
    private Node removeFirstNode()
    {
        if(this.size==0)
        {
            return null;
        }
        Node node=this.head;
        this.size--;
        if(this.size==1)
        {
            this.head=this.tail=null;
        }
        else
        {
            this.head=this.head.next;
        }
        node.next=null;
        return node;
    }
    public int removeFirst()
    {
        Node node=removeFirstNode();
        if(node==null)
            return -1;
        else    
            return node.data;
    }
    private Node removeLastNode()
    {
        Node node=this.tail;
        if(this.size==1)
        {
            this.head=this.tail=null;
        }
        else
        {
            Node curr=this.head;

            while(curr.next!=tail)
            {
                curr=curr.next;
            }
            curr.next=null;
            this.tail=curr;
        }
        this.size--;
        return node;
    }
    public int removeLast()
    {
        if(this.size==0)
            return -1;
        Node node=removeLastNode();
        return node.data;
    }

    private Node getFirstNode()
    {
        return this.head;
    }
    public int getFirst()
    {
        if(this.size==0)
            return -1;
        Node node=getFirstNode();
        return node.data;
    }
    private Node getLastNode()
    {
        return this.tail;
    }
    public int getLast()
    {
        if(this.size==0)
            return -1;
        return getLastNode().data;
    }
    private Node getAtNode(int idx)
    {
        if(idx<0 || idx>=this.size)
            return null;
        
        Node curr=this.head;

        for(int i=1;i<=idx;i++)
            curr=curr.next;
        
        return curr;
    }
    public int getAt(int idx)
    {
        Node node=getAtNode(idx);
        if(node==null)
            return -1;
        return node.data;
    }

    public void addAt(int data,int idx)
    {
        if(idx<0 || idx>this.size)
            return;
        if(idx==0)
            addFirst(data);
        else if(idx==this.size)
            addLast(data);
        else
        {
            Node curr=this.head;

            while(idx-->1)
            {
                curr=curr.next;
            }
            Node node=new Node(data);
            curr.next=node;
            this.size++;
        }
    }

    @Override
    public String toString()
    {
        Node c=this.head;
        StringBuilder sb=new StringBuilder();
        while(c!=null)
        {
            sb.append(c.data);
            if(c.next!=null)
                sb.append(", ");
            c=c.next;
        }
        return sb.toString();
    }
    public int removeAt(int idx)
    {
        if(idx<0 || idx>this.size)
            return -1;
        else if(idx==0)
            return removeFirst();
        else if(idx==this.size-1)
            return removeLast();
        else
        {
            Node curr=this.head;
            while(idx-->1)
            {
                curr=curr.next;
            }

            Node node=curr.next;
            curr.next=node.next;
            node.next=null;
            this.size--;
            return node.data;
        }
    }


}