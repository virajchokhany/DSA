import java.util.ArrayList;

public class recursion
{
    public static void printIncreasing(int a, int b) 
    {
        if(a>b)
            return;
        System.out.println(a);
        printIncreasing(a + 1, b);

    }

    public static void printDecreasing(int a, int b) 
    {
        if(a>b)
            return;
        printDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void printIncreasingDecreasing(int a, int b) 
    {
        if(a>b)
            return;
        System.out.println(a);
        printIncreasingDecreasing(a + 1, b);
        System.out.println(a);
    }

    public static void oddEven(int a, int b) 
    {
        if(a>b)
            return;

        if(a%2!=0)
        {
            System.out.println(a);
        }
        oddEven(a + 1, b);
        if(a%2==0)
            System.out.println(a);
    }

    public static int factorial(int n) 
    {
        if(n<=1)
            return 1;
        return factorial(n-1)*n;
    }

    // a^b
    public static int power(int a, int b) 
    {
        if(b==0)
            return 1;
        return power(a, b-1)*a;
    }

    // O(logn)
    // n n/2 n/2^2 n/2^3 .... 1
    // gp   ak=1=a0(r^k-1)
    //      1=n(0.5)^(k-1)
    // 1=n/2^(k-1)
    // n=2^k-1
    // k=log(n)
    public static int powerBtr(int a, int b)
    {
        if(b==0)
            return 1;
        
        int half=powerBtr(a,b/2);
        if(b%2==0)
            return half*half;
        else
            return half*half*a;
    }

    public static void printArray(int[] arr,int idx) 
    {
        if(idx==arr.length)
            return;
        System.out.println(arr[idx]);
        printArray(arr, idx+1);
    }

    public static void printArrayReverse(int[] arr,int idx) 
    {
        if(idx==arr.length)
            return;
        printArrayReverse(arr, idx+1);
        System.out.println(arr[idx]);
    }

    public static int maximum(int[] arr,int idx) 
    {
        if(idx==arr.length)
            return -(int)1e9;
        int rres=maximum(arr, idx+1);
        return Math.max(arr[idx],rres);

    }

    public static int minimum(int[] arr,int idx) 
    {
        if(arr.length==idx)
            return (int)1e9;
        int rres=minimum(arr, idx+1);
        return Math.min(arr[idx],rres);
    }

    public static boolean find(int[] arr, int idx, int data)
    {
        if(idx==arr.length)
            return false;
        if(arr[idx]==data)
            return true;
        return find(arr, 1+idx, data);
    }

    public static int firstIndex(int[] arr, int idx, int data) 
    {
        if(idx==arr.length)
            return -1;
        if(arr[idx]==data)
            return idx;
        return firstIndex(arr, 1+idx, data);
    }

    public static int lastIndex(int[] arr, int idx, int data) 
    {
        if(idx==arr.length)
            return -1;
        int rres=lastIndex(arr, idx+1, data);
        if(rres!=-1)
            return rres;
        else
        {
            if(arr[idx]==data)
            {
                return idx;
            }
            else
                return -1;
        }
    }

    public static int[] allIndices(int arr[],int idx, int data,int osf)
    {
        if(idx==arr.length)
        {
            return new int[osf];
        }

        if(arr[idx]==data)
        {
            int ans[]=allIndices(arr, idx+1, data, osf+1);
            ans[osf]=idx;
            return ans;
        }
        else
        {
            return allIndices(arr, idx+1, data, osf);
        }
    }

    public static ArrayList<String> subsequence(String str,int idx)
    {
        if(idx==str.length())
        {
            ArrayList<String> al=new ArrayList<>();
            al.add("");
            return al;
        }
        ArrayList<String> rans=subsequence(str, idx+1);
        ArrayList<String> myAns=new ArrayList<>();
        for(String s:rans)
            myAns.add(s);
        for(String s:rans)
            myAns.add(str.charAt(idx)+s);
        return myAns;
    }

    public static int subsequence(String str,int idx, String asf, ArrayList<String> ans)
    {
        if(idx==str.length())
        {
            ans.add(asf);
            return 1;
        }
        return subsequence(str, idx+1, asf, ans)+subsequence(str, idx+1, asf+str.charAt(idx), ans);
    }


















    public static String[] nokiaKeys = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };


