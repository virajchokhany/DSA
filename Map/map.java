import java.util.*;
public class map
{
    public static void CharacterIndex()
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();

        HashMap<Character,ArrayList<Integer>> hm=new HashMap<>();

       /*  for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);

            if(hm.get(ch)==null)
            {
                hm.put(ch,new ArrayList<>());
            }

            ArrayList<Integer> list=hm.get(ch);

            list.add(i);
        } */

        
       for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);

            hm.putIfAbsent(ch, new ArrayList<>());

            hm.get(ch).add(i);
        } 

        
        System.out.println(hm);
        
    }
    public static void CharacterFreq()
    {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();

        HashMap<Character,Integer> map=new HashMap<>();

        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);

            // if nothing is mapped to ch then default value 0 is obtained.
            // if value is matched then its value is returned.
            map.put(ch,map.getOrDefault(ch, 0)+1);
        }

        for(char ch:map.keySet())
        {
            System.out.println(ch+" -> "+map.get(ch));
        }
    }
    public static void longestConsecutiveElement(int a[])
    {
        HashSet<Integer> set=new HashSet<>();

        for(int x:a)
        {
            set.add(x);
        }
        int mxl=0;
        int start=0;
        for(int x:a)
        {
            if(set.size()==0)
                break;
            if(!set.contains(x))
            {
                continue;
            }

            int left=x-1;
            int right=x+1;
            set.remove(x);

            while(set.contains(left)){
                set.remove(left);
                left--;
            }
            while(set.contains(right)){
                set.remove(right);
                right++;
            }
            
            int l=right-left-1;
            if(l>mxl)
            {
                mxl=l;
                start=left+1;
            }
        }

        for(int i=1;i<=mxl;i++)
        {
            System.out.println(start+i-1);
        }
    }
    public static void main(String[] args) {
        //CharacterIndex();
        //CharacterFreq();
        
    }
}