
public class client {
    
    public static void main(String[] args) throws Exception{
        /* queue q=new queue(5);

        q.add(1);
        q.add(2);
        System.out.println(q);

        System.out.println(q.remove());
        System.out.println(q.size());
        System.out.println(q.remove());
        System.out.println(q.size());

        System.out.println(q.peek()); */
        
        /* q.add(6);
        q.add(8);

        System.out.println(q.remove()); */

        /* LinkedList ll=new LinkedList<>();

        ll.addFirst(10);
        System.out.println(ll.size()); */
        /* dynamicStack st=new dynamicStack(3);

        for(int i=1;i<=10;i++)
        {
            st.push(i*10);
        }
        System.out.println(st);
        System.out.println(st.size()); */


        dynamicQueue que=new dynamicQueue(3);

        for(int i=1;i<=10;i++)
        {
            que.add(i*10);
        }

        System.out.println(que);
    }
}
