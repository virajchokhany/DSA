import java.util.ArrayList;
public class sudokuBits 
{
    public static int rows[],cols[],mat[][];
    
    public boolean solveSudoku(char board[][],ArrayList<Integer> arr,int idx)
    {
        if(idx==arr.size())
        {
            return true;
        }

        int r=arr.get(idx)/9;
        int c=arr.get(idx)%9;

        for(char ch='1';ch<='9';ch++)
        {
            int mask=(1<<(ch-'0'));
            if((rows[r] & mask)==0 && (cols[c] & mask)==0 && (mat[r/3][c/3] & mask)==0)
            {
                board[r][c]=ch;
                rows[r]^=(1<<mask);
                cols[c]^=mask;
                mat[r][c]^=mask;
                boolean ans=solveSudoku(board, arr, idx+1);
                if(ans)
                    return ans;
                board[r][c]='.';
                rows[r]^=(1<<mask);
                cols[c]^=mask;
                mat[r/3][c/3]^=mask;
            }
        }
        return false;
    }
    public void solveSudoku(char[][] board)
    {
        rows=new int[9];
        cols=new int[9];
        mat=new int[3][3];
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(board[i][j]=='.')
                {
                    arr.add(i*9+j);
                }
                else
                {
                    char ch=board[i][j];
                    rows[i]^=(1<<(ch-'0'));
                    cols[j]^=(1<<(ch-'0'));
                    mat[i/3][j/3]^=(1<<(ch-'0'));
                }
            }
        }
        solveSudoku(board,arr,0);
    }    
}
