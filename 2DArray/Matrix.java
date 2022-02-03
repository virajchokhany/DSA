import java.util.Scanner;

public class Matrix
{
    public static void horizontalChange(int arr[][])
    {
        int m=arr.length;
        int n=arr[0].length;

        for(int i=0;i<m;i++)
        {
            if(i%2==0)
            {
                for(int j=0;j<n;j++)
                {
                    System.out.print(arr[i][j]+" ");
                }
            }
            else
            {
                for(int j=n-1;j>=0;j--)
                {
                    System.out.print(arr[i][j]+" ");
                }
            }
        }
    }

    public static void displayVerticialChange(int arr[][])
    {
        int m=arr.length;
        int n=arr[0].length;

        for(int j=0;j<n;j++)
        {
            if(j%2==0)
            {
                for(int i=0;i<m;i++)
                {
                    System.out.print(arr[i][j]+" ");
                }
            }
            else
            {
                for(int i=m-1;i>=0;i--)
                {
                    System.out.print(arr[i][j]+" ");
                }
            }
        }
    }


    public static void printUsingGapStrategy(int arr[][])
    {
        int m=arr.length;
        int n=arr[0].length;
        
        for(int gap=0;gap<n;gap++)
        {
            for(int i=0,j=gap;i<m && j<n;i++,j++)
            {
                System.out.print(arr[i][j]+" ");
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int arr[][]=new int[sc.nextInt()][sc.nextInt()];
        for(int i=0;i<arr.length;i++)
            for(int j=0;j<arr[0].length;j++)
                arr[i][j]=sc.nextInt();
        
                for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[0].length;j++){
                    System.out.print(arr[i][j]+" ");
                }
                System.out.println();
            }
                    
        // System.out.println();
        // System.out.println();
        // horizontalChange(arr);
        // System.out.println();
        // displayVerticialChange(arr);

        printUsingGapStrategy(arr);
    }
}