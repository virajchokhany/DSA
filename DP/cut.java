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
    public static void main(String[] args) 
    {
        int p[]={40,20,30,10,30};
        System.out.println(mcm(p));
    }
}
