
import java.util.Arrays;

public class stringDP 
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
    public static int longestPalindromeSubseq(String s)
    {
        int n=s.length();
        int dp[][]=new int[n][n];
        for(int ar[]:dp)
        {
            Arrays.fill(ar, -1);
        }
        int ans=LPS_tabu(s,0,n-1,dp);   
        return ans;
    }    

    public static int LPS(String s,int si,int ei,int dp[][])
    {
        if(si==ei)
            return dp[si][ei]=1;
        else if(si>ei)
            return dp[si][ei]=0;
        if(dp[si][ei]!=-1)
            return dp[si][ei];
        if(s.charAt(si)==s.charAt(ei))
            return dp[si][ei]=2+LPS(s,si+1,ei-1,dp);
        else
            return dp[si][ei]=Math.max(LPS(s,si+1,ei,dp),LPS(s,si,ei-1,dp));
    }

    public static int LPS_tabu(String s,int SI,int EI,int dp[][])
    {
        for(int gap=0;gap<EI;gap++)
        {
            for(int si=0,ei=gap;ei<=EI;si++,ei++)
            {
                if(si==ei)
                {
                    dp[si][ei]=1;
                    continue;
                }
                if(s.charAt(si)==s.charAt(ei))
                {
                    dp[si][ei]=2+dp[si+1][ei-1];    
                    continue;
                }
                else
                    dp[si][ei]=Math.max(dp[si+1][ei],dp[si][ei-1]);  
            }
        }
        return dp[SI][EI];
    }   


    public int longestCommonSubsequence(String text1, String text2) 
    {
        int n=text1.length();
        int m=text2.length();
        int dp[][]=new int[n+1][m+1];
        for(int a[]:dp)
            Arrays.fill(a,-1);
        return LCS(text1,n,text2,m,dp);
    }

    public int LCS(String s1,int n, String s2,int m,int dp[][])
    {
        if(n==0 || m==0)
            return dp[n][m]=0;
        if(dp[n][m]!=-1)
            return dp[n][m];
        if(s1.charAt(n-1)==s2.charAt(m-1))
        {
            return dp[n][m]=1+LCS(s1,n-1,s2,m-1,dp);
        }
        else
        {
            return dp[n][m]=Math.max(LCS(s1,n,s2,m-1,dp),LCS(s1,n-1,s2,m,dp));
        }
    }

    public static int LCS_tabu(String s1,int N,String s2,int M,int dp[][])
    {
        for(int n=0;n<=N;n++)
        {
            for(int m=0;m<=M;m++)
            {
                if(n==0 || m==0)
                {
                    dp[n][m]=0;
                    continue;
                } 
                if(s1.charAt(n-1)==s2.charAt(m-1))
                {
                     dp[n][m]=1+dp[n-1][m-1];   //LCS(s1,n-1,s2,m-1,dp);
                }
                else
                {
                     dp[n][m]=Math.max(dp[n][m-1],dp[n-1][m]);    //LCS(s1,n,s2,m-1,dp),LCS(s1,n-1,s2,m,dp));
                }
            }
        }
        return dp[N][M];
    }


    // edit distance
    public static int mindst(String s1,String s2,int i,int j,int dp[][])
    {
        if(i==s1.length())
            return dp[i][j]=s2.length()-j;
        else if(j==s2.length())
            return dp[i][j]=s1.length()-i;
        if(dp[i][j]!=-1)
            return dp[i][j];
        if(s1.charAt(i)==s2.charAt(j))
        {
            return dp[i][j]=mindst(s1,s2,i+1,j+1,dp);
        }
        else
        {
            int op1=mindst(s1,s2,i,j+1,dp);    //insert
            int op2=mindst(s1,s2,i+1,j+1,dp);  // update
            int op3=mindst(s1,s2,i+1,j,dp);    // delete
            
            return dp[i][j]=Math.min(op1,Math.min(op2,op3))+1;
        }
    }

    public static int mindst_tabu(String s1,String s2,int I,int J,int dp[][])
    {
        for(int i=s1.length();i>=0;i--)
        {
            for(int j=s2.length();j>=0;j--)
            {
                if(i==s1.length())
                {
                    dp[i][j]=s2.length()-j;
                    continue;
                }
                else if(j==s2.length())
                {
                    dp[i][j]=s1.length()-i;
                    continue;
                }

                if(s1.charAt(i)==s2.charAt(j))
                {
                    dp[i][j]=dp[i+1][j+1];  //mindst(s1,s2,i+1,j+1,dp);
                }
                else
                {
                    int op1=dp[i][j+1]; //mindst(s1,s2,i,j+1,dp);    //insert
                    int op2=dp[i+1][j+1]; //mindst(s1,s2,i+1,j+1,dp);  // update
                    int op3=dp[i+1][j]; //mindst(s1,s2,i+1,j,dp);    // delete
                    
                    dp[i][j]=Math.min(op1,Math.min(op2,op3))+1;
                }
            }
        }
        return dp[I][J];
    }
    public static int minDistance(String s1, String s2) 
    {
        int dp[][]=new int[s1.length()+1][s2.length()+1];
        for(int ar[]:dp)
            Arrays.fill(ar,-1);
        int ans=mindst(s1,s2,0,0,dp);    
        display2D(dp);
        return ans;
    }


       

    // wildcard pattern matching
    public static String removeStar(String str)
    {
        if(str.length()==0)
            return str;
        
        StringBuilder sb=new StringBuilder();
        sb.append(str.charAt(0));
        int i=1;
        while(i<str.length())
        {
            while(i<str.length() && str.charAt(i)==sb.charAt(sb.length()-1) && str.charAt(i)=='*')
                i++;
                
            if(i>=str.length())
                break;
            sb.append(str.charAt(i));
            i++;
        }
        return sb.toString();
    }

    public static int isMatch(String s,String p,int i,int j,int dp[][])
    {
        if(i==s.length() || j==p.length())
        {
            if(i==s.length() && j==p.length())
                return dp[i][j]=1;
            
            else if(j==p.length()-1 && p.charAt(j)=='*')
                return dp[i][j]=1;
            else 
                return dp[i][j]=0;
        }
        
        if(dp[i][j]!=-1)
            return dp[i][j];
        char ch1=s.charAt(i);
        char ch2=p.charAt(j);
        
        if(ch1==ch2 || ch2=='?')
        {
            return dp[i][j]=isMatch(s,p,i+1,j+1,dp);
        }
        else if(ch2=='*')
        {
            boolean res=false;
            res=res || isMatch(s,p,i+1,j,dp)==1;
            res=res || isMatch(s,p,i,j+1,dp)==1;
            return dp[i][j]=(res==true?1:0);
        }
        else
            return dp[i][j]=0;
    }

    public static int isMatch_tabu(String s,String p,int I,int J,int dp[][])
    {
        for(int i=s.length();i>=0;i--)
        {
            for(int j=p.length();j>=0;j--)
            {
                if(i==s.length() || j==p.length())
                {
                    if(i==s.length() && j==p.length())
                    {
                        dp[i][j]=1;
                        continue;
                    }
                    
                    else if(j==p.length()-1 && p.charAt(j)=='*')
                    {
                        dp[i][j]=1;
                        continue;
                    }

                    else 
                    {
                        dp[i][j]=0;
                        continue;
                    }
                }
                
                char ch1=s.charAt(i);
                char ch2=p.charAt(j);
                
                if(ch1==ch2 || ch2=='?')
                {
                    dp[i][j]=dp[i+1][j+1];  //isMatch(s,p,i+1,j+1,dp);
                    continue;
                }
                else if(ch2=='*')
                {
                    boolean res=false;
                    res=res || dp[i+1][j]==1;   //isMatch(s,p,i+1,j,dp)==1;
                    res=res || dp[i][j+1]==1;  //isMatch(s,p,i,j+1,dp)==1;
                    dp[i][j]=(res==true?1:0);
                    continue;
                }
                else
                {
                    dp[i][j]=0;
                    continue;
                } 
            }
        }
        return dp[0][0];
    }



    // Count subsequences of type a^i, b^j, c^k

    public int fun(String s)
    {
        // Write your code here
        long  a=(s.charAt(0)=='a'?1:0),ab=0,abc=0;
        int mod=1000000000+7;
        for(int i=1;i<s.length();i++)
        {
            char ch=s.charAt(i);    //[i];
            
            if(ch=='c')
            {
                abc=(2*abc+ab)%mod;
            }
            
            if(ch=='b')
            {
                ab=(2*ab+a)%mod;
            }
            
            if(ch=='a')
            {
                a=(2*a+1)%mod;
            }
        }
        return (int)abc;
    }

    // maximum dot product

    public int maxDotProduct(int[] nums1, int[] nums2) 
    {
        int n=nums1.length,m=nums2.length;
        int dp[][]=new int[n+1][m+1];
        return maxDotProduct(nums1,nums2,n,m,dp);
    }
    
    public int maxDotProduct(int[] nums1, int[] nums2,int N,int M,int dp[][])
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
                int a=dp[n][m-1];   //maxDotProduct(nums1,nums2,n,m-1);
                int b=dp[n-1][m];   //maxDotProduct(nums1,nums2,n-1,m);
                int c=nums1[n-1]*nums2[m-1];
                int d=c+dp[n-1][m-1];   //maxDotProduct(nums1,nums2,n-1,m-1);

                dp[n][m]=Math.max(a,Math.max(b,Math.max(c,d)));
            }
        }
        return dp[N][M];
        
    }
    
    
    
    public static void main(String[] args) 
    {
        
    }
}
