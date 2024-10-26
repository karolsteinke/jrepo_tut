package sk;

import java.util.*;  
      
public class GraphExample   
{  
    private int V; // Number of vertices  
    private List<List<Integer>> adjacencyList; // Adjacency list representation  
  
    public GraphExample(int V)   
    {  
        this.V=V;  
        adjacencyList=new ArrayList<>(V);  
  
        // Initialize the adjacency list  
        for (int i=0;i<V;i++)   
        {  
            adjacencyList.add(new ArrayList<>());  
        }  
    }  
  
    // Function to add an edge between two vertices  
    public void addEdge(int source,int destination)   
    {  
        adjacencyList.get(source).add(destination);  
        adjacencyList.get(destination).add(source);  
    }  
  
    // Function to perform Breadth-First Search traversal of the graph  
    public void bfs(int startVertex)   
    {  
        boolean[] visited=new boolean[V];  
        Queue<Integer> queue=new LinkedList<>();  
  
        visited[startVertex]=true;  
        queue.add(startVertex);  
  
        while (!queue.isEmpty())   
        {  
            int currentVertex=queue.poll();  
            System.out.print(currentVertex+" ");  
  
            List<Integer> neighbors=adjacencyList.get(currentVertex);  
            for (int neighbor:neighbors)   
            {  
                if (!visited[neighbor])   
                {  
                    visited[neighbor]=true;  
                    queue.add(neighbor);  
                }  
            }  
        }  
        System.out.println();  
    }  
  
    // Function to perform Depth-First Search traversal of the graph  
    public void dfs(int startVertex)   
    {  
        boolean[] visited=new boolean[V];  
        dfsUtil(startVertex,visited);  
        System.out.println();  
    }  
  
    private void dfsUtil(int vertex,boolean[] visited)   
    {  
        visited[vertex]=true;  
        System.out.print(vertex+" ");  
  
        List<Integer> neighbors=adjacencyList.get(vertex);  
        for (int neighbor:neighbors)   
        {  
            if (!visited[neighbor])   
            {  
                dfsUtil(neighbor,visited);  
            }  
        }  
    }  
  
    public static void main(String[] args)   
    {  
        int V=5; // Number of vertices  
        GraphExample graph=new GraphExample(V);  
  
        // Add edges  
        graph.addEdge(0,1);  
        graph.addEdge(0,2);  
        graph.addEdge(1,3);  
        graph.addEdge(2,3);  
        graph.addEdge(2,4);  
  
        System.out.print("BFS traversal: ");  
        graph.bfs(0);  
  
        System.out.print("DFS traversal: ");  
        graph.dfs(0);  
    }  
}  