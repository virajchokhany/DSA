class l001
{
    public static void swap(int arr[],int i,int j)
    {
        int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }
    public static void sort01(int[] arr)
    {
        int n=arr.length;
        int p=-1,itr=0;
        
        while(itr<n)
        {
            if(arr[itr]==0)
            {
                p++;
                swap(arr,itr,p);
            }
            itr++;
        }
    }
    public static void main(String[] args) 
    {

    }
}