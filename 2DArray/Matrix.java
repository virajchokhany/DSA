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

    public static void printSpiral(int arr[][])
    {
        int n=arr.length;
        int m=arr[0].length;
        int rmin=0,rmax=n-1,cmin=0,cmax=m-1;
        int tne=n*m;

        while(tne>0)
        {
            for(int r=rmin;r<=rmax && tne>0 ; r++)
            {
                System.out.print(arr[r][cmin]+" ");
                tne--;
            }
            cmin++;
            for(int c=cmin;c<=cmax && tne>0;c++)
            {
                System.out.print(arr[rmax][c]+" ");
                tne--;
            }
            rmax--;
            for(int r=rmax;r>=rmin && tne>0 ;r--)
            {
                System.out.print(arr[r][cmax]+" ");
                tne--;
            }
            cmax--;
            for(int c=cmax;c>=cmin && tne>0;c--)
            {
                System.out.print(arr[rmin][c]+" ");
                tne--;
            }
            rmin++;
        }
    }

    public static void exitPointOfMatrix(int arr[][])
    {
        int i=0,j=0,n=arr.length,m=arr[0].length;
        int dir=0;
        int r=0,c=0;
        while(i>=0 && j>=0 && i<n && j<m)
        {
            r=i;
            c=j;
            if(arr[i][j]==0)
            {
                if(dir==0)
                {
                    j++;
                }
                else if(dir==1)
                {
                    i++;
                }
                else if(dir==2)
                {
                    j--;
                }
                else 
                {
                    i--;
                }
            }
            else
            {
                if(dir==0)
                {
                    i++;
                    dir=1;
                }
                else if(dir==1)
                {
                    j--;
                    dir=2;
                }
                else if(dir==2)
                {
                    i--;
                    dir=3;
                }
                else 
                {
                    j++;
                    dir=0;
                }
            }
        }
        System.out.println(r);
        System.out.println(c);
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

        exitPointOfMatrix(arr);
    }
}