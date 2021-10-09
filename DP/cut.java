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
    public static void main(String[] args) 
    {
        // int p[]={40,20,30,10,30};
        // System.out.println(mcm(p));

        pair ans=minMax("1+2*3+4*5");
        System.out.println("MIN VALUE = "+ans.min);
        System.out.println("MAX VALUE = "+ans.max);
    }
}
