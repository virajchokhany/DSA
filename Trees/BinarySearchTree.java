import java.util.ArrayList;

public class BinarySearchTree {
    
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

    public static int maximum(Node node)
    {
        if(node==null)
            return -(int)1e9;
        
        while(node.right!=null)
            node=node.right;
        return node.data;
    }

    public static int minimum(Node node)
    {
        if(node==null)
            return (int)1e9;
        
        while(node.left!=null)
            node=node.left;
        return node.data;
    }

    public static boolean find(Node node,int data)
    {
        if(node==null)
            return false;
        while(node!=null)
        {
            if(node.data>data)
            {
                node=node.left;
            }
            else if(node.data<data)
            {
                node=node.right;
            }
            else
                return true;
        }
        return false;
    }

    public static ArrayList<Node> NodeToRootPath(Node node,int data)
    {
        if(node==null)
            return null;
        ArrayList<Node> list=new ArrayList<>();
        boolean found=false;
        while(node!=null)
        {
            list.add(node);
            if(node.data>data)
                node=node.left;
            else if(node.data<data)
                node=node.right;
            else
            {
                found=true;
                break;
            }
        }

        if(!found)
            return new ArrayList<>();
        else
        {
            int i=0,j=list.size()-1;
            while(i<j)
            {
                Node ith=list.get(i);
                Node jth=list.get(j);
                list.set(i, jth);
                list.set(j,ith);
            }
            return list;
        }
    }

    // T- O(logN)
    // S- O(logN)
    public static Node LowestCommonAncestor(Node node,int data1,int data2)
    {
        if(node==null)
            return null;
        ArrayList<Node> list1=NodeToRootPath(node, data1);
        ArrayList<Node> list2=NodeToRootPath(node, data2);
        int i=list1.size()-1,j=list2.size()-1;

        while(list1.get(i)==list2.get(j))
        {
            i--;
            j--;
        }
        return list1.get(i+1);    
    }

    public static Node LowestCommmonAncestor_01(Node node,int data1,int data2)
    {
        if(node==null)
            return null;
        
        while(node!=null)
        {
            if(node.data>data1 && node.data>data2)
                node=node.left;
            else if(node.data<data1 && node.data<data2)
                node=node.right;
            else
                break;
        }
        return node;
    }

    public static Node addData(Node node,int data)
    {
        if(node==null)
        {
            Node newNode=new Node(data);
            return newNode;
        }
        if(node.data>data)
        {
            node.left=addData(node.left, data);
        }
        else if(node.data<data)
        {
            node.right=addData(node.right, data);
        }
        return node;
    }



    public Node getMaximumNode(Node root)
    {
        if(root==null)
            return null;
        while(root.right!=null)
            root=root.right;
        return root;
    }
    public Node deleteNode(Node node, int key) {
        
        if(node==null)
            return null;
        
        if(node.data>key)
        {
            node.left=deleteNode(node.left,key);
        }
        else if(node.data<key)
        {
            node.right=deleteNode(node.right,key);
        }
        else
        {
            if(node.left==null && node.right==null)
            {
                return null;
            }
            else if(node.left==null && node.right!=null)
            {
                return node.right;
            }
            else if(node.left!=null && node.right==null)
            {
                return node.left;
            }
            else
            {
                Node mxNode=getMaximumNode(node.left);
                node.data=mxNode.data;
                node.left=deleteNode(node.left,mxNode.data);
            }
        }
        return node;
        
    }
}
