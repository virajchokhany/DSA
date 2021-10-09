import java.util.*;
class l003
{
    public static void display2D(boolean [][]dp)
    {
        for(boolean b[]:dp)
        {
            display(b);
        }
        System.out.println();
    }

    public static void display(boolean b[])
    {
        for(boolean bl:b)
        {
            System.out.print(bl+" ");
        }
        System.out.println();
    }
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
    
    public int maxDotProduct(int[] nums1, int[] nums2) 
    {
        int n=nums1.length,m=nums2.length;
        int dp[][]=new int[n+1][m+1];
        return maxDotProd(nums1,nums2,n,m,dp);
    }
    public int maxDotProd(int nums1[],int nums2[],int N,int M,int dp[][])
    {
        for(int n=0;n<=N;n++)
        {
            for(int m=0;m<=M;m++)
            {
                if(n==0 || m==0)
                {
                    dp[n][m]=-(int)1e9;
                    continue;
                }
                
                int a=dp[n-1][m];   //maxDotProd(nums1,nums2,n-1,m,dp);
                int b=dp[n][m-1];   //maxDotProd(nums1,nums2,n,m-1,dp);
                int c=nums1[n-1]*nums2[m-1];
                int d=c+dp[n-1][m-1];   //maxDotProd(nums1,nums2,n-1,m-1,dp);
                dp[n][m]=Math.max(a,Math.max(b,Math.max(c,d)));
            }
        }
        return dp[N][M];
    }

    

    public int palindrome(String s)
    {
        int n=s.length();
        int dp[][]=new int[n][n];
        int count=0;
        int mxLength=0;
        int si=0,ei=0;
        for(int i=n-1;i>=0;i--)
        {
            for(int j=i;j<n;j++)
            {
                if(i==j)
                {
                    dp[i][j]=1;
                    continue;
                }    
                else 
                {
                    if(s.charAt(i)!=s.charAt(j))
                        dp[i][j]=0;
                    else
                    {
                        if(j==i+1)
                        {
                            dp[i][j]=1;
                        }
                        else
                            dp[i][j]=dp[i+1][j-1];
                    }
                }
                count+=dp[i][j];
                if(dp[i][j]==1 && j-i+1>mxLength)
                {
                    mxLength=j-i+1;
                    si=i;
                    ei=j;
                }
            }
        }
        System.out.println("Maximum length is "+(ei-si+1));
        System.out.println("Maximum length pal string is "+s.substring(si, ei+1));
        return count;
    }

    public int longestCommonSubstr(String s1, String s2, int N, int M)
    {
        // code here
        int ans=0;
        
        int dp[][]=new int[N+1][M+1];
        for(int n=0;n<=N;n++)
        {
            for(int m=0;m<=M;m++)
            {
                if(n==0 || m==0)
                {
                    dp[n][m]=0;
                    continue;
                }

                if(s1.charAt(n-1)!=s2.charAt(m-1))
                {
                    dp[n][m]=0;
                }
                
                else
                    dp[n][m]=1+dp[n-1][m-1];    //longestSubstring(s1, s2, n-1, m-1);
                ans=Math.max(ans, dp[n][m]);
            }
        }
        return ans;
    }

    // leetcode 132
    public static int minCut(String s,int si,int ei,boolean dp[][],int palDp[])
    {
        if(dp[si][ei])
            return palDp[si]=0;
        
        if(palDp[si]!=-1)
            return palDp[si];
        
        int mnAns=(int)1e9;
        for(int cut=si;cut<=ei;cut++)
        {
            if(dp[si][cut])
            {
                mnAns=Math.min(mnAns,minCut(s,cut+1,ei,dp,palDp));
            }
        }
        return palDp[si]=mnAns+1;
    }
    public static int minCut(String s) 
    {
        int n=s.length();
        boolean dp[][]=new boolean[n][n];
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(gap==0)
                {
                    dp[i][j]=true;
                }
                else if(gap==1 && s.charAt(i)==s.charAt(j))
                {
                    dp[i][j]=true;
                }
                else
                {
                    if(s.charAt(i)==s.charAt(j))
                    {
                        dp[i][j]=dp[i+1][j-1];
                    }
                    else
                        dp[i][j]=false;
                }
            }
        }
        display2D(dp);
        int palDp[]=new int[n];
        Arrays.fill(palDp,-1);
        int ans=minCut(s,0,n-1,dp,palDp);
        System.out.println();
        display(palDp);
        return ans;
    }


    // 1278
    public int palindromePartition(String s, int k) 
    {
        int dp1[][]=new int[s.length()+1][k+1];
        int dp2[][]=new int[s.length()][s.length()];
        initialise(s,dp2);
        return palPart(s,0,k,dp1,dp2);    
    }
    public void initialise(String s, int dp[][])
    {
        int n=s.length();
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;j<n;i++,j++)
            {
                if(i==j)
                {
                    dp[i][j]=0;
                }
                else if(gap==1 && s.charAt(i)==s.charAt(j))
                {
                    dp[i][j]=0;
                }
                else
                {
                    if(s.charAt(i)==s.charAt(j))
                    {
                        dp[i][j]=dp[i+1][j-1];
                    }
                    else
                        dp[i][j]=1+dp[i+1][j-1];
                }
            }
        }
    }
    public int palPart(String s,int IDX, int K,int dp1[][],int dp2[][])
    {
        for(int k=0;k<=K;k++)
        {
            for(int idx=s.length();idx>=IDX;idx--)
            {
                if(s.length()-idx<k)
                {
                    dp1[idx][k]=(int)1e9;
                    continue;
                }
                
                if(idx==s.length() && k==0)
                {
                    dp1[idx][k]=0;
                    continue;
                }
                
                else if(idx!=s.length() && k==0)
                {
                    dp1[idx][k]=(int)1e9;
                    continue;
                }
                
                int mn=(int)1e9;
                for(int i=idx;i<s.length();i++)
                {
                    mn=Math.min(mn,dp1[i+1][k-1]+dp2[idx][i]);
                }
                dp1[idx][k]=mn;
            }
        }
        return dp1[IDX][K];
    }
    public static void main(String[] args) {
        
        System.out.println(minCut("leet"));
    }

    
}