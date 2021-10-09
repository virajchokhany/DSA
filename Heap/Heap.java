import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;

import javax.print.event.PrintEvent;
public class Heap
{
    public static void Int_MNPR()
    {
        // this-other min pq
        //other - this max pq
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b) -> {
            return a-b;
        });

        for(int i=10;i>=1;i--)
        {
            pq.add(10*i);
        }
        while(pq.size()>0){
            System.out.println(pq.remove());
        }
    }
    
    public static void Int_MXPR()
    {
        // this-other min pq
        //other - this max pq
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b) -> {
            return b-a;
        });

        for(int i=1;i<=10;i++)
        {
            pq.add(10*i);
        }
        while(pq.size()>0){
            System.out.println(pq.remove());
        }
    }
    public static void print(int x[])
    {
        for(int i=0;i<x.length;i++)
        {
            System.out.print(x[i]+" ");
        }
    }
    public static void matrixPQ(int[][] arr)
    {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
            return a[1]-b[1];
        });
        
        for(int x[]:arr){
            pq.add(x);
        }
        System.out.println("ORIGINAL MATRIX\n");

        for(int x[]:arr){
            print(x);
            System.out.println();
        }
        System.out.println("AFTER OPERATION\n");
        while(pq.size()>0)
        {
            print(pq.remove());
            System.out.println();
        }
    }

    public static class mobilePhone 
    {
        String Company = "";
        String Model = "";
        int Ram = 0;
        int Storage = 0;
        int BatteryBackup = 0;

        mobilePhone(String Company, String Model, int Ram, int Storage, int BatteryBackup) 
        {
            this.Company = Company;
            this.Model = Model;
            this.Ram = Ram;
            this.Storage = Storage;
            this.BatteryBackup = BatteryBackup;
        }

        mobilePhone()
        {

        }

        @Override
        public String toString() 
        {
            StringBuilder sb = new StringBuilder();
            
            sb.append("Company: " + this.Company + "\n");
            sb.append("Model: " + this.Model + "\n");
            sb.append("Ram: " + this.Ram + "GB\n");
            sb.append("Storage: " + this.Storage + "GB\n");
            sb.append("BatteryBackup: " + this.BatteryBackup + "mAH\n");
            
            return sb.toString();
        }
    }
    public static void MOBILE()
    {
        PriorityQueue<mobilePhone> pq=new PriorityQueue<>((a,b)->{
            
            if(a.Ram!=b.Ram)
                return b.Ram-a.Ram;
            else if(a.Storage!=b.Storage)
                return b.Storage-a.Storage;
            else
                return b.BatteryBackup-a.BatteryBackup;
            
        });

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n");
        int n=sc.nextInt();

        mobilePhone arr[]=new mobilePhone[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=new mobilePhone();
        }
        for(int i=0;i<n;i++)
        {
            System.out.println("enter company, model, ram, storage, battery cap\n\n");
            arr[i].Company=sc.next();
            arr[i].Model=sc.next();
            arr[i].Ram=sc.nextInt();
            arr[i].Storage=sc.nextInt();
            arr[i].BatteryBackup=sc.nextInt();
            pq.add(arr[i]);
        }
        while(pq.size()>0){
            System.out.println(pq.remove());
        }
    }
    public static void sort2DMatrix()
    {
        int [][]matrix=new int[4][5];
        Scanner sc=new Scanner(System.in);
        int m=matrix.length;
        int n=matrix[0].length;

        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                matrix[i][j]=sc.nextInt();
            }
        }

        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->{
            int r1=a/n,c1=a%n,r2=b/n,c2=b%n;
            return matrix[r1][c1]-matrix[r2][c2];
        });

        for(int i=0;i<m;i++)
        {
            pq.add(i*n);
        }
        int ans[]=new int[m*n];
        int idx=0;
        while(pq.size()>0)
        {
            int i=pq.remove();
            int r=i/n;
            int c=i%n;
            ans[idx++]=matrix[r][c];
            c++;
            if(c<n)
            {
                pq.add(r*n+c);
            }
        }
        for(int val:ans){
            System.out.print(val+" ");
        }
    }
    public static void main(String[] args)
    {
        //Int_MXPR();
        //int arr[][]={{8,9,7,6},{2,8,4,1},{0,1,8,5},{6,2,1,0}};
        //matrixPQ(arr);
        //MOBILE();
        sort2DMatrix();
    }
}