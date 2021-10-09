import java.util.ArrayList;
public class genericTree {

    public static class Node
    {
        int data;
        ArrayList<Node> childs;
        Node(int data)
        {
            this.data=data;
        }
    }

    public static int height(Node node)
    {
        int mx=-1;
        for(Node child:node.childs)
        {
            mx=Math.max(mx,height(child));
        }
        return mx+1;
    }

    public static int size(Node node)
    {
        int s=1;
        for(Node child:node.childs)
        {
            s+=size(child);
        }
        return s;
    }

    public static int sum(Node node)
    {
        int s=node.data;
        for(Node child:node.childs)
        {
            s+=size(child);
        }
        return s;
    }

    public static int maximum(Node node)
    {
        int mx=node.data;
        for(Node child:node.childs)
        {
            mx=Math.max(mx,maximum(child));
        }
        return mx;
    }

    public static int minimum(Node node)
    {
        int mn=node.data;
        for(Node child:node.childs)
        {
            mn=Math.min(mn,maximum(child));
        }
        return mn;
    }

    public static boolean find(Node node,int data)
    {
        if(node.data==data)
            return true;
        
        boolean res=false;
        for(Node child:node.childs)
        {
            res=res||find(child,data);
        }
        return res;
    }

    public static ArrayList<Node> nodeToRootPath(Node node,int data)
    {
        if(node.data==data)
        {
            ArrayList<Node> base=new ArrayList<>();
            base.add(node);
            return base;
        }

        for(Node child:node.childs)
        {
            ArrayList<Node> ans=nodeToRootPath(child, data);
            if(ans.size()>0)
            {
                ans.add(node);
                return ans;
            }
        }
        return new ArrayList<>();
    }

    public static int countLeaves(Node node)
    {
        if(node.childs.size()==0)
            return 1;
        
        int sum=0;
        for(Node child:node.childs)
        {
            sum+=countLeaves(child);
        }
        return sum;
    }

    public static Node LCA(Node node,Node p,Node q)
    {
        ArrayList<Node> list1=nodeToRootPath(node, p.data);
        ArrayList<Node> list2=nodeToRootPath(node, q.data);
        int i=list1.size()-1,j=list2.size()-1;
        while(i>=0 && j>=0 && list1.get(i)==list2.get(j))
        {
            i--;
            j--;
        }
        return list1.get(i+1);
    }

    public static int distanceBetweenNodes(Node node, int d1, int d2)
    {
        ArrayList<Node> list1=nodeToRootPath(node, d1);
        ArrayList<Node> list2=nodeToRootPath(node, d2); 
        if(list1.size()==0 || list2.size()==0)
            return -1;
        int i=list1.size()-1,j=list2.size()-1;
        while(i>=0 && j>=0 && list1.get(i)==list2.get(j))
        {
            i--;j--;
        }
        return i+j+2;
    }

    public static int getFloor(Node node,int x)
    {
        int ans=-(int)1e9;
        for(Node child:node.childs)
        {
            int rans=getFloor(child,x);
            ans=Math.max(ans,rans);
        }
        if(node.data<x)
        {
            ans=Math.max(ans,node.data);
        }
        return ans;
    }
    public static int kthLargest(Node node, int k)
    {
        int x=(int)1e9;
        
        for(int i=1;i<=k;i++)
        {
            x=getFloor(node,x);
        }
        return x;
    }
    
}
