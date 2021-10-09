public class client {

    public static void test()
    { 
        createHashMap map=new createHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        map.put(4, 40);
        map.put(5, 50);

        map.put(1,100);
    }

    
    public static void main(String[] args) {
        test();    
    }
    
}
