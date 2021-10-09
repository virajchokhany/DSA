public class practice
{
    //============================================ 1D ========================================

    public static int queensCombination(int tnb,int bno,int qpsf,int tnq,String asf)
    {
        if(qpsf==tnq)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=bno;i<tnb;i++)
        {
            count+=queensCombination(tnb, i+1, qpsf+1, tnq, asf+"b"+i+"q"+qpsf+" ");
        }
        return count;
    }

    public static int queensCombination_subseq(int tnb,int bno,int qpsf,int tnq,String asf)
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
        count+=queensCombination_subseq(tnb, bno+1, qpsf, tnq, asf);
        count+=queensCombination_subseq(tnb, bno+1, qpsf+1, tnq, asf+"b"+bno+"q"+qpsf+" ");
        return count;
    }


    public static int queensPermutation1D(int tnb,int qpsf,int tnq,String asf, boolean box[])
    {
        if(qpsf==tnq)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=0;i<tnb;i++)
        {
            if(box[i]==false)
            {
                box[i]=true;
                count+=queensPermutation1D(tnb, qpsf+1, tnq, asf+"b"+i+"q"+qpsf+" ", box);
                box[i]=false;
            }
        }
        return count;
    }


    public static int queensPermutation1D_subseq(int tnb,int bno,int qpsf,int tnq,String asf, boolean box[])
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
        count+=queensPermutation1D_subseq(tnb, bno+1, qpsf, tnq, asf, box);
        if(box[bno]==false)
        {
            box[bno]=true;
            count+=queensPermutation1D_subseq(tnb, 0, qpsf+1, tnq,  asf+"b"+bno+"q"+qpsf+" ", box);
            box[bno]=false;
        }
        return count;
    }

    public static void queen1D()
    {
        int tnb=7,bno=0,qpsf=0,tnq=4;
        String asf="";
        boolean box[]=new boolean[tnb];
        System.out.println(queensPermutation1D_subseq(tnb, bno, qpsf, tnq, asf, box));
    }



    //========================================== 2D ===========================================

    public static int queensCombination2D(int bno,int tnb,int n,int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=bno;i<tnb;i++)
        {
            int r=i/n,c=i%n;
            count+=queensCombination2D(i+1, tnb, n, tnq-1, asf+"("+r+","+c+") ");
        }
        return count;
    }

    public static int queensCombination2D_subseq(int bno,int tnb,int n,int tnq,String asf)
    {
        if(bno==tnb || tnq==0)
        {
            if(tnq==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=queensCombination2D_subseq(bno+1, tnb, n, tnq, asf);
        int r=bno/n,c=bno%n;
        count+=queensCombination2D_subseq(bno+1, tnb, n, tnq-1, asf+"("+r+","+c+") ");
        return count;
    }

    public static int queensPermutation2D(int n,int tnq,String asf,boolean box[][])
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false)
            {
                box[r][c]=true;
                count+=queensPermutation2D(n, tnq-1, asf+"("+r+","+c+") ", box);
                box[r][c]=false;
            }
        }
        return count;
    }

    public static int queensPermutation2D_subseq(int bno,int tnb,int n,String asf,int tnq,boolean box[][])
    {
        if(bno==tnb || tnq==0)
        {
            if(tnq==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=queensPermutation2D_subseq(bno+1, tnb, n, asf, tnq, box);
        int r=bno/n,c=bno%n;
        if(box[r][c]==false)
        {
            box[r][c]=true;
            count+=queensPermutation2D_subseq(0, tnb, n, asf+"("+r+","+c+") ", tnq-1, box);
            box[r][c]=false;
        }
        return count;
    }


    public static void queen2D()
    {
        int bno=0,tnb=16,n=4,tnq=4;
        String asf="";
        boolean box[][]=new boolean[n][n];
        System.out.println(queensPermutation2D_subseq(bno, tnb, n, asf, tnq, box));
    }
    
    
    //=========================================== N QUEENS =======================================

    public static int nqueens_01()
    {
        
    }


    public static void nqueens()
    {
        nqueens_01();
    }
    public static void main(String[] args) {
        nqueens();
    }
}