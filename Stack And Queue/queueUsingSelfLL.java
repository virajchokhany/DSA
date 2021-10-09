public class queueUsingSelfLL 
{
    
    public static class LinkedList
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

        private void addLast(int data)
        {
            if(head==null)
            {
                head=tail=new Node(data);
            }
            else
            {
                tail.next=new Node(data);
            }
            size++;
        }

        public void add(int data)
        {
            addLast(data);
        }

        private int getFirst()
        {
            if(head==null)
            {
                return -1;
            }
            else
                return head.data;
        }

        public int peek()
        {
            return getFirst();
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
    }
    public static void main(String[] args) {
        
        LinkedList ll=new LinkedList();

        ll.add(6);
        ll.add(8);
        System.out.println(ll.remove());
    }
}
