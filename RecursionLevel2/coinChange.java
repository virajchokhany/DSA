import java.util.*;
public class coinChange {
    

    // permutations with infinite coins
    public static int infiPermutation(int coins[],int tar,String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=0;i<coins.length;i++)
        {
            if(tar-coins[i]>=0)
            {
                count+=infiPermutation(coins, tar-coins[i], asf+coins[i]);
            }
        }
        return count;   
    }

    // combinations with infinite coins
    public static int infiCombination(int coins[],int tar,int idx, String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;

        for(int i=idx;i<coins.length;i++)
        {
            if(tar-coins[i]>=0)
            {
                count+=infiCombination(coins, tar-coins[i], i, asf+coins[i]);
            }
        }
        return count;
    }

    // combinations using unique indices of coins
    public static int uniCombination(int coins[],int tar,int idx, String asf)
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;

        for(int i=idx;i<coins.length;i++)
        {
            if(tar-coins[i]>=0)
            {
                count+=uniCombination(coins, tar-coins[i], i+1, asf+coins[i]);
            }
        }
        return count;
    }

    // permutations with unique indices of coins
    public static int uniPermutations(int coins[],int tar,String asf,boolean used[])
    {
        if(tar==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0;

        for(int i=0;i<coins.length;i++)
        {
            if(used[i]==false && tar-coins[i]>=0)
            {
                used[i]=true;
                count+=uniPermutations(coins, tar-coins[i], asf+coins[i], used);
                used[i]=false;
            }
        }
        return count;
    }

    // ``````````````````````````````````````````````````` LEETCODE````````````````````````````````````````````
    // leetcode 39
    public static void solve(int arr[],int tar,List<List<Integer>> ans,List<Integer> sans,int idx)
    {
        if(tar==0)
        {
            ans.add(new ArrayList<>(sans));
            return;
        }

        for(int i=idx;i<arr.length;i++)
        {
            if(tar-arr[i]>=0)
            {
                sans.add(arr[i]);
                solve(arr, tar-arr[i], ans, sans, i);
                sans.remove(sans.size()-1);
            }
        }
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> sans=new ArrayList<>();
        
        solve(candidates,target,ans,sans,0);
        return ans;
    }


    // leetcode 40
    public void solve2(int arr[],int tar,List<List<Integer>> ans,List<Integer> sans,int idx)
    {
        if(tar==0)
        {
            ans.add(new ArrayList<>(sans));
            return;
        }
        
        int prev=-1;
        for(int i=idx;i<arr.length;i++)
        {
            if(tar-arr[i]<0)
                break;
            else
            {
                if(prev!=arr[i])
                {
                    prev=arr[i];
                    sans.add(arr[i]);
                    solve2(arr,tar-arr[i],ans,sans,i+1);
                    sans.remove(sans.size()-1);
                }
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] arr, int tar) 
    {
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> sans=new ArrayList<>();
        Arrays.sort(arr);
        solve(arr,tar,ans,sans,0);
        return ans;
    }


    public static int wordsCombindation(String ques,String asf,int idx)
    {
        System.out.println(asf);
        int count=1;
        for(int i=idx;i<ques.length();i++)
        {
            count+=wordsCombindation(ques, asf+ques.charAt(i), i+1);
        }
        return count;
    }

    // ````````````````````````````````````````````     SUBSEQ METHOD  ```````````````````````````````````````````````````````

    public static int singleCombination_subseq(int coins[],int tar,int idx,String asf)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=singleCombination_subseq(coins,tar-coins[idx],idx+1,asf+coins[idx]+" ");
        }
        count+=singleCombination_subseq(coins,tar,idx+1,asf);
        return count;
    }


    public static int infiCombinations_subseq(int coins[],int tar,int idx,String asf)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=infiCombinations_subseq(coins, tar-coins[idx], idx, asf+coins[idx]+" ");
        }
        count+=infiCombinations_subseq(coins, tar, idx+1, asf);
        return count;
    }


    public static int infiPermutations_subseq(int coins[],int tar,int idx,String asf)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;
        if(tar-coins[idx]>=0)
        {
            count+=infiPermutations_subseq(coins, tar-coins[idx], 0, asf+coins[idx]+" ");
        }
        count+=infiPermutations_subseq(coins, tar, idx+1, asf);
        return count;
    }

    public static int singlePermutations_subseq(int coins[],int tar,int idx,String asf)
    {
        if(tar==0 || idx==coins.length)
        {
            if(tar==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;

        if(coins[idx]>0 && tar-coins[idx]>=0)
        {
            int x=coins[idx];
            coins[idx]=-coins[idx];
            count+=singlePermutations_subseq(coins, tar-x, 0, asf+x+" ");
            coins[idx]=-coins[idx];
        }
        count+=singlePermutations_subseq(coins, tar, idx+1, asf);

        return count;
    }
    // https://www.interviewbit.com/problems/subset/
    public static void main(String[] args) {
        int coins[]={2,3,5,7};
        int tar=10;
        String asf="";
        
        System.out.println(singlePermutations_subseq(coins, tar, 0, asf));
    }
}
