import java.util.*;
public class equalSet {

    public static int equalset(int arr[],int idx,int sum1,String set1,int sum2,String set2)
    {
        if(idx==arr.length)
        {
            if(sum1==sum2)
            {
                System.out.println(set1+" = "+set2);
                return 1;
            }
            return 0;
        }

        int count=0;
        count+=equalset(arr, idx+1, sum1+arr[idx], set1+arr[idx]+" ", sum2, set2);
        count+=equalset(arr, idx+1, sum1, set1, sum2+arr[idx], set2+arr[idx]+" ");
        return count;
    }
    
    public static void equalSet(int[] arr, int idx, int sum, ArrayList<ArrayList<Integer>> ans) 
    {
        
    }

    public static void equalSet(int[] arr, int idx) 
    {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if ((sum & 1) != 0)
            return;

        equalSet(arr, 0, sum >>> 2, ans);
        System.out.println(ans);
    }
    public static void main(String[] args) {
        int arr[]={10,20,30,40,50,60,70,80,100};
       /*  equalset(arr, 0, 0, "", 0, ""); */
       equalset(arr, 1, arr[0], arr[0]+" ", 0, "");
    }
}
