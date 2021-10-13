public class client
{
    public static void main(String[] args) {
        int arr[]={10,22,0,-5,99};
        heap h=new heap(arr, true);
        h.add(48);
        h.add(1000);
        h.add(-100);
        while(h.size()>0){
            System.out.println(h.remove());
        }
    }
}