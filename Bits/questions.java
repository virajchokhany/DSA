import java.util.ArrayList;
public class questions
{
    public  boolean isSafeToPlace(char board[][],int r,int c,char ch)
    {
        for(int i=0;i<9;i++)
        {
            if(board[i][c]==ch)
                return false;
        }
        for(int j=0;j<9;j++)
        {
            if(board[r][j]==ch)
                return false;
        }
        int i=(r/3)*3;
        int j=(c/3)*3;
        for(int ii=0;ii<3;ii++)
        {
            for(int jj=0;jj<3;jj++)
            {
                if(board[i+ii][j+jj]==ch)
                    return false;
            }
        }
        return true;
    }
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
            if(isSafeToPlace(board,r,c,ch))
            {
                board[r][c]=ch;
                boolean ans=solveSudoku(board, arr, idx+1);
                if(ans)
                    return ans;
                board[r][c]='.';
            }
        }
        return false;
    }
    public void solveSudoku(char[][] board) {
     
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                if(board[i][j]=='.')
                {
                    arr.add(i*9+j);
                }
            }
        }
        solveSudoku(board,arr,0);
    }
}