import java.util.LinkedList;
public class stackUsingJavaLL {

    public static class stack
    {
        private LinkedList<Integer> ll=new LinkedList<>();

        public int size()
        {
            return this.ll.size();
        }

        public boolean isEmpty()
        {
            return this.ll.size()==0;
        }

        public void push(int data)
        {
            this.ll.addFirst(data);
        }

        public int top()
        {
            return this.ll.getFirst();
        }

        public int pop()
        {
            return this.ll.removeFirst();
        }

    }
    public static void main(String[] args) {
        
        stack st=new stack();
        System.out.println(st.size());
    }
}
