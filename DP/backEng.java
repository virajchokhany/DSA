import java.util.*;
public class backEng 
{
    public static void display(int[] d)
    {
        for(int ele:d)
        {
            System.out.print(ele+" ");
        }
        System.out.println();
    }
    public static boolean wordBreak(String s, List<String> wordDict) 
    {
        HashSet<String> set=new HashSet<>();
        for(String str:wordDict)
        {
            set.add(str);
        }
        int dp[]=new int[s.length()+1];
        boolean res=wordBreak(s,wordDict,0,dp,set)==1;    
        display(dp);
        return res;
    }
    
    public static int wordBreak(String s,List<String> wordDict,int IDX,int dp[],HashSet<String> set)
    {
        for(int idx=s.length();idx>=0;idx--)
        {
            if(idx==s.length())
            {
                dp[idx]=1;
                continue;
            }
            
            boolean ans=false;
            for(int i=idx;i<s.length();i++)
            {
                if(set.contains(s.substring(idx,i+1)))
                {
                    ans=ans || (dp[i+1]==1);
                }
            }
            dp[idx]=ans?1:0;   
        }
        return dp[IDX];
    }  
    
    public static void main(String[] args)
    {
        String s = "pineapplepenapple";
        List<String> wordDict = new ArrayList<>();   
        wordDict.add("apple");
        wordDict.add("pen");
        wordDict.add("applepen");
        wordDict.add("pine");
        wordDict.add("pineapple");
        System.out.println(wordBreak(s, wordDict));
    }
}
