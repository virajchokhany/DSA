public class crossWord
{
    static char[][] board = { 
                                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, 
                                { '+', '-', '-', '-', '-', '-', '-', '-', '+', '+' },
                                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' }, 
                                { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
                                { '+', '-', '-', '-', '-', '-', '-', '+', '+', '+' }, 
                                { '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
                                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' }, 
                                { '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
                                { '+', '+', '+', '+', '+', '+', '+', '+', '+', '+' } 
                            };

    static String[] words = { "agra", "norway", "england", "gwalior" };
    
    public static void display()
    {

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }
    
    public static boolean canPlaceH(int i,int j,String word)
    {
        if(j-1>=0 && board[i][j-1]!='+')
            return false;
        
        if(j+word.length()-1>=board[0].length)
            return false;

        if(j+word.length()<board[0].length && board[i][j+word.length()]!='+')
            return false;
        
        for(int idx=0;idx<word.length();idx++)
        {
            if(board[i][j+idx]=='-' || board[i][j+idx]==word.charAt(idx))
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public static boolean canPlaceV(int i,int j,String word)
    {
        if(i-1>=0 && board[i-1][j]!='+')
            return false;
        
        if(i+word.length()-1>=board.length)
            return false;

        if(i+word.length()<board.length && board[i+word.length()][j]!='+')
            return false;
        
        for(int idx=0;idx<word.length();idx++)
        {
            if(board[i+idx][j]=='-' || board[i+idx][j]==word.charAt(idx))
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    
    public static int placeH(int i,int j,String word,int vis)
    {
        for(int idx=0;idx<word.length();idx++)
        {
            if(board[i][j+idx]!='-')
                continue;
            else
            {
                vis^=(1<<idx);  //vis[idx]=true;
                board[i][j+idx]=word.charAt(idx);
            }
        }
        return vis;
    }

    public static int placeV(int i,int j,String word,int vis)
    {
        for(int idx=0;idx<word.length();idx++)
        {
            if(board[i+idx][j]!='-')
                continue;
            else
            {
                vis^=(1<<idx);
                board[i+idx][j]=word.charAt(idx);
            }
        }
        return vis;
    }

    public static void unplaceH(int i,int j,String word,int vis)
    {
        for(int idx=0;idx<word.length();idx++)
        {
            if((vis & (1<<idx))==0)    //!vis[idx])
                continue;
            else
            {
                board[i][j+idx]='-';
            }
        }
    }

    public static void unplaceV(int i,int j,String word,int vis)
    {
        for(int idx=0;idx<word.length();idx++)
        {
            if((vis & (1<<idx))==0)
                continue;
            else
            {
                board[i+idx][j]='-';            
            }
        }
    }
    public static void solveCrossWord(int idx)
    {
        if(idx==words.length)
        {
            display();
            System.out.println();
            return;
        }

        String word=words[idx];

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {   
                if(board[i][j]=='-' || board[i][j]==word.charAt(0))
                {
                    if(canPlaceH(i,j,word))
                    {
                        int vis=0;  //boolean vis[]=new boolean[word.length()];
                        vis=placeH(i,j,word,vis);
                        solveCrossWord(idx+1);
                        unplaceH(i,j,word,vis);
                    }
                     
                    if(canPlaceV(i,j,word))
                    {
                        int vis=0;      //boolean vis[]=new boolean[word.length()];
                        vis=placeV(i,j,word,vis);
                        solveCrossWord(idx+1);
                        unplaceV(i,j,word,vis);
                    }
                }
            }
        }
    }
    public static void main(String[] args) 
    {
        solveCrossWord(0);    
    }
}