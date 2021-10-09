public class nqueensBits {
    

    public static int nQueensCombination(int floor,int col,int diag,int adiag,int n,String asf)
    {
        if(floor==n)
        {
            System.out.println(asf);
            return 1;
        }
        int count=0;
        for(int room=0;room<n;room++)
        {
            int r=floor,c=room;
            if((col & 1<<c)==0  && (diag & 1<<(r+c))==0  && (adiag & 1<<(r-c+n-1))==0)
            {
                col=col | (1<<c);
                diag=diag | (1<<(r+c));
                adiag=adiag | (1<<(r-c+n-1));
                count+=nQueensCombination(floor+1, col, diag, adiag, n, asf+"("+r+","+c+") ");
                int colmsk=~(1<<c);
                int diagmsk=~(1<<(r+c));
                int adiagmsk=~(1<<(r-c+n-1));
                col=col  & colmsk;
                diag=diag & diagmsk;
                adiag=adiag & adiagmsk;
            }
        }
        return count;
    }

    public static int nQueensPermutation(int floor,int n,int tnq,String asf,int row,int col,int diag,int adiag)
    {
        if(floor==n || tnq==0)
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
            if((1<<r & row)==0 && (1<<c & col)==0 && (diag & 1<<(r+c))==0 && (adiag & (1<<(r-c+n-1)))==0)
            {
                row=row | (1<<r);
                col=col | (1<<c);
                diag=diag | (1<<(r+c));
                adiag=adiag | (1<<(r-c+n-1));
                count+=nQueensPermutation(0, n, tnq-1, asf+"("+r+","+c+") ", row, col, diag, adiag);
                int rmsk=~(1<<r);
                int cmsk=~(1<<c);
                int dmsk=~(1<<(r+c));
                int admsk=~(1<<(r-c+n-1));
                row=row & rmsk;
                col=col & cmsk;
                diag=diag & dmsk;
                adiag=adiag & admsk;
            }
        }
        count+=nQueensPermutation(floor+1, n, tnq, asf, row, col, diag, adiag);
        return count;
    }
    public static void main(String[] args) {
        
        System.out.println(nQueensPermutation(0, 4, 4, "", 0, 0, 0, 0));
    }
}
