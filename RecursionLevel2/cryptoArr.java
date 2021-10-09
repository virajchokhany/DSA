import java.util.ArrayList;

public class cryptoArr {

    public static int getValue(String s,Integer val[])
    {
        int c=0;
        int ten=1;
        for(int i=s.length()-1;i>=0;i--)
        {
            char ch=s.charAt(i);
            if(i==0 && val[ch-'A'].equals(0) && s.length()>1)
                return -1;
            c+=ten*val[ch-'A'];
            ten*=10;
        }
        return c;
    }
    public static boolean equals(String words[],String result,Integer val[])
    {
        int lhs=0;
        for(int i=0;i<words.length;i++)
        {
            String s=words[i];
            int ans=getValue(s,val);
            if(ans==-1)
                return false;
            lhs+=ans;
        }
        int rhs=getValue(result,val);
        return lhs==rhs?true:false;
    }

    public static boolean solve(int idx,String ustring,Integer val[],String words[],String result, boolean used[])
    {
        if(idx==ustring.length())
        {
            if(equals(words,result,val)==true)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        char ch=ustring.charAt(idx);
        for(int i=0;i<=9;i++)
        {
            if(used[i]==false)
            {
                used[i]=true;
                val[ch-'A']=i;
                boolean res=solve(idx+1,ustring,val,words,result,used);
                used[i]=false;
                if(res==true)
                    return true;

            }
        }
        return false;
    }
    public static boolean isSolvable(String[] words, String result) 
    {
        String ustring="";
        boolean arr[]=new  boolean[26];
        
        int n=words.length;
        
        for(int i=0;i<n;i++)
        {
            String s=words[i];
            int x=s.length();
            
            for(int j=0;j<x;j++)
            {
                char ch=s.charAt(j);
                if(arr[ch-'A']==false)
                {
                    arr[ch-'A']=true;
                    ustring+=ch;
                }
            }
        }
        
        n=result.length();
        for(int j=0;j<n;j++)
        {
            char ch=result.charAt(j);
            if(arr[ch-'A']==false)
            {
                arr[ch-'A']=true;
                ustring+=ch;
            }
        }
        
        
        return solve(0,ustring,new Integer[26],words,result,new boolean[10]);
        
    }
    
    
    public static void main(String[] args) {
        
        String words[]={"A","B"};
        String result="A";

        boolean ans=isSolvable(words, result);
        System.out.println(ans);
    }
    
}
