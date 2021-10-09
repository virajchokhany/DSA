import java.util.*;


public class createHashMap {
    
    // ```````````````````````````````````````````````` DATA MEMBERS```````````````````````````````````````````

    private int bucketLen=0;
    private LinkedList<Node> Buckets[];
    private int NoOfEle=0;

    //`````````````````````````````````````````````````CLASS``````````````````````````````````````````````````

    private class Node  
    {
        Integer key;
        Integer value;
        Node(Integer key,Integer value)
        {
            this.key=key;
            this.value=value;
        }
    }

    
    //``````````````````````````````````````````````BASIC FUCNTIONS```````````````````````````````````````````````

    public int size()
    {
        return NoOfEle;
    }

    public boolean isEmpty()
    {
        return NoOfEle==0;
    }

    //````````````````````````````````````````` CONSTRUCTOR+ INITIALISATION```````````````````````````````````````````
    private void initialise(int size)
    {
        this.bucketLen=size;
        Buckets=new LinkedList[size];
        for(int i=0;i<size;i++)
            Buckets[i]=new LinkedList<Node>();
        NoOfEle=0;
    }

    public createHashMap()
    {
        initialise(10);
    }

    //`````````````````````````````````````````````````````````` DS FUNCTIONS`````````````````````````````````````````

    
    @Override
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("{");
        int t=NoOfEle;
        for(int i=0;i<bucketLen;i++)
        {
            LinkedList<Node> list=Buckets[i];
            int size=list.size();
            while(size-->0)
            {
                Node node=list.removeFirst();
                list.addLast(node);
                sb.append(node.key);
                sb.append("=");
                t--;
                sb.append(node.value);
                if(t!=0)
                    sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public void rehash()
    {
        LinkedList<Node> temp[]=Buckets;
        int n=bucketLen;
        initialise((int)(1.7*n));

        for(int i=0;i<n;i++)
        {
            LinkedList<Node> list=temp[i];
            int size=list.size();

            while(size-->0)
            {
                Node node=list.removeFirst();
                put(node.key,node.value);
            }
        }
    }

    public void put(Integer key,Integer value)
    {
        boolean ans=containsKey(key);
        LinkedList<Node> list=getGroup(key);
        if(ans==true)
        {   
            list.getFirst().value=value;
        }
        else
        {
            list.addLast(new Node(key, value));
            this.NoOfEle++;

            double lamda=list.size()/(1.0*bucketLen);
            if(lamda>0.4)
                rehash();
        }
    }

    public void putIfAbsent(Integer key,Integer value)
    {
        boolean ans=containsKey(key);
        if(!ans)
        {
            LinkedList<Node> list=getGroup(key);
            list.addLast(new Node(key, value));
            this.NoOfEle++;
        }
    }


    public Integer get(Integer key)
    {
        boolean ans=containsKey(key);
        LinkedList<Node> list=getGroup(key);
        if(ans==true)
        {
            return list.getFirst().value;
        }
        else
            return null;
    }

    public Integer getOrDefault(Integer key,Integer def)
    {
        boolean ans=containsKey(key);
        if(ans)
            return get(key);
        else
            return def;
    }


    public Integer remove(Integer key)
    {
        boolean ans=containsKey(key);
        LinkedList<Node> list=getGroup(key);
        if(ans)
        {
            Node node=list.getFirst();
            list.removeFirst();
            this.NoOfEle--;
            return node.key;
        }
        else
        {
            return null;
        }
    }

    public boolean containsKey(Integer key)
    {
        LinkedList<Node> list=getGroup(key);
        int size=list.size();

        while(size-->0)
        {
            if(list.getFirst().key.equals(key))
                return true;
            Node node=list.removeFirst();
            list.addLast(node);
        }
        return false;
    }

    public ArrayList<Integer> keySet()
    {
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<bucketLen;i++)
        {
            LinkedList<Node> group=Buckets[i];
            int size=group.size();
            while(size-->0)
            {
                Node node=group.removeFirst();
                group.addLast(node);
                list.add(node.key);
            }
        }
        return list;
    }
    private LinkedList<Node> getGroup(Integer key)
    {
        return Buckets[getHashCode(key)];
    }

    private int getHashCode(Integer key)
    {
        return Math.abs(key.hashCode())%bucketLen;  
    }
}