    // return type
    public static ArrayList<String> nokiaKeyPad(String str)
    {
        if(str.length()==0)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> rres=nokiaKeyPad(str.substring(1));   //  [d,e,f]
        ArrayList<String> ans=new ArrayList<>();
        String word=nokiaKeys[str.charAt(0)-'0'];       // "abc"

        for(int i=0;i<word.length();i++)
        {
            for(String s:rres)
            {
                ans.add(word.charAt(i)+s);
            }
        }
        return ans;
    }

    // way up
    public static int nokiaKeyPad(String str, String ans) 
    {
        if (str.length() == 0) 
        {
            System.out.println(ans);
            return 1;
        }

        char ch=str.charAt(0);
        String word=nokiaKeys[ch-'0'];

        int total=0;
        for(int i=0;i<word.length();i++)
        {
            total+=nokiaKeyPad(str.substring(1), ans+word.charAt(i));
        }
        return total;

    }
    


    // return type
    public static ArrayList<String> stairPath(int n)
    {
        if(n==0)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans=new ArrayList<>();
        for(int i=1;i<=3;i++)
        {
            if(n-i<0)
                break;
            ArrayList<String> rres=stairPath(n-i);
            for(String s:rres)
            {
                ans.add(i+s);
            }
        }
        return ans;
    }


    // way up
    public static int stairPath(int n, String psf,ArrayList<String> ans) 
    {
        if(n==0)
        {
            ans.add(psf);
            return 1;
        }
        int total=0;
        for(int i=1;i<=3;i++)
        {
            if(n-i<0)
                break;
            total+=stairPath(n-i, psf+i, ans);
        }
        return total;
    }




    // return type
    public static ArrayList<String> boardPath(int n)
    {
        if(n==0)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> ans=new ArrayList<>();
        for(int i=1;i<=6;i++)
        {
            if(n-i<0)
                break;
            ArrayList<String> rres=boardPath(n-i);
            for(String s:rres)
            {
                ans.add(i+s);
            }
        }
        return ans;
    }

    // way up
    public static int boardPath(int n, String psf,ArrayList<String> ans) 
    {
        if(n==0)
        {
            ans.add(psf);
            return 1;
        }
        int c=0;
        for(int i=1;i<=6;i++)
        {   
            if(n-i<0)   break;
            c+=boardPath(n-i, psf+i, ans);
        }
        return c;
    }



   /*  public static int boardPath(int[] arr, int src, int n, String ans) 
    {

    } */


    // return type
    public static ArrayList<String> mazePath_HVD(int sr,int sc,int dr,int dc)
    {
        if(sr>dr || sc>dc)
        {
            return new ArrayList<>();
        }
        else if(sr==dr && sc==dc)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> hor=mazePath_HVD(sr, sc+1, dr, dc);
        ArrayList<String> ver=mazePath_HVD(sr+1, sc, dr, dc);
        ArrayList<String> dia=mazePath_HVD(sr+1,sc+1,dr,dc);
        ArrayList<String> ans=new ArrayList<>();

        for(String h:hor)
        {
            ans.add('h'+h);
        }
        for(String v:ver)
        {
            ans.add('v'+v);
        }
        for(String d:dia)
        {
            ans.add('d'+d);
        }
        return ans;
    }

    // way up
    public static int mazePath_HVD(int sr, int sc, int dr, int dc,  String psf,ArrayList<String> ans) 
    {
        if(sr>dr || sc>dc)
        {
            return 0;
        }
        else if(sr==dr && sc==dc)
        {
            ans.add(psf);
            return 1;
        }
        return mazePath_HVD(sr, sc+1, dr, dc, psf+'h', ans)+mazePath_HVD(sr+1, sc, dr, dc, psf+'v', ans)+mazePath_HVD(sr+1,sc+1,dr,dc,psf+'d',ans);

    }





