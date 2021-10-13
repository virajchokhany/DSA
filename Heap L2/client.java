public class client
{
    public static void main(String[] args) {
        heap h=new heap();
        h.add(10);
        h.add(22);
        h.add(0);
        h.add(-5);
        h.add(99);

        while(h.size()>0)
        {
            System.out.println(h.remove());
        }
    }
}