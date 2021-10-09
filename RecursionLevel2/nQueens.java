public class nQueens {
    
    // Check to see if all queens are safe
    public static boolean isSafe(boolean box[][],int sr,int sc)
    {
        int dir[][]={{0,-1},{-1,0},{-1,1},{-1,-1},{0,1},{1,1},{1,0},{1,-1}};
        int n=box.length;
        for(int i=0;i<dir.length;i++)
        {
            for(int rad=1;rad<n;rad++)
            {
                int r=sr+rad*dir[i][0],c=sc+rad*dir[i][1];
                if(r>=0 && c>=0 && r<n && c<n)
                {
                    if(box[r][c])
                        return false;
                }
                else
                    break;
            }
        }
        return true;
    }

    // nQueens combination
    public static int queensCombination2D(boolean box[][],int bno,int tnq, String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int n=box.length,count=0;
        for(int i=bno;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(isSafe(box,r,c))
            {
                box[r][c]=true;
                count+=queensCombination2D(box, i+1, tnq-1, asf+"("+r+","+c+") ");
                box[r][c]=false;
            }
        }
        return count;
    }

    // nQueens permutations
    public static int queensPermutations2D(boolean box[][],int tnq,String asf)
    {
        if(tnq==0)
        {
            System.out.println(asf);
            return 1;
        }
        int n=box.length,count=0;
        for(int i=0;i<n*n;i++)
        {
            int r=i/n,c=i%n;
            if(box[r][c]==false && isSafe(box, r, c))
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
