
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

//import jdk.javadoc.internal.doclets.formats.html.resources.standard;
public class graph {

    static class Edge
    {
        int src;
        int nbr;
        int wt;
        Edge(int src,int nbr,int wt)
        {
            this.src=src;
            this.nbr=nbr;
            this.wt=wt;
        }
    }
     
    public static void addEdge(ArrayList<Edge> graph[],int u,int v,int w)
    {
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }
    public static void construction()
    {
        int N=7;
        ArrayList<Edge> graph[]=new ArrayList[N];
        for(int i=0;i<N;i++)    
            graph[i]=new ArrayList<>();
        addEdge(graph,0,1,10);
        addEdge(graph,0,3,10);
        addEdge(graph,1,2,10);
        addEdge(graph, 2,3,40);
        addEdge(graph,3,4,2);
        addEdge(graph, 4,5,2);
        addEdge(graph, 4,6,8);
        addEdge(graph, 5,6,3);
        
        //ceilAndFloor(graph);
        //System.out.println(isConnected(graph));
        //System.out.println(gcc(graph));
        //hamiltonainPathAndCycle(graph, 0, "0", 0, new boolean[graph.length], 0);
        //BFS(graph, 0);

        printBFS(graph);
    }
    public static void printBFS(ArrayList<Edge> graph[])
    {
        Queue<triple> q=new ArrayDeque<>();
        q.add(new triple(0, "0", 0));
        boolean vis[]=new boolean[graph.length];

        while(q.size()>0)
        {
            triple rn=q.remove();

            if(vis[rn.vtx]==true)
                continue;
            vis[rn.vtx]=true;
            System.out.println(rn.vtx+"->"+rn.psf+"@"+rn.wsf);

            for(Edge E:graph[rn.vtx])
            {
                if(vis[E.nbr]==false)
                {
                    q.add(new triple(E.nbr, rn.psf+E.nbr, rn.wsf+E.wt));
                }
            }
        }
    }
    public static class Pair
    {
        int wt;
        String path;
        String exists="NO";
        Pair(int wt,String path,String exists)
        {
            this.wt=wt;
            this.path=path;
            this.exists=exists;
        }
        Pair()
        {

        }
    }

    /*
            DFS TIME COMPLEXITY- O(E)       E is the number of edges
            GCC COUNT TIME COMPLEXITY- O(E+V)

    */
    public static Pair heaviest(ArrayList<Edge> graph[],int src,int dest, boolean vis[])
    {
        if(src==dest)
        {
            return new Pair(0,dest+"","YES");
        }
        vis[src]=true;
        Pair myAns=new Pair();
        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                Pair recAns=heaviest(graph, E.nbr, dest,vis);
                if(recAns.exists=="YES" && myAns.wt<recAns.wt+E.wt)
                {
                   myAns.path=src+recAns.path;
                   myAns.wt=recAns.wt+E.wt;
                   myAns.exists="YES";
                }
            }
        }
        vis[src]=false;
        return myAns;
    }
    public static class ceilFloor
    {
        int ceil=(int)1e9;
        int floor=-(int)1e9;
    }
    public static void ceilAndFloor(ArrayList<Edge> graph[],int src, int data,boolean vis[], int wsf,ceilFloor pair)
    {
        if(wsf>data)
        {
            pair.ceil=Math.min(wsf,pair.ceil);
        }   
        else if(wsf<data)
        {   
            pair.floor=Math.max(wsf,pair.floor);
        }
        vis[src]=true;
        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                ceilAndFloor(graph,E.nbr,data,vis,wsf+E.wt,pair);
            }
        }
        vis[src]=false;
    }
    public static void dfs(ArrayList<Edge> graph[],int src,boolean vis[])
   {
       vis[src]=true;
       for(Edge E:graph[src])
       {
           if(vis[E.nbr]==false)
           {
               dfs(graph,E.nbr,vis);
           }
       }   
   }
    public static boolean isConnected(ArrayList<Edge> graph[])
    {
        int count=0;
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<vis.length;i++){
            if(count>1) break;
            if(vis[i]==false){
                count++;
                dfs(graph,i,vis);

            }
        }
        if(count>1)
            return false;
        return true;
    }
    public static ArrayList<ArrayList<Integer>> gcc(ArrayList<Edge> graph[])
    {
        ArrayList<ArrayList<Integer>> comps = new ArrayList<ArrayList<Integer>>();
      
        boolean vis[]=new boolean[graph.length];
        for(int i=0;i<vis.length;i++)
        {
            if(vis[i]==false)
            {
                ArrayList<Integer> list=new ArrayList<>();
                dfs(graph,i,vis,list);
                comps.add(list);
            }
        }
        return comps;
    }
    public static void dfs(ArrayList<Edge> graph[],int src, boolean vis[],ArrayList<Integer> list)
    {
       list.add(src);
       vis[src]=true;
       for(Edge E:graph[src])
       {
           if(vis[E.nbr]==false)
           {
               dfs(graph,E.nbr,vis,list);
           }
       }
    }
    public static void ceilAndFloor(ArrayList<Edge> graph[])
    {
        ceilFloor pair=new ceilFloor();
        ceilAndFloor(graph,0,42,new boolean[graph.length],0,pair);
        System.out.println("Ceil "+pair.ceil);
        System.out.println("Floor "+pair.floor);
    }
    public static void hamiltonainPathAndCycle(ArrayList<Edge> graph[],int src,String psf,int idx,boolean vis[],int osrc)
    {
       if(idx==graph.length-1)
       {
           boolean star=false;
           for(Edge E:graph[src])
           {
               if(E.nbr==osrc)
               {
                   star=true;
                   break;
               }
           }
           System.out.print(psf);
           if(star)
           System.out.println("*");
           else
           System.out.println(".");
           return;
       }
       
        vis[src]=true;
        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                hamiltonainPathAndCycle(graph,E.nbr,psf+E.nbr,idx+1,vis,osrc);
            }
        }
        vis[src]=false;
    }
    public static Pair lightest(ArrayList<Edge> graph[],int src,int dest, boolean vis[])
    {
        if(src==dest){
            return new Pair(0,dest+"","YES");
        }
        vis[src]=true;
        Pair myAns=new Pair();
        myAns.wt=Integer.MAX_VALUE;
        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                Pair recAns=lightest(graph, E.nbr, dest,vis);
                if(recAns.exists=="YES" && myAns.wt>recAns.wt+E.wt)
                {
                   myAns.path=src+recAns.path;
                   myAns.wt=recAns.wt+E.wt;
                   myAns.exists="YES";
                }
            }
        }
        vis[src]=false;
        return myAns;
    }
    public static int printAllPaths(ArrayList<Edge> graph[],int src,int dest,String psf, boolean vis[])
    {
        if(src==dest){
            System.out.println(psf);
            return 1;
        }
        vis[src]=true;
        int count=0;
        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                count+=printAllPaths(graph, E.nbr, dest, psf+E.nbr, vis);
            }
        }
        vis[src]=false;
        return count;
    }
    public static void removeVtx(ArrayList<Edge> graph[],int u)
    {
        while(graph[u].size()>0)
        {
            Edge E=graph[u].get(0);
            removeEdge(graph, E.src, E.nbr);
        }
    }
    public static void display(ArrayList<Edge> graph[],int N)
    {
        for(int i=0;i<N;i++)
        {
            System.out.print(i+" -> ");
            for(Edge E:graph[i])
            {
                System.out.print("("+E.nbr+","+E.wt+") ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void preorder(ArrayList<Edge> graph[],int src,String psf,int wsf,boolean vis[])
    {
        System.out.println(src+" -> "+psf+"@"+wsf);
        vis[src]=true;

        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                preorder(graph,E.nbr,psf+E.nbr,wsf+E.wt,vis);
            }
        }
        vis[src]=false;
    }
    public static void postorder(ArrayList<Edge> graph[],int src,String psf,int wsf,boolean vis[])
    {
        
        vis[src]=true;

        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                postorder(graph,E.nbr,psf+E.nbr,wsf+E.wt,vis);
            }
        }
        vis[src]=false;
        System.out.println(src+" -> "+psf+"@"+wsf);
    }
    public static void BFS(ArrayList<Edge> graph[],int src)
    {
        int level=0;
        Queue<Integer> q=new ArrayDeque<>();
        boolean vis[]=new boolean[graph.length];
        q.add(src);
        boolean isCycle=false;
        while(q.size()>0)
        {
            int size=q.size();
            while(size-->0)
            {
                int rn=q.remove();
                if(vis[rn])
                {
                    isCycle=true;
                    continue;
                }
                vis[rn]=true;
                System.out.println("Level "+level+"\t--> "+rn);
                for(Edge E:graph[rn])
                {
                    if(vis[E.nbr]==false)
                    {
                        q.add(E.nbr);
                    }
                }
            }
            level++;
        }
        System.out.println(isCycle);
    }
    public static boolean hasPath(ArrayList<Edge> graph[],int src,int dest,boolean vis[])
    {
        if(src==dest)
            return true;
        vis[src]=true;
        for(Edge E:graph[src])
        {
            if(vis[E.nbr]==false)
            {
                boolean ans=hasPath(graph,E.nbr,dest,vis);
                if(ans==true)
                    return true;
            }
        }
        return false;
    }
    public static void removeEdge(ArrayList<Edge> graph[],int u,int v)
    {
        for(int i=0;i<graph[u].size();i++)
        {   
            Edge E=graph[u].get(i);
            if(E.src==u && E.nbr==v)
            {
                graph[u].remove(i);
                break;
            }
        }
        for(int i=0;i<graph[v].size();i++)
        {
            Edge E=graph[v].get(i);
            if(E.src==v && E.nbr==u)
            {
                graph[v].remove(i);
                break;
            }
        }
    }
    static class triple
    {
        int vtx;
        String psf;
        int wsf;
        triple(int vtx,String psf,int wsf)
        {
            this.psf=psf;
            this.vtx=vtx;
            this.wsf=wsf;
        }
    }
    public static void main(String args[])
    {
        construction();   
    }
}