    public static ArrayList<String> mazePath_HVD_multi(int sr,int sc,int dr,int dc)
    {
        if(sr==dr && sc==dc)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        else if(sr>dr || sc>dc)
        {
            return new ArrayList<>();
        }
        ArrayList<String> hor=new ArrayList<>();
        ArrayList<String> ver=new ArrayList<>();
        ArrayList<String> dia=new ArrayList<>();
        ArrayList<String> ans=new ArrayList<>();
        
        for(int i=1;i+sc<=dc;i++)
        {
            hor=mazePath_HVD_multi(sr,sc+i,dr,dc);
            for(String s:hor)
            {
                ans.add("h"+i+s);
            }
        }
        
        for(int j=1;j+sr<=dr;j++)
        {
            ver=mazePath_HVD_multi(sr+j,sc,dr,dc);
            for(String s:ver)
            {
                ans.add("v"+j+s);
            }
        }
        
        for(int i=1;i+sc<=dc && i+sr<=dr;i++)
        {
            dia=mazePath_HVD_multi(sr+i,sc+i,dr,dc);
            for(String s:dia)
            {
                ans.add("d"+i+s);
            }
        }
        return ans;    
    } 

    public static int mazePath_HVD_multi(int sr, int sc, int dr, int dc,  String psf,ArrayList<String> ans) 
    {
        if(sr==dr && sc==dc)
        {
            ans.add(psf);
            return 1;
        }
        int count=0;

        for(int i=1;i+sc<=dc;i++)
        {
            count+=mazePath_HVD_multi(sr, sc+i, dr, dc, psf+'h'+i, ans);
        }

        for(int j=1;j+sr<=dr;j++)
        {
            count+=mazePath_HVD_multi(sr+j, sc, dr, dc, psf+'v'+j, ans);
        }

        for(int i=1;i+sc<=dc && i+sr<=dr;i++)
        {
            count+=mazePath_HVD_multi(sr+i, sc+i, dr, dc, psf+'d'+i, ans);
        }
        return count;
    }



    public static int maze_path_multi_HVD(int sr,int sc,int er,int ec,String psf,int dir[][],String dirs[],ArrayList<String> ans)
    {
        if(sr==er && sc==ec)
        {
            ans.add(psf);
            return 1;
        }
        int count=0;
        for(int i=0;i<dir.length;i++)
        {
            for(int rad=1;rad<=Math.max(er,ec);rad++)
            {
                int r=sr+rad*dir[i][0];
                int c=sc+rad*dir[i][1];
                if(r>=0 && c>=0 && r<=er && c<=ec)
                    count+=maze_path_multi_HVD(r, c, er, ec, psf+dirs[i]+rad, dir, dirs, ans);
                else    break;
            }
        }
        return count;
    }   

    public static ArrayList<String> maze_path_multi_HVD(int sr,int sc,int er,int ec,int dir[][],String dirs[])
    {
        if(sr==er && sc==ec)
        {
            ArrayList<String> base=new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String>  myAns=new ArrayList<>();
        for(int i=0;i<dir.length;i++)
        {
            for(int rad=1;rad<=Math.max(er,ec);rad++)
            {
                int r=sr+rad*dir[i][0],c=sc+rad*dir[i][1];
                if(r>=0 && c>=0 && r<=er && c<=ec)
                {
                    ArrayList<String> sans=maze_path_multi_HVD(r,c,er,ec,dir,dirs);
                    for(String s:sans)
                    {
                        myAns.add(dirs[i]+rad+s);
                    }
                }
                else break; 
            }
        }
        return myAns;
    }
    
    public static void maze_path()
    {
        int dir[][]={{0,1},{1,0},{1,1}};
        String dirs[]={"H","V","D"};
        System.out.println(maze_path_multi_HVD(0,0,2,2,dir,dirs));
    }
    public static void main(String[] args) 
    {       
          
        maze_path();

    }


}