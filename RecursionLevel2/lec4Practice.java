
public class lec4Practice {

    // DATE==================27/07

    // =================================================1D SET================================================================

    // normal quuens combination in 1D board
    public static int queensCombination1D(int bno,int tb,int qpsf, int tnq,String asf)
    {
        if(qpsf==tnq)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int i=bno;i<tb;i++)
        {
            count+=queensCombination1D(i+1, tb,qpsf+1, tnq, asf+"b"+i+"q"+qpsf+" ");
        }
        return count;
    }

    // queens combination using subsequence method in 1D board
    public static int queensCombination1D_subseq(int bno,int tb,int qpsf, int tnq,String asf)
    {
        if(bno==tb || qpsf==tnq)
        {
            if(tnq==qpsf)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=queensCombination1D_subseq(bno+1, tb, qpsf+1, tnq, asf+"b"+bno+"q"+qpsf+" ");
        count+=queensCombination1D_subseq(bno+1, tb, qpsf, tnq, asf);
        return count;
    }

    // normal queens permutation in 1D board
    public static int queensPermutation1D(int qpsf,int tnq,String asf,boolean box[])
    {
        if(qpsf==tnq)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        int tb=box.length;
        for(int i=0;i<tb;i++)
        {
            if(box[i]==false)
            {
                box[i]=true;
                count+=queensPermutation1D(qpsf+1, tnq, asf+"b"+i+"q"+qpsf+" ", box);
                box[i]=false;
            }
        }
        return count;
    }

    // queens permutation in 1D using subseq method
    public static int queensPermutation1D_subseq(int bno,int tb, int qpsf,int tnq,String asf,boolean box[])
    {
        if(bno==tb || qpsf==tnq)
        {
            if(qpsf==tnq)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=queensPermutation1D_subseq(bno+1,tb,qpsf,tnq,asf,box);
        if(box[bno]==false)
        {
            box[bno]=true;
            count+=queensPermutation1D_subseq(0,tb,qpsf+1,tnq,asf+"b"+bno+"q"+qpsf+" ",box);
            box[bno]=false;
        }
        return count;
    }





    // ==================================================2D SET==============================================================


    //  normal queens combination in 2D board
    public static int queensCombination2D(boolean box[][],int bno,int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        int n=box.length;
        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            count+=queensCombination2D(box, i+1, tnq-1, asf+"("+r+","+c+") ");
        }
        return count;
    }

    // ``````````````````````````````````````````````````HW Q1```````````````````````````````````````````````````````

    // queens combination using subseq in 2D
    public static int queensCombination2D_subseq(int bno,int tb,int qpsf,int tnq,boolean box[][],String asf)
    {
        if(bno==tb || qpsf==tnq)
        {
            if(qpsf==tnq)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=queensCombination2D_subseq(bno+1,tb,qpsf,tnq,box,asf);
        int r=bno/box.length,c=bno%box.length;
        count+=queensCombination2D_subseq(bno+1,tb,qpsf+1,tnq,box,asf+"("+r+","+c+") ");
        return count;
    }

    
    // normal queens permutation in 2D board
    public static int queensPermutation2D(boolean box[][],int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        int n=box.length;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false)
            {
                box[r][c]=true;
                count+=queensPermutation2D(box, tnq-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }


    //  ```````````````````````````````````````````````HW 2````````````````````````````````````````````````````````````
    // Quuens permutation in 2D using subseq method
    public static int queensPermutation2D_subseq(int bno,int tnb,int qpsf,int tnq,boolean box[][],String asf)
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
        count+=queensPermutation2D_subseq(bno+1,tnb,qpsf,tnq,box,asf);
        int r=bno/box.length,c=bno%box.length;
        if(box[r][c]==false)
        {
            box[r][c]=true;
            count+=queensPermutation2D_subseq(0,tnb,qpsf+1,tnq,box,asf+"("+r+","+c+") ");
            box[r][c]=false;
        }
        return count;
    }




    //=============================================== N QUEENS SET ================================================

    // N queens combination
    public static int nQueensCombination2D(boolean box[][],int bno,int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0,n=box.length;

        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(isSafe(box,r,c)==true)
            {
                box[r][c]=true;
                count+=nQueensCombination2D(box, i+1, tnq-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }
    
    //``````````````````````````````````````` ```````````HW Q3```````````````````````````````````````````````````````

    // N queens combination in 2D using subseq
    public static int NqueensCombination2D_subseq(int bno,int tb,int qpsf,int tnq,boolean box[][],String asf)
    {
        if(bno==tb || qpsf==tnq)
        {
            if(qpsf==tnq)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=NqueensCombination2D_subseq(bno+1,tb,qpsf,tnq,box,asf);
        int r=bno/box.length,c=bno%box.length;
        if(isSafe(box, r, c))
        {   
            box[r][c]=true;
            count+=NqueensCombination2D_subseq(bno+1,tb,qpsf+1,tnq,box,asf+"("+r+","+c+") ");
            box[r][c]=false;
        }
        return count;
    }


    // N Queens Permutation
    public static int nQueensPermutation2D(boolean box[][],int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0,n=box.length;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false && isSafe(box, r, c))
            {
                box[r][c]=true;
                count+=nQueensPermutation2D(box, tnq-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }
      
    // `````````````````````````````````````````````````HW Q4```````````````````````````````````````````````````````
    // N queens permutation in 2D using subseq
    public static int nQueensPermutation2D_subseq(int bno,int tnb,int qpsf,int tnq,boolean box[][],String asf)
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
        count+=nQueensPermutation2D_subseq(bno+1, tnb, qpsf, tnq, box, asf);
        int r=bno/box.length,c=bno%box.length;
        if(box[r][c]==false && isSafe(box, r, c))
        {
            box[r][c]=true;
            count+=nQueensPermutation2D_subseq(0,tnb,qpsf+1,tnq,box,asf+"("+r+","+c+") ");
            box[r][c]=false;
        }
        return count;
    }



    // ================================================== QUEEN HELPER  ===========================================
    // helper to check if queen is safe 
    public static boolean isSafe(boolean box[][],int sr,int sc)
    {
        int dir[][]={{0,-1},{-1,0},{-1,-1},{-1,1},{0,1},{1,1},{1,0},{1,-1}};

        int n=box.length;

        for(int i=0;i<dir.length;i++)
        {
            for(int rad=1;rad<=n;rad++)
            {
                int r=sr+rad*dir[i][0];
                int c=sc+rad*dir[i][1];
                if(r>=0 && c>=0 && r<n && c<n)
                {
                    if(box[r][c]==true)
                        return false;
                }
                else
                    break;
            }
        }
        return true;
    }

    

    


    
    //================================================== KNIGHTS==================================================

    // Knight combination
    public static int knightCombination(boolean box[][],int bno,int tnk,String asf)
    {
        if(tnk==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        int n=box.length;
        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            count+=knightCombination(box, i+1, tnk-1, asf+"("+r+","+c+") ");
        }
        return count;
    }

    // Knight Permutations
    public static int knightPermutation(boolean box[][],int tnk,String asf)
    {
        if(tnk==0)
        {
            System.out.println(asf);
            return 1;
        }
        int n=box.length;
        int count=0;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false)
            {
                box[r][c]=true;
                count+=knightPermutation(box, tnk-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }
    
    
    // helper to check if knight is safe
    public static boolean isKnightSafe(boolean box[][],int sr,int sc)
    {
        int dir[][]={{-2,-1},{-1,-2},{-1,2},{-2,1},{1,2},{2,1},{2,-1},{1,-2}};
        int n=box.length;

        for(int i=0;i<dir.length;i++)
        {
            int r=sr+dir[i][0],c=sc+dir[i][1];
            if(r>=0 && c>=0 && r<n && c<n && box[r][c])
                return false;
        }
        return true;
    }



    // ============================================== N KNIGHTS ==================================================
    // N Knight Combination
    public static int nKnightCombination(boolean box[][],int bno,int tnk,String asf)
    {
        if(tnk==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0;
        int n=box.length;
        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(isKnightSafe(box,r,c))
            {
                box[r][c]=true;
                count+=nKnightCombination(box, i+1, tnk-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }


    // N Knights combination using subseq method
    public static int nKnightCombination_subseq(int bno,int tnb,int kpsf,int tnk,String asf,boolean box[][])
    {
        if(bno==tnb || kpsf==tnk)
        {
            if(kpsf==tnk)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }

        int count=0;
        count+=nKnightCombination_subseq(bno+1,tnb,kpsf,tnk,asf,box);
        
        int r=bno/box.length,c=bno%box.length;
        if(isKnightSafe(box, r, c))
        {
            box[r][c]=true;
            count+=nKnightCombination_subseq(bno+1,tnb,kpsf+1,tnk,asf+"("+r+","+c+") ",box);
            box[r][c]=false;
        }
        return count;
    }


    // N Knights Permutation
    public static int nKnightPermutation(boolean box[][],int tnk,String asf)
    {
        if(tnk==0)
        {
            System.out.println(asf);
            return 1;
        }
        int n=box.length;
        int count=0;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false && isKnightSafe(box, r, c))
            {
                box[r][c]=true;
                count+=nKnightPermutation(box, tnk-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }

    
    // N Knights Permutation using subseq
    public static int nKnightPermutation_subseq(int bno,int tnb, int kpsf,int tnk,String asf, boolean box[][])
    {
        if(bno==tnb || kpsf==tnk)
        {
            if(kpsf==tnk)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        int count=0;
        count+=nKnightPermutation_subseq(bno+1,tnb,kpsf,tnk,asf,box);
        int n=box.length;
        int r=bno/n,c=bno%n;
        if(box[r][c]==false && isKnightSafe(box, r, c))
        {
            box[r][c]=true;
            count+=nKnightPermutation_subseq(0,tnb,kpsf+1,tnk,asf+"("+r+","+c+") ",box);
            box[r][c]=false;
        }
        return count;
    }
   



    // DATE============== 28/07

    //============================================= BRANCH AND BOUND ============================================


    static boolean row[],col[],diag[],adiag[];

    // N Queens Combination Branch & Bound
    public static int nQueensCombination2D_BRANCH_AND_BOUND(int n,int bno,int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0;

        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(!row[r] && !col[c] && !diag[r+c] && !adiag[r-c+n-1])
            {
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=true;
                count+=nQueensCombination2D_BRANCH_AND_BOUND(n, i+1, tnq-1, asf+"("+r+","+c+") ");
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=false;
            }
        }
        return count;
    }
    

    // N Queens Permutations Branch & Bound
    public static int nQueensPermutation2D_BRANCH_AND_BOUND(int n,int tnq,String asf)
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
            if(!row[r] && !col[c] && !diag[r+c] && !adiag[r-c+n-1])
            {
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=true;
                count+=nQueensPermutation2D_BRANCH_AND_BOUND(n, tnq-1, asf+"("+r+","+c+") ");
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=false;
            }
        }
        return count;
    }


    // N Queens Combinations Branch & Bound With 1 Ans
    public static int nQueensCombination2D_BRANCH_AND_BOUND_ONE_ANS(int n,int bno,int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }

        int count=0;

        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(!row[r] && !col[c] && !diag[r+c] && !adiag[r-c+n-1])
            {
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=true;
                count+=nQueensCombination2D_BRANCH_AND_BOUND_ONE_ANS(n, i+1, tnq-1, asf+"("+r+","+c+") ");
                if(count>0)
                    return count;
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=false;
            }
        }
        return count;
    }
    


    // N Queens combination optimised
    public static int nqueensOptmised(int n, int floor,int tnq,String asf)
    {
        if(tnq==0)      // floor==n && tnq==0 occur simultaneously
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int room=0;room<n;room++)
        {
            int r=floor,c=room;
            if(!col[c] && !diag[r+c] && !adiag[r-c+n-1])
            {
                col[c]=diag[r+c]=adiag[r-c+n-1]=true;
                count+=nqueensOptmised(n, floor+1, tnq-1, asf+"("+r+","+c+") ");
                col[c]=diag[r+c]=adiag[r-c+n-1]=false; 
            }
        }
        return count;
    }


    // N Queens Permutation Optimised

    public static int nqueensPermOptimised(int n,int floor,int tnq,String asf)
    {
        if( tnq==0 || floor==n)
        {
            if(tnq==0)
            {
                System.out.println(asf);
                return 1;
            }
            return 0;
        }
        
        int count=0;
        for(int room=0;room<n;room++)
        {
            int r=floor,c=room;
            if(!row[r] && !col[c] && !diag[r+c] && !adiag[r-c+n-1])
            {
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=true;
                count+=nqueensPermOptimised(n,0,tnq-1,asf+"("+r+","+c+") ");
                row[r]=col[c]=diag[r+c]=adiag[r-c+n-1]=false;
            }
        }
        count+=nqueensPermOptimised(n,floor+1,tnq,asf); 
        return count;
    }

    public static void queen()
    {
        int n=4;
        row=new boolean[n];
        col=new boolean[n];
        diag=new boolean[n+n-1];
        adiag=new boolean[n+n-1];
        System.out.println(nqueensPermOptimised(n, 0, 4, ""));
    }
    
    public static void main(String[] args) {

        queen();   
    }
}
