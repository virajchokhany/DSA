import java.util.Arrays;

class twoPointer
{
    // display methods
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

    // fibonacci
    public static int fib_memo(int n,int dp[])
    {
        if(n<=1)
            return dp[n]=n;
        
        if(dp[n]!=0)
            return dp[n];
        int ans=fib_memo(n-1,dp)+fib_memo(n-2,dp);
        return dp[n]=ans;
    }

    public static int fib_tabu(int N,int dp[])
    {
        for(int n=0;n<=N;n++)
        {
            if(n<=1)
            {   
                dp[n]=n;
                continue;
            }
            
            
            int ans=dp[n-1]+dp[n-2];    //fib_memo(n-1,dp)+fib_memo(n-2,dp);
            dp[n]=ans;
        }
        return dp[N];
    }

    public static int fib_opti(int n)
    {
        int a=0,b=1;
        if(n==0)return a;
        else if(n==1)return b;
        int s=0;
        for(int i=2;i<=n;i++)
        {
            s=a+b;
            a=b;
            b=s;
        }
        return b;
    }


    // maze path with 1 unit jump

    public static int mazePath_memo(int sr,int sc,int er,int ec,int dir[][],int dp[][])
    {
        if(sr==er && sc==ec)
            return dp[sr][sc]=1;

        if(dp[sr][sc]!=0)
            return dp[sr][sc];

        int count=0;

        for(int i=0;i<dir.length;i++)
        {
            int r=sr+dir[i][0];
            int c=sc+dir[i][1];
            if(r>=0 && r<=er && c>=0 && c<=ec)
                count+=mazePath_memo(r, c, er, ec, dir,dp);
        }
        return dp[sr][sc]=count;
    }

    public static int mazePath_tabu(int SR,int SC,int ER,int EC,int dir[][],int dp[][])
    {
        for(int sr=ER;sr>=0;sr--)
        {
            for(int sc=EC;sc>=0;sc--)
            {
                if(sr==ER && sc==EC)
                {
                    dp[sr][sc]=1;
                    continue;
                }

                int count=0;

                for(int i=0;i<dir.length;i++)
                {
                    int r=sr+dir[i][0];
                    int c=sc+dir[i][1];
                    if(r>=0 && r<=ER && c>=0 && c<=EC)
                        count+=dp[r][c];    //mazePath_memo(r, c, ER, EC, dir,dp);
                }
                dp[sr][sc]=count;
            }
        }
        return dp[SR][SC];
    }


    // maze path with jumps of more than 1 unit

    public static int mazePathJump_memo(int sr,int sc,int er,int ec,int dir[][],int dp[][])
    {
        if(sr==er && sc==ec)    
            return dp[sr][sc]=1;
        
        if(dp[sr][sc]!=0)
            return dp[sr][sc];
        
        int count=0;
        for(int i=0;i<dir.length;i++)
        {
            int r=sr+dir[i][0];
            int c=sc+dir[i][1];
            while(r>=0 && r<=er && c>=0 && c<=ec)
            {
                count+=mazePathJump_memo(r, c, er, ec, dir, dp);
                r+=dir[i][0];
                c+=dir[i][1];
            }
        }
        return dp[sr][sc]=count;
    }

    public static int mazePathJump_tabu(int SR,int SC,int ER,int EC,int dir[][],int dp[][])
    {
        for(int sr=ER;sr>=0;sr--)
        {
            for(int sc=EC;sc>=0;sc--)
            {
                if(sr==ER && sc==EC)    
                {
                    dp[sr][sc]=1;
                    continue;
                } 
                int count=0;
                for(int i=0;i<dir.length;i++)
                {
                    int r=sr+dir[i][0];
                    int c=sc+dir[i][1];
                    while(r>=0 && r<=ER && c>=0 && c<=EC)
                    {
                        count+=dp[r][c];    //mazePathJump_memo(r, c, ER, EC, dir, dp);
                        r+=dir[i][0];
                        c+=dir[i][1];
                    }
                }
                dp[sr][sc]=count;
            }
        }
        return dp[SR][SC];
    }
    
    // board path

    public static int boardPath(int src,int dst,int dp[])
    {
        if(src==dst)
            return dp[src]=1;
        if(dp[src]!=0)
            return dp[src];
        int count=0;
        for(int i=1;i<=6;i++)
        {
            if(i+src<=dst)
            {
                count+=boardPath(src+i, dst,dp);
            }
        }
        return dp[src]=count;
    }

    public static int boardPath(int DST,int dp[])
    {
        for(int src=DST;src>=0;src--)
        {
            if(src==DST)
            {
                dp[src]=1;
                continue;
            }

            int count=0;
            for(int i=1;i<=6;i++)
            {
                if(i+src<=DST)
                {
                    count+=dp[src+i];   //boardPath(src+i, dst,dp);
                }
                else    
                    break;
            }
            dp[src]=count;
        }
        return dp[0];
    }


    // LEETCODE DECODE WAYS -1

    public static int decodeWays_opti(String s)
    {
        int a=1;
        int n=s.length();
        int b=(s.charAt(n-1)=='0'?0:1);
        int c=0;
        for(int idx=n-2;idx>=0;idx--)
        {
            char ch=s.charAt(idx);
            if(ch=='0')
            {
                b=a;
                a=0;
                continue;
            }
            else
            {
                c+=a;
            }

            char ch1=s.charAt(idx+1);
            if((ch-'0')*10+(ch1-'0')<=26)
                    c+=b; 
            b=a;
            a=c;
        }
        return a;
    }
    public static int decodeWays_tabu(String s,int IDx,int dp[])
    {
        for(int idx=s.length();idx>=0;idx--)
        {
            if(idx==s.length())
            {
                dp[idx]=1;
                continue;
            }
            int ans=0;
            char ch=s.charAt(idx);
            if(ch=='0')
            {   
                dp[idx]=0;
                continue;
            }
            else
                ans+=dp[idx+1]; //decodeWays_memo(s,idx+1,dp);

            if(idx+1<s.length())
            {
                char ch1=s.charAt(idx+1);

                if((ch-'0')*10+(ch1-'0')<=26)
                    ans+=dp[idx+2]; //decodeWays_memo(s, idx+2,dp);
            }
            dp[idx]=ans;
        }
        return dp[IDx];
    }
    public static int decodeWays_memo(String s,int idx,int dp[])
    {
        if(idx==s.length())
            return dp[idx]=1;

        if(dp[idx]!=-1)
            return dp[idx];
        int ans=0;
        char ch=s.charAt(idx);
        if(ch=='0')
            return dp[idx]=0;
        else
            ans+=decodeWays_memo(s,idx+1,dp);

        if(idx+1<s.length())
        {
            char ch1=s.charAt(idx+1);

            if((ch-'0')*10+(ch1-'0')<=26)
                ans+=decodeWays_memo(s, idx+2,dp);
        }
        return dp[idx]=ans;
    }
    public static int decodeWays(String s)
    {
        int dp[]=new int[s.length()+1];
        Arrays.fill(dp, -1);
        int sl=decodeWays_tabu(s,0,dp);
        display(dp);
        return sl;
    }



    // friends pairing


    public long count_opti(int N)
    {
        int mod=1000000000+7;
        long a=1,b=1;
        if(N==1)
            return b;
        for(int n=2;n<=N;n++)
        {
            long ans=0;
            ans=(ans+a)%mod;   //countFriends(n-1,dp);
            ans=(ans+b*(n-1))%mod;   //countFriends(n-2,dp)*(n-1);
            b=a;
            a=ans;
        }
        return a;
    }
    public long countFriendsPairings(int n) 
    { 
       //code here
       return count_opti(n);
    }
    
    public long count_tabu(int N,long dp[])
    {
        int mod=1000000000+7;
        for(int n=0;n<=N;n++)
        {
            if(n<=1)
            {
                dp[n]=1;
                continue;
            }
            
            long ans=0;
            ans=(ans+dp[n-1])%mod;   //countFriends(n-1,dp);
            ans=(ans+dp[n-2]*(n-1))%mod;   //countFriends(n-2,dp)*(n-1);
            dp[n]=ans;    
        }
        return dp[N];
    }
    public long countFriends_memo(int n,int dp[])
    {
        if(n<=1)
            return dp[n]=1;
        if(dp[n]!=0)
            return dp[n];
        int ans=0;
        ans+=countFriends_memo(n-1,dp);
        ans+=countFriends_memo(n-2,dp)*(n-1);
        return dp[n]=ans;
    }



    // https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    // https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/


    //  count ways to divide a sum of N 
    public static int countWays_memo(int sum,int idx,int k,int l_value,int dp[][][])
    {
        if(idx==k)
        {
            if(sum==0)
                return dp[sum][idx][l_value]=1;
            else
                return  dp[sum][idx][l_value]=0;
        }
        if( dp[sum][idx][l_value]!=-1)
            return  dp[sum][idx][l_value];
        int ans=0;
        for(int i=l_value;i<=sum;i++)
        {
            ans+=countWays_memo(sum-i, idx+1, k, i,dp);
        }
        return  dp[sum][idx][l_value]=ans;
    }

    
    public static int countWaysToDivide(int N,int k)
    {
        int dp[][][]=new int[N+1][k+1][N+1];
        for(int [][]ar:dp)
        {
            for(int a[]:ar)
                Arrays.fill(a,-1);  
        }
        return countWays_memo(N,0,k,1,dp);
    }



    // partition into k groups
    public static int divideInKgroups_memo(int n,int k,int dp[][])
    {
        if(n==k || k==1)
            return dp[n][k]=1;
        if(dp[n][k]!=0)
            return dp[n][k];
        int ans=0;
        ans+=divideInKgroups_memo(n-1, k-1,dp);
        ans+=divideInKgroups_memo(n-1, k,dp)*k;
        return dp[n][k]=ans;
    }

    public static int divideInKgroups_tabu(int N,int K,int dp[][])
    {
        for(int n=1;n<=N;n++)
        {
            for(int k=1;k<=K;k++)
            {
                if(n==k || k==1)
                {
                    dp[n][k]=1;
                    continue;
                }
                int ans=0;
                ans+=dp[n-1][k-1];  
                ans+=dp[n-1][k]*k;    
                dp[n][k]=ans;
            }
        }
        return dp[N][K];
    }


