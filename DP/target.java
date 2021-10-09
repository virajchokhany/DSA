import java.util.Arrays;

public class target
{
    public static void fill(int dp[])
    {
        Arrays.fill(dp, -1);
    }
    public static void fill(int dp[][])
    {
        for(int d[]:dp)
            fill(d);
    }

    public static  void display(int dp[])
    {
        for(int ele:dp){
            System.out.print(ele+" ");
        }
        System.out.println();
    }
    public static void display(int dp[][])
    {
        for(int d[]:dp){
            display(d);
        }
    }
    public static int permutation(int arr[],int tar,int dp[])
    {
        if(tar==0)
            return dp[tar]=1;

        if(dp[tar]!=-1)
            return dp[tar];
        int count=0;
        for(int i=0;i<arr.length;i++)
        {
            if(tar-arr[i]>=0)
            {
                count+=permutation(arr, tar-arr[i],dp);
            }
        }
        return dp[tar]=count;
    }

    public static int permutation_tabu(int arr[],int TAR,int dp[])
    {
        int n=arr.length;
        for(int tar=0;tar<=TAR;tar++)
        {
            if(tar==0)
            {
                dp[tar]=1;
                continue;
            }

            int count=0;
            for(int i=0;i<n;i++)
            {
                if(tar-arr[i]>=0)
                {
                    count+=dp[tar-arr[i]];   
                }
            }
            dp[tar]=count;
        }
        return dp[TAR];
    }


    public static int combination(int arr[],int tar,int idx,int dp[][])
    {
        if(tar==0)
            return dp[idx][tar]=1;

        if(dp[idx][tar]!=-1)
            return dp[idx][tar];

        int count=0;
        for(int i=idx;i<arr.length;i++)
        {
            if(tar-arr[i]>=0)
            {
                count+=combination(arr, tar-arr[i], i, dp);
            }
        }
        return dp[idx][tar]=count;
    }
    
    public static int combination_tabu(int arr[],int TAR,int IDX,int dp[][])
    {
        int n=arr.length;

        for(int idx=n-1;idx>=0;idx--)
        {
            for(int tar=0;tar<=TAR;tar++)
            {
                if(tar==0)
                {
                    dp[idx][tar]=1;
                    continue;
                }

                int count=0;
                for(int i=idx;i<n;i++)
                {
                    if(tar-arr[i]>=0)
                    {
                        count+=dp[i][tar-arr[i]];   
                    }
                }
                dp[idx][tar]=count;
            }
        }
        return dp[IDX][TAR];    
    }

    public static int coinChange(int[] coins, int amount) 
    {
        int dp[]=new int[amount+1];
        int ans= coinChange(coins,amount,dp);
        if(ans>=(int)1e9)
            return -1;
        return ans;
    }
    
    public static int coinChange(int arr[],int AMT,int dp[])
    {
        int n=arr.length;
        for(int amt=0;amt<=AMT;amt++)
        {
            if(amt==0)
            {
                dp[amt]=0;
                continue;
            }
        
            int mn=(int)1e9;
            
            for(int i=0;i<n;i++)
            {
                if(amt-arr[i]>=0)
                {
                    mn=Math.min(mn,dp[amt-arr[i]]); //solve(arr,amt-arr[i],dp));
                }
            }
            dp[amt]=mn+1;
        }
        return dp[AMT];
    }

    public static int combination_subs(int arr[],int TAR,int N,int dp[])
    {
        for(int n=0;n<=N;n++)
        {
            for(int tar=0;tar<=TAR;tar++)
            {
                if(tar==0)
                {
                    dp[tar]=1;
                    continue;
                }
            
                else if(n==0)
                {
                    dp[tar]=0;
                    continue;
                } 

                int count=0;
                if(tar-arr[n-1]>=0)
                {
                    count+=dp[tar-arr[n-1]]; 
                }
                count+=dp[tar];    

                dp[tar]=count;
            }
        }
        return dp[TAR];
        
    }



    //01 knapsack
    public static int knapSack(int W, int wt[], int val[], int N) 
    { 
        int dp[][]=new int[N+1][W+1];
        for(int n=0;n<=N;n++)
        {
            for(int w=0;w<=W;w++)
            {
                if(n==0 || W==0)
                {
                    dp[n][W]=0;
                    continue;
                }

                if(wt[n-1]<=w)
                {
                    dp[n][w]=Math.max(dp[n-1][w],dp[n-1][w-wt[n-1]]+val[n-1]);
                }
                else
                    dp[n][w]=dp[n-1][w];    
            }
        }
        return dp[N][W];
    } 


    // no of solns of linear equation

    public static int linear(int coeffs[],int rhs)
    {
        int N=coeffs.length;
        return combination_subs(coeffs, rhs, N, new int[rhs+1]);
    }



    // 698
    public static boolean canPartitionKSubsets(int arr[],int k,int idx,int ssf,int tar,boolean vis[])
    {
        if(ssf>tar)
            return false;
        if(k==0)
            return true;
        if(ssf==tar)
            return canPartitionKSubsets(arr,k-1,0,0,tar,vis);
        boolean res=false;
        for(int i=idx;i<arr.length;i++)
        {
            if(vis[i]==false)
            {
                vis[i]=true;
                res=res||canPartitionKSubsets(arr,k,i+1,ssf+arr[i],tar,vis);
                vis[i]=false;
            }
        }
        return res;
    }
    public static boolean canPartitionKSubsets(int[] arr, int k) {
        
        int n=arr.length;
        int sum=0;
        for(int ele:arr)    sum+=ele;
        if(sum%k!=0)return false;
        return canPartitionKSubsets(arr,k,0,0,sum/k,new boolean[n]);
    }


    // knight in chessboard
    // memoisation
    public double knightProbability(int n, int k, int r, int c) 
    {
        int x[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        double dp[][][]=new double[k+1][n][n];//rck
        for(double dd[][]:dp)
        {
            for(double d[]:dd)
                Arrays.fill(d,-1.0);
        }
        return knightProbability_memo(n,r,c,k,x,y,dp);
    }
    public double knightProbability_memo(int n,int sr,int sc,int k,int x[],int y[],double dp[][][])
    {
        
        if(k==0)
            return 1.0;
        if(dp[k][sr][sc]!=-1)
            return dp[k][sr][sc];
        double ans=0;
        for(int i=0;i<8;i++)
        {
            int r=sr+x[i];
            int c=sc+y[i];
            if(r>=0 && r<n && c>=0 && c<n)
            {
                ans+=(1.0/8.0)*knightProbability_memo(n,r,c,k-1,x,y,dp);
            }
        }
        return dp[k][sr][sc]=ans;
    }
    
    //tabulation
    public double knightProbability(int n,int SR,int SC,int K,int x[],int y[],double dp[][][])
    {
        for(int k=0;k<=K;k++)
        {
            for(int sr=0;sr<n;sr++)
            {
                for(int sc=0;sc<n;sc++)
                {
                    if(k==0)
                    {
                        dp[k][sr][sc]=1.0;
                        continue;
                    }
                    
                    double ans=0;
                    for(int i=0;i<8;i++)
                    {
                        int r=sr+x[i];
                        int c=sc+y[i];
                        if(r>=0 && r<n && c>=0 && c<n)
                        {
                            ans+=(1.0/8.0)*dp[k-1][r][c];   
                        }
                    }
                   dp[k][sr][sc]=ans;
                }
            }
        }
        return dp[K][SR][SC];
        
    }
    
    
    // 576
    public int findPaths(int m, int n, int move, int r, int c) {
        int dir[][]={{1,0},{-1,0},{0,1},{0,-1}};
        long dp[][][]=new long[move+1][m][n];
        return (int)findPaths(m,n,move,r,c,dir,dp);
    }
    public long findPaths(int m,int n,int MOVE,int SR,int SC,int dir[][],long dp[][][])
    {
        int mod=1000000000+7;
        for(int move=0;move<=MOVE;move++)
        {
            for(int sr=0;sr<m;sr++)
            {
                for(int sc=0;sc<n;sc++)
                {
                    if(move==0)
                    {
                        continue;
                    }
                    
                    long count=0;
                    for(int d[]:dir)
                    {
                        int r=sr+d[0];
                        int c=sc+d[1];
                        if(r<0 || c<0 || r==m || c==n)
                        {
                            count=(count+1)%mod;
                        }
                        else
                        {
                            count=(count+dp[move-1][r][c])%mod;        
                        }
                    }
                     dp[move][sr][sc]=count;
                }
            }
        }
        return dp[MOVE][SR][SC];
        
    }
    public static void main(String[] args) {
        // int arr[]={2,3,5,7};
        // int tar=10;
        // int n=arr.length;
        // int dp[]=new int[tar+1];
        // //  fill(dp);
        // System.out.println(combination_subs(arr, tar, n, dp));
        // display(dp);
        // int coins[] = {1};
        // int amount=2;
        // System.out.println(coinChange(coins, amount));
        //
        int arr[]={605,454,322,218,8,19,651,2220,175,710,2666,350,252,2264,327,1843};
        int k=4;
        System.out.println(canPartitionKSubsets(arr, k));
        
    }
}