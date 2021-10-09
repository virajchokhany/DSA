import java.util.*;

import javax.swing.plaf.synth.SynthStyle;

public class BinaryTree
{
    public static class Node
    {
        int data;
        Node left;
        Node right;

        Node()
        {

        }
        Node(int data,Node left,Node right)
        {
            this.data=data;
            this.left=left;
            this.right=right;
        }
        Node(int data)
        {
            this(data,null,null);
        }
    }

    public static int size(Node root)
    {
        if(root==null)
            return 0;
        return 1+size(root.left)+size(root.right);
    }

    public static int sum(Node root)
    {
        if(root==null)
            return 0;
        return root.data+sum(root.left)+sum(root.right);
    }

    public static int max(Node root)
    {
        if(root==null)
        {
            return -(int)1e9;
        }
        return Math.max(root.data,Math.max(max(root.left),max(root.right)));
    }

    public static int min(Node root)
    {
        if(root==null)
        {
            return (int)1e9;
        }
        return Math.min(root.data,Math.min(min(root.left),min(root.right)));
    }

    public static int height(Node root)
    {
        if(root==null)
            return -1;
        
        return Math.max(height(root.left),height(root.right))+1;
    }

    public static int countLeaves(Node node)
    {
        if(node==null)
            return 0;
        else if(node.left==null && node.right==null)
            return 1;
        
        return countLeaves(node.left)+countLeaves(node.right);
    }

    // prints the nodes which have exactly one child
    public static void exactlyOneChild(Node root,ArrayList<Integer> ans)
    {
        if(root==null)
            return;
        
        else if((root.left==null && root.right!=null) || (root.left!=null && root.right==null))
            ans.add(root.data);
        
        exactlyOneChild(root.left,ans);
        exactlyOneChild(root.right,ans);
    }

    public static int countExactlyOneChildNodes(Node node)
    {
        if(node==null)
            return 0;
        
        int lans=countExactlyOneChildNodes(node.left);
        int rans=countExactlyOneChildNodes(node.right);
        if((node.left==null && node.right!=null) || (node.left!=null && node.right==null))
            return 1+lans+rans;
        else
            return lans+rans;
    }



    public static boolean find(Node node,int data)
    {
        if(node==null)
            return false;
        else if(node.data==data)
            return true;
        return find(node.left,data) || find(node.right,data);
    }

    public static boolean nodeToRootPath(Node node,int data,ArrayList<Node> ans)
    {
        if(node==null)
            return false;
        else if(node.data==data)
        {   
            ans.add(node);
            return true;
        }
        boolean res=nodeToRootPath(node.left,data,ans) || nodeToRootPath(node.right,data,ans);
        if(res)
        {
            ans.add(node);
        }
        return res;
    }

    public static ArrayList<Node> nodeToRootPath(Node node,int data)
    {
        ArrayList<Node> list=new ArrayList<>();
        nodeToRootPath(node, data,list);
        return list;
    }

    public static ArrayList<Node> nrp_(Node node,int data)
    {
        if(node==null)
            return null;
        else if(node.data==data)
        {
            ArrayList<Node> al=new ArrayList<>();
            al.add(node);
            return al;
        }

        ArrayList<Node> left=nrp_(node.left, data);
        if(left!=null)
        {
            left.add(node);
            return left;
        }
        left=nrp_(node.right,data);
        if(left!=null)
        {
            left.add(node);
            return left;
        }
        return null;
    }

    public static ArrayList<Node> nrp(Node node,int data)
    {
        ArrayList<Node> ans=nrp_(node, data);
        return ans==null?new ArrayList<>():ans;
    }



    public static void printKLevelsDown(Node node,int k,Node block,ArrayList<Integer> ans)
    {
        if(node==null || k<0 || node==block)
            return;
        if(k==0)
        {
            ans.add(node.data);
            return;
        }
        printKLevelsDown(node.left,k-1,block,ans);
        printKLevelsDown(node.right,k-1,block,ans);
    }


    ////    vvvvvvvvvvvvvvvvvvvvvv imp

    public static ArrayList<Integer> kaway(Node node,int k,int data)
    {
        ArrayList<Integer> ans=new ArrayList<>();
        ArrayList<Node> ntrp=nodeToRootPath(node, data);
        Node block=null;

        for(int i=0;i<ntrp.size();i++)
        {
            printKLevelsDown(ntrp.get(i), k-i,block, ans);
            block=ntrp.get(i);
        }
        return ans;
    }

    ///// super vvvvvvvvvvvv imp
    public static int kaway(Node node,int k,int data,ArrayList<Integer> ans)
    {
        if(node==null)
        {
            return -1;
        }
        else if(node.data==data)
        {
            printKLevelsDown(node, k, null, ans);
            return 1;
        }

        int lans=kaway(node.left, k, data, ans);
        if(lans!=-1)
        {
            printKLevelsDown(node, k-lans, node.left, ans);
            return lans+1;
        }
        else
        {
            int rans=kaway(node.right, k, data, ans);
            if(rans!=-1)
            {
                printKLevelsDown(node, k-rans, node.right, ans);
                return rans+1;
            }
            else
            {
                return -1;
            }
        }
    }

    static Node prev=null;
    public static boolean isBST(Node root)
    {
        if(root==null)
            return true;
        
        boolean left=isBST(root.left);
        if(!left)   return false;
        
        if(prev!=null && root.data<prev.data)
        {
            return false;
        }
        prev=root;
        boolean right=isBST(root.right);
        if(right==false)
            return false;
        
        return true;
        
    }

    public static void BFS(Node node)
    {
        if(node==null)  return;
        LinkedList<Node> que=new LinkedList<>();
        que.addLast(node);
        int level=0;
        while(que.size()>0)
        {
            int size=que.size();
            System.out.print("LEVEL "+level+" -> ");
            while(size-->0)
            {
                Node rn=que.removeFirst();
                System.out.print(rn.data+" ");
                if(rn.left!=null)
                    que.addLast(rn.left);
                if(rn.right!=null)
                    que.addLast(rn.right);
            }
            System.out.println();
            level++;
        }
    }

    
    public static void main(String[] args) {
        
    }
}