    // https://practice.geeksforgeeks.org/problems/gold-mine-problem2608/1
    // https://practice.geeksforgeeks.org/problems/path-in-matrix3805/1


    public static int maximumPath(int N, int Matrix[][])
    {
        // code here
        int ans=0;
        int dir[]={1,0,-1};
        int dp[][]=new int[N][N];
        int r=0;
        for(int i=0;i<N;i++)
        {
            int rAns=maximumPath(0,i,N,Matrix,dir,dp);
            if(rAns>ans)
            {
                r=i;
                ans=rAns;
            }
        }
        return ans;
    }

    

    public static int maximumPath(int sr,int sc,int N,int M[][],int dir[],int dp[][])
    {
        if(sr==N-1)
            return dp[sr][sc]=M[N-1][sc];
        
        if(dp[sr][sc]!=0)
            return dp[sr][sc];
        int ans=0;
        for(int i=0;i<dir.length;i++)
        {
            int r=sr+1;
            int c=sc+dir[i];
            if(r>=0 && r<N && c>=0 && c<N)
            {
                ans=Math.max(ans, maximumPath(r, c, N, M, dir,dp));
            }
        }
        return dp[sr][sc]=ans+M[sr][sc];
    }

    public static int maximumPath_tabu(int SR,int SC,int N,int M[][],int dir[],int dp[][])
    {
        
        if(dp[SR][SC]!=0)
            return dp[SR][SC];
        for(int sr=N-1;sr>=SR;sr--)
        {
            for(int sc=0;sc<N;sc++)
            {
                if(sr==N-1)
                {
                    dp[sr][sc]=M[N-1][sc];
                    continue;
                }
                
                int ans=0;
                for(int i=0;i<dir.length;i++)
                {
                    int r=sr+1;
                    int c=sc+dir[i];
                    if(r>=0 && r<N && c>=0 && c<N)
                    {
                        ans=Math.max(ans, dp[r][c]);    //maximumPath(r, c, N, M, dir,dp));
                    }
                } 
                dp[sr][sc]=ans+M[sr][sc];
            }
        }
        return dp[SR][SC];
    }


    public static int maxGold(int n, int m, int M[][])
    {
        // code here
        int dir[]={1,0,-1};
        int dp[][]=new int[n][m];
        int ans=0;
        int r=-1;
        for(int i=0;i<n;i++)
        {
            int rans=maxGold_tabu(i,0,M,dir,dp);
            if(rans>ans)
            {
                ans=rans;
                r=i;
            }
        }
        //display2D(dp);
        System.out.println(maxGold_backEng(r, 0, dir, dp));
        return ans;
    }

    public static String maxGold_backEng(int sr,int sc,int dir[],int dp[][])
    {
        if(sc==dp[0].length-1)
            return "("+sr+","+sc+")";
        int d=-1;
        int mx=-1;
        for(int i=0;i<dir.length;i++)
        {
            int r=sr+dir[i];
            int c=sc+1;
            if(r>=0 && r<dp.length && c>=0 && c<dp[0].length)
            {
                if(mx<dp[r][c])
                {
                    mx=dp[r][c];
                    d=i;
                }
            }
        }

        return "("+sr+","+sc+") ->"+maxGold_backEng(sr+dir[d], sc+1, dir, dp);
    }


    public static int maxGold(int sr,int sc,int M[][],int dir[],int dp[][])
    {
        if(sc==M[0].length-1)
            return dp[sr][sc]=M[sr][sc];
        if(dp[sr][sc]!=0)
            return dp[sr][sc];
        int ans=0;
        for(int ele:dir)
        {
            int r=sr+ele;
            int c=sc+1;
            if(r>=0 && r<M.length && c>=0 && c<M[0].length)
            {
                ans=Math.max(ans, maxGold(r, c, M, dir, dp));
            }
        }
        return dp[sr][sc]=ans+M[sr][sc];
    }

    public static int maxGold_tabu(int SR,int SC,int M[][],int dir[],int dp[][])
    {
        if(dp[SR][SC]!=0)
            return dp[SR][SC];
        for(int sc=M[0].length-1;sc>=0;sc--)
        {
            for(int sr=M.length-1;sr>=0;sr--)
            {
                if(sc==M[0].length-1)
                {
                    dp[sr][sc]=M[sr][sc];
                    continue;
                }
                int ans=0;
                for(int ele:dir)
                {
                    int r=sr+ele;
                    int c=sc+1;
                    if(r>=0 && r<M.length && c>=0 && c<M[0].length)
                    {
                        ans=Math.max(ans, dp[r][c]);    //maxGold(r, c, M, dir, dp));
                    }
                }
                dp[sr][sc]=ans+M[sr][sc];
            }
        }
        return dp[SR][SC];
    }


    public static void main(String[] args) {
        int M[][]={{1, 3, 3},
        {2, 1, 4},
        {0, 6, 4}};
        System.out.println(maxGold(3, 3, M));
    }
    
}