import java.util.Arrays;

public class queens 
{
    public static int queensCombination1D(int tnb,int bno, int tnq,int qpsf, String asf)
    {
        if(qpsf==tnq)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=bno;i<tnb;i++)
        {
            count+=queensCombination1D(tnb, bno+1, tnq, qpsf+1, asf+"b"+i+"q"+qpsf+" ");
        }
        return count;
    }    

    public static int queensCombination1D_subseq(int tnb,int bno,int tnq,int qpsf,String asf)
    {
        if(bno==tnb || qpsf==tnq)
        {
            if(tnq==qpsf)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=queensCombination1D_subseq(tnb,bno+1,tnq,qpsf+1,asf+"b"+bno+"q"+qpsf+" ");
        count+=queensCombination1D_subseq(tnb,bno+1,tnq,qpsf,asf);
        return count;
    }


    public static int queensPermutations1D(boolean used[],int tnq,int qpsf,String asf)
    {
        if(qpsf==tnq)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=0;i<used.length;i++)
        {
            if(used[i]==false)
            {
                used[i]=true;
                count+=queensPermutations1D(used, tnq, qpsf+1,asf+"b"+i+"q"+qpsf+" ");
                used[i]=false;
            }
        }
        return count;
    }

    public static int queensPermutations1D_subseq(int tnb,int bno,int tnq,int qpsf,boolean vis[],String asf)
    {
        if(bno==tnb || qpsf==tnq)
        {
            if(qpsf==tnq)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        if(vis[bno]==false)
        {
            vis[bno]=true;
            count+=queensPermutations1D_subseq(tnb, 0, tnq, qpsf+1, vis, asf+"b"+bno+"q"+qpsf+" ");
            vis[bno]=false;
        }
        count+=queensPermutations1D_subseq(tnb, bno+1, tnq, qpsf, vis, asf);
        return count;
    }

    // 2D

    public static int queensCombination2D(boolean box[][],int bno,int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int m=box.length,n=box[0].length,count=0;
        for(int i=bno;i<m*n;i++)
        {
            int r=i/n,c=i%n;
            count+=queensCombination2D(box, i+1, tnq-1, asf+"("+r+","+c+") ");
        }
        return count;
    }

    

    public static int queensPermutations2D(boolean box[][],int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int m=box.length,n=box[0].length,count=0;
        for(int i=0;i<m*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false)
            {
                box[r][c]=true;
                count+=queensPermutations2D(box, tnq-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        
        System.out.println(queensPermutations2D(new boolean[4][4], 4, ""));
    }
}
