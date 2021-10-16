import java.util.HashMap;
public class SortByFreq
{
    // LC 451
    public String frequencySort(String s) 
    {
        HashMap<Character,Integer> hm=new HashMap<>();
        // freq map O(N)
        for(int i=0;i<s.length();i++)
        {
            if(!hm.containsKey(s.charAt(i)))
                hm.put(s.charAt(i), 1);
            else
                hm.put(s.charAt(i), hm.get(s.charAt(i))+1);
        }
        
        char chars[]=new char[hm.size()];
        int i=0;
        for(char ch:hm.keySet())
            chars[i++]=ch;
        int n=chars.length;
        int li=n-1;
        boolean maxHeap=true;
        // O(N) -> heap
        for(i=n-1;i>=0;i--)
            downHeapify(chars,i,li,maxHeap,hm);
        
        for(int j=1;j<n;j++)
        {
            swap(chars,0,li);
            li--;
            downHeapify(chars, 0, li, maxHeap, hm);
        }
        StringBuilder sb=new StringBuilder();
        for(int j=n-1;j>=0;j--)
        {
            int t=hm.get(chars[j]);
            for(int k=1;k<=t;k++)
            {
                sb.append(chars[j]);
            }
        }
        return sb.toString();    
    }

    private void downHeapify(char[] chars, int pi, int li, boolean maxHeap,HashMap<Character,Integer> hm) 
    {
        int lci=2*pi+1;
        int rci=2*pi+2;
        int mxi=pi;
        if(lci<=li && compareTo(chars,lci,mxi,maxHeap,hm))
            mxi=lci;
        if(rci<=li && compareTo(chars, rci, mxi, maxHeap, hm))
            mxi=rci;
        if(pi!=mxi)
        {
            swap(chars,pi,mxi);
            downHeapify(chars, mxi, li, maxHeap, hm);
        }
        
    }

    private void swap(char[] chars, int pi, int mxi) {
        char ch=chars[pi];
        chars[pi]=chars[mxi];
        chars[mxi]=ch;
    }

    private boolean compareTo(char[] chars, int lci, int mxi, boolean maxHeap, HashMap<Character, Integer> hm) 
    {
        if(maxHeap)
            return hm.get(chars[lci])>hm.get(chars[mxi]);
        else
            return hm.get(chars[mxi])>hm.get(chars[lci]);
    }
}