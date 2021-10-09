import java.util.LinkedList;

public class queueUsingJavaLL {
    
    public static class queue
    {
        private LinkedList<Integer> ll=new LinkedList<>();

        public int size()
        {
            return ll.size();
        }

        public boolean isEmpty()
        {
            return size()==0;
        }

        public void add(int data)
        {
            ll.addLast(data);
        }

        public int peek()
        {
            return ll.getFirst();
        }

        public int remove()
        {
            return ll.removeFirst();
        }
    }

    public static void main(String[] args) {
        
        queue q=new queue();
        System.out.println(q.remove());
    }
}
