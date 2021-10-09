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
        int values[]={1,3,1,4,1,5};
        System.out.println(minScoreTriangulation(values));
    }
}
