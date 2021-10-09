import java.util.LinkedList;
import java.util.Stack;
import java.util.Arrays;
public class questions {
    
    public static boolean balancedBrackets(String s)
    {
        LinkedList<Character> st=new LinkedList<>();
        int n=s.length();
        boolean res=true;
        for(int i=0;i<n;i++)
        {
            char ch=s.charAt(i);
            
            if(ch=='(' || ch=='{' || ch=='[')
                st.addFirst(ch);
                
            else if(ch==')' || ch=='}' || ch==']')
            {
                if(st.size()==0)
                {
                    res=false;
                    break;
                }
                char top=st.removeFirst();
                if((ch==')' && top!='(') || (ch=='}' && top!='{') || (ch==']' && top!='['))
                {
                    res=false;
                    break;
                }
            }
        }
        if(st.size()>0)
        {
            res=false;
        }
        return res;
    }

    public static boolean duplicateBrackets(String s)
    {
        
        LinkedList<Character> st=new LinkedList<>();
        int n=s.length();
        boolean res=false;
        for(int i=0;i<n;i++)
        {
            char ch=s.charAt(i);
            
            if(ch==')')
            {
                char top=st.getFirst();
                if(top=='(')
                {
                    res=true;
                    break;
                }
                else
                {
                    while(st.getFirst()!='(')
                    {
                        st.removeFirst();
                    }
                    st.removeFirst();
                }
            }
            else
            {
                st.addFirst(ch);
            }
        }
        
        return res;
    }

    public static int[] nextGreaterToRight(int arr[])
    {
        int n=arr.length;
        int ngr[]=new int[n];
        
        Stack<Integer> st=new Stack<>();
        
        for(int i=n-1;i>=0;i--)
        {
            if(st.size()==0)
            {
                ngr[i]=-1;
            }
            else if(arr[i]<arr[st.peek()])
            {
                ngr[i]=arr[st.peek()];
            }
            else
            {
                while(st.size()>0 && arr[i]>=arr[st.peek()])
                {
                    st.pop();
                }
                if(st.size()==0)
                {
                    ngr[i]=-1;
                }
                else
                {
                    ngr[i]=arr[st.peek()];
                }
            }
            st.push(i);
        }
        return ngr;
    }

    public static int celebrityProblem(int arr[][],int n)
    {
        int celebrity=0;
    	for(int i=1;i<n;i++)
    	{
    	    if(arr[celebrity][i]!=0)
    	    {
    	        celebrity=i;   
    	    }
    	}
    	boolean ans=true;
    	for(int i=0;i<n;i++)
    	{
    	    if(celebrity!=i && (arr[celebrity][i]==1 || arr[i][celebrity]==0))
    	        ans=false;
    	}
    	if(!ans)
    	    return -1;
    	else
    	    return celebrity;
    }

    static class Pair
    {
        int start;
        int end;
        Pair()
        {
            
        }
        Pair(int start,int end)
        {
            this.start=start;
            this.end=end;
        }
    }
    private static void print(Stack<Pair> st)
    {
        if(st.size()==0)
            return;
        Pair p=st.pop();
        print(st);
        System.out.println(p.start+" "+p.end);
    }
    public static void mergeOverlappingIntervals(int[][] arr) 
    {
        
        Arrays.sort(arr,(a,b)->
        {
            return a[0]-b[0];
        });
        
        Stack<Pair> st=new Stack<>();
        int n=arr.length;
        st.push(new Pair(arr[0][0],arr[0][1]));
        
        for(int i=1;i<n;i++)
        {
            if(arr[i][0]>st.peek().end)
            {
                st.push(new Pair(arr[i][0],arr[i][1]));
            }
            else
            {
                Pair p=st.pop();
                st.push(new Pair(p.start,Math.max(p.end,arr[i][1])));
            }
        }
        
        print(st);
    }
}
