import java.util.*;


class Dikstra {
    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here
        
        List<List<Pair>> graph=new ArrayList<>();
        for(int i=0;i<V;i++){
            graph.add(new ArrayList<Pair>());
        }
        
        for(int[] edge:edges){
            graph.get(edge[0]).add(new Pair(edge[1],edge[2]));
            graph.get(edge[1]).add(new Pair(edge[0],edge[2]));
        }
        int dist[]=new int[V];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        pq.add(new Pair(src,0));
        
        while(!pq.isEmpty()){
            Pair top=pq.remove();
            int node=top.node;
            int distance=top.weight;
            
            for(Pair child:graph.get(node)){
                if(dist[child.node]>distance+child.weight){
                    dist[child.node]=distance+child.weight;
                    pq.add(new Pair(child.node,dist[child.node]));
                }
            }
            
        }
        return dist;
        
        
    }

    public static void main(String[] args) {

    }
}