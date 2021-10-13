import java.util.*;
public class cut 
{
    public static void display(int[] d)
    {
        for(int ele:d)
        {
            System.out.print(ele+" ");
        }
        System.out.println();
    }

    public static void display2D(int[][] dp)
    {
        for(int []d:dp)
        {
            display(d);
        }
        System.out.println();
    }
    

    //``````````````````````````````````````````````````````````````````````````````````````````````````````````````````
    // matrix chain multiplication
    public static int mcm(int p[])
    {
        int n=p.length;
        int dp[][]=new int[n][n];
        int ans=mcm_tabu(p,0,n-1,dp);
        String sdp[][]=new String[n][n];
        System.out.println(printMCMBrackets_tabu(p, 0, n-1, dp,sdp));
        return ans;
    }
    // memoisation
    public  static int mcm(int[] p, int i, int j,int dp[][]) 
    {
        if(j-i==1)
            return dp[i][j]=0;
        if(dp[i][j]!=-1)
            return dp[i][j];
        int mn=(int)1e9;
        for(int k=i+1;k<j;k++)
        {
            int l=mcm(p,i,k,dp);
            int r=mcm(p,k,j,dp);
            mn=Math.min(mn,l+r+p[i]*p[j]*p[k]);
        }
        return dp[i][j]=mn;
    }
    // tabulation
    public  static int mcm_tabu(int[] p, int I, int J,int dp[][]) 
    {
        int n=dp.length;
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(j-i==1)
                {
                    dp[i][j]=0;
                    continue;
                }
                
                int mn=(int)1e9;
                for(int k=i+1;k<j;k++)
                {
                    int l=dp[i][k]; //mcm(p,i,k,dp);
                    int r=dp[k][j]; //mcm(p,k,j,dp);
                    mn=Math.min(mn,l+r+p[i]*p[j]*p[k]);
                }
                dp[i][j]=mn;
            }
        }
        return dp[I][J];
    }
  
    //`````````````````````````````````````````````````````````````````````````````````````````````````````````````
    // print brackets memoisation
    public static String printMCMBrackets(int p[],int i,int j,int dp[][],String sdp[][])
    {
        
        if(j-i==1)
        {
            int x=(int)('A');
            x+=i;
            return sdp[i][j]=(char)(x)+"";
        }
        if(sdp[i][j]!=null)
            return sdp[i][j];
        int mn=(int)1e9;
        String ans="";
        for(int k=i+1;k<j;k++)
        {
            if(mn>dp[i][k]+dp[k][j]+p[i]*p[j]*p[k])
            {
                mn=dp[i][k]+dp[k][j]+p[i]*p[j]*p[k];
                ans="("+printMCMBrackets(p, i, k, dp,sdp)+printMCMBrackets(p, k, j, dp,sdp)+")";
            }
        }
        return sdp[i][j]=ans;
    }

    // print brackets tabulation
    public static String printMCMBrackets_tabu(int p[],int I,int J,int dp[][],String sdp[][])
    {
        int n=p.length;
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(j-i==1)
                {
                    int x=(int)('A');
                    x+=i;
                    sdp[i][j]=(char)(x)+"";
                    continue;
                }
                
                int mn=(int)1e9;
                String ans="";
                for(int k=i+1;k<j;k++)
                {
                    if(mn>dp[i][k]+dp[k][j]+p[i]*p[j]*p[k])
                    {
                        mn=dp[i][k]+dp[k][j]+p[i]*p[j]*p[k];
                        ans="("+sdp[i][k]+sdp[k][j]+")";
                    }
                }
                sdp[i][j]=ans;
            }
        }
        return sdp[I][J];
    }

    

    //``````````````````````````````````````````````````````````````````````````````````````````````````````````````
    public static int minScoreTriangulation(int[] values)
    {
        int n=values.length;
        int dp[][]=new int[n][n];
        for(int ar[]:dp)
            Arrays.fill(ar,-1);
        int ans=getCuts(0,n-1,values,dp);  
        display2D(dp);
        return ans;  
    }
    
    public static int getCuts(int i,int j,int val[],int dp[][])
    {
        if(j-i<2)
            return dp[i][j]=0;
        
        if(dp[i][j]!=-1)
            return dp[i][j];
        
        int mn=(int)1e9;
        for(int k=i+1;k<j;k++)
        {
            int a=getCuts(i,k,val,dp);
            int b=getCuts(k,j,val,dp);
            mn=Math.min(mn,a+b+val[i]*val[j]*val[k]);
        }
        return dp[i][j]=mn;
    }    

    public int getCuts_tabu(int I,int J,int val[],int dp[][])
    {
        int n=val.length;
        for(int i=n-1;i>=0;i--)
        {
            for(int j=0;j<n;j++)
            {
                if(j-i<2)
                {
                    dp[i][j]=0;
                    continue;
                }
                
                int mn=(int)1e9;
                for(int k=i+1;k<j;k++)
                {
                    int a=dp[i][k]; //getCuts(i,k,val,dp);
                    int b=dp[k][j]; //getCuts(k,j,val,dp);
                    mn=Math.min(mn,a+b+val[i]*val[j]*val[k]);
                }
                dp[i][j]=mn;
            }
        }
        return dp[I][J];
    }

    //````````````````````````````````````````````````````````````````````````````````````````````````````````````````
    
    // min-max expression evaluation
    public static class pair
    {
        int min=(int)1e9;
        int max=-(int)1e9;
    }
    
    public static pair minMax(String str)
    {
        int n=str.length();
        pair dp[][]=new pair[n][n];
        return minMax_tabu(str,0,n-1,dp);
    }
    
    // memoisation
    private static pair minMax(String str, int i, int j,pair dp[][]) 
    {
        if(i==j)
        {
            pair base=new pair();
            base.max=base.min=str.charAt(i)-'0';
            return dp[i][j]=base;
        }
        if(dp[i][j]!=null)
            return dp[i][j];
        pair ans=new pair();
        for(int k=i+1;k<j;k=k+2)
        {
            pair l=minMax(str, i, k-1, dp);
            pair r=minMax(str, k+1, j, dp);
            if(str.charAt(k)=='+')
            {
                // min
                ans.min=Math.min(ans.min,l.min+r.min);
                //max 
                ans.max=Math.max(ans.max, l.max+r.max);
            }
            else
            {
                // min
                ans.min=Math.min(ans.min,l.min*r.min);
                //max 
                ans.max=Math.max(ans.max, l.max*r.max);
            }
        }
        return dp[i][j]=ans;
    }
    //tabulation
    private static pair minMax_tabu(String str, int I, int J,pair dp[][]) 
    {
        int n=dp.length;
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(i==j)
                {
                    pair base=new pair();
                    base.max=base.min=str.charAt(i)-'0';
                    dp[i][j]=base;
                    continue;
                }
                
                pair ans=new pair();
                for(int k=i+1;k<j;k=k+2)
                {
                    pair l=dp[i][k-1];  //minMax(str, i, k-1, dp);
                    pair r=dp[k+1][j];  //minMax(str, k+1, j, dp);
                    if(str.charAt(k)=='+')
                    {
                        // min
                        ans.min=Math.min(ans.min,l.min+r.min);
                        //max 
                        ans.max=Math.max(ans.max, l.max+r.max);
                    }
                    else
                    {
                        // min
                        ans.min=Math.min(ans.min,l.min*r.min);
                        //max 
                        ans.max=Math.max(ans.max, l.max*r.max);
                    }
                }
                dp[i][j]=ans;
            }
        }
        return dp[I][J];
    } 
   
    //```````````````````````````````````````````````````````````````````````````````````````````````````````````````
    
    
    //``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````
    // balloon burst LC- 312

    public static int maxCoins(int[] nums) 
    {
        int n=nums.length;
        int dp[][]=new int[n][n];
        // for(int d[]:dp)
        //     Arrays.fill(d, -1);
        return maxCoins_tabu(nums,0,n-1,dp);    
    }
    
    // memoisation
    private static int maxCoins_memo(int[] nums, int i, int j,int dp[][]) 
    {
        if(dp[i][j]!=-1)
            return dp[i][j];
        int mx=-(int)1e9;
        int l=i-1>=0?nums[i-1]:1;
        int r=j+1<nums.length?nums[j+1]:1;
        for(int k=i;k<=j;k++)
        {
            int left=0,right=0;
            if(i<=k-1)
                left=maxCoins_memo(nums, i, k-1,dp);
            if(k+1<=j)
                right=maxCoins_memo(nums, k+1, j, dp);
            int myCost=left+right+nums[k]*l*r;
            mx=Math.max(mx,myCost);
        }
        return dp[i][j]=mx;
    }

    // tabulation
    private static int maxCoins_tabu(int nums[],int I,int J,int dp[][])
    {
        int n=nums.length;
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                
                int mx=-(int)1e9;
                int l=i-1>=0?nums[i-1]:1;
                int r=j+1<nums.length?nums[j+1]:1;
                for(int k=i;k<=j;k++)
                {
                    int left=0,right=0;
                    if(i<=k-1)
                        left=dp[i][k-1];    //maxCoins_memo(nums, i, k-1,dp);
                    if(k+1<=j)
                        right=dp[k+1][j];   //maxCoins_memo(nums, k+1, j, dp);
                    int myCost=left+right+nums[k]*l*r;
                    mx=Math.max(mx,myCost);
                }
                dp[i][j]=mx;
            }
        }   
        return dp[I][J];
    }
    //```````````````````````````````````````````````````````````````````````````````````````````````````````````````````
    


    // `````````````````````````````````````````````````````````````````````````````````````````````````````````````
    // boolean parenthesization
    public static class boolClass
    {
        int trueWays;
        int falseWays;
    }
    public static int countWays(int N, String s)
    {
        boolClass dp[][]=new boolClass[N][N];
        return countWays_tabu(s, 0, N-1,dp).trueWays;
    }

    // memoisation
    private static boolClass countWays(String s, int i, int j, boolClass[][] dp) 
    {
        if(i==j)
        {
            boolClass base=new boolClass();
            base.trueWays=(s.charAt(i)=='T'?1:0);
            base.falseWays=(s.charAt(i)=='F'?1:0);
            return dp[i][j]=base;
        }

        if(dp[i][j]!=null)
            return dp[i][j];

        boolClass ways=new boolClass();
        for(int k=i+1;k<j;k+=2)
        {
            boolClass left=new boolClass();
            boolClass right=new boolClass();
            if(i<=k-1)
                left=countWays(s, i, k-1, dp);
            if(k+1<=j)
                right=countWays(s, k+1, j, dp);

            if(s.charAt(k)=='|')
            {
                ways.trueWays+=left.falseWays*right.trueWays+left.trueWays*right.falseWays+left.trueWays*right.trueWays;
                ways.falseWays+=left.falseWays*right.falseWays;
            }
            else if(s.charAt(k)=='&')
            {
                ways.trueWays+=left.trueWays*right.trueWays;
                ways.falseWays+=left.trueWays*right.falseWays+left.falseWays*right.trueWays+left.falseWays*right.falseWays;
            }
            else
            {
                ways.trueWays+=(left.falseWays*right.trueWays)+(left.trueWays*right.falseWays);
                ways.falseWays+=(left.falseWays*right.falseWays)+(left.trueWays*right.trueWays);
            }
        }   
        return dp[i][j]=ways;
    }

    // tabulation
    private static boolClass countWays_tabu(String s, int I, int J, boolClass[][] dp) 
    {
        int n=s.length();
        int mod=1003;
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(i==j)
                {
                    boolClass base=new boolClass();
                    base.trueWays=(s.charAt(i)=='T'?1:0);
                    base.falseWays=(s.charAt(i)=='F'?1:0);
                    dp[i][j]=base;
                    continue;
                }

                boolClass ways=new boolClass();
                for(int k=i+1;k<j;k+=2)
                {
                    boolClass left=new boolClass();
                    boolClass right=new boolClass();
                    if(i<=k-1)
                        left=dp[i][k-1];    //countWays(s, i, k-1, dp);
                    if(k+1<=j)
                        right=dp[k+1][j];   //countWays(s, k+1, j, dp);

                    if(s.charAt(k)=='|')
                    {
                        ways.trueWays=(ways.trueWays+left.falseWays*right.trueWays+left.trueWays*right.falseWays+left.trueWays*right.trueWays)%mod;
                        ways.falseWays=(ways.falseWays+left.falseWays*right.falseWays)%mod;
                    }
                    else if(s.charAt(k)=='&')
                    {
                        ways.trueWays=(ways.trueWays+left.trueWays*right.trueWays)%mod;
                        ways.falseWays=(ways.falseWays+left.trueWays*right.falseWays+left.falseWays*right.trueWays+left.falseWays*right.falseWays)%mod;
                    }
                    else
                    {
                        ways.trueWays=(ways.trueWays+(left.falseWays*right.trueWays)+(left.trueWays*right.falseWays))%mod;
                        ways.falseWays=(ways.falseWays+(left.falseWays*right.falseWays)+(left.trueWays*right.trueWays))%mod;
                    }
                }   
                dp[i][j]=ways;
            }
        }
        return dp[I][J];
        
    }
    
    
    //`````````````````````````````````````````````````````````````````````````````````````````````````````````````
    // optimal binary search tree
    
    public static int obst(int freq[],int keys[])
    {
        int n=keys.length;
        int dp[][]=new int[n][n];
        for(int d[]:dp)Arrays.fill(d, -1);
        return obst(freq,keys,0,n-1,dp);
    }
    // memoisation
    private static int obst(int[] freq, int[] keys, int i, int j, int dp[][]) 
    {
        if(dp[i][j]!=-1)
            return dp[i][j];
        int ans=(int)1e9;
        int sum=0;
        for(int k=i;k<=j;k++)
        {
            int left=0,right=0;
            if(i<=k-1)
            left=obst(freq, keys, i, k-1,dp);
            if(k+1<=j)
            right=obst(freq, keys, k+1, j, dp);
            ans=Math.min(ans,left+right);
            sum+=freq[k];
        }
        return dp[i][j]=ans+sum;
    }

    // tabulation
    private static int obst_tabu(int[] freq, int[] keys, int I, int J, int dp[][])
    {
        int n=freq.length;
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                int ans=(int)1e9;
                int sum=0;
                for(int k=i;k<=j;k++)
                {
                    int left=0,right=0;
                    if(i<=k-1)
                    left=dp[i][k-1];    //obst(freq, keys, i, k-1,dp);
                    if(k+1<=j)
                    right=dp[k+1][j];   //obst(freq, keys, k+1, j, dp);
                    ans=Math.min(ans,left+right);
                    sum+=freq[k];
                }
                dp[i][j]=ans+sum;
            }
        }
        return dp[I][J];
    }
    public static void main(String[] args) 
    {
        // int p[]={40,20,30,10,30};
        // System.out.println(mcm(p));

        // pair ans=minMax("1+2*3+4*5");
        // System.out.println("MIN VALUE = "+ans.min);
        // System.out.println("MAX VALUE = "+ans.max);
        // int nums[]={3,1,5,8};
        // System.out.println(maxCoins(nums));
        // System.out.println(countWays(7, "T|T&F^T"));
        int keys[]={10,12,20};
        int freq[]={34,8,50};
        System.out.println(obst(freq, keys));
    }
}
