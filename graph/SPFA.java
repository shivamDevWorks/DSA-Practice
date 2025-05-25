import java.util.*;
import java.util.Map.*;

class SPFA{

    public static int bfs(List<List<Entry<Integer, Integer>>> graph, int a, int b){
        ArrayList<Integer> dist=new ArrayList<>();
        for(int i=0;i<graph.size();i++){
            dist.add(Integer.MAX_VALUE);
        }
        dist.set(a,0);
        ArrayDeque<Integer> queue=new ArrayDeque<>();
        queue.offerLast(a);

        boolean visited[]=new boolean[graph.size()];
        visited[a]=true;
        while(!queue.isEmpty()){
            int node=queue.pollFirst();

            for(Entry<Integer,Integer> neigh:graph.get(node)){
                int child=neigh.getKey();
                int childWeight=neigh.getValue();
                if(dist.get(child)>dist.get(node)+childWeight){
                    dist.set(child,dist.get(node)+childWeight);
                    if(!visited[child]){
                        visited[child]=true;
                        queue.offerLast(child);
                    }
                }

            }
        }

        return dist.get(b)==Integer.MAX_VALUE?0:dist.get(b);
    }
    public static int shortestPath(List<List<Entry<Integer, Integer>>> graph, int a, int b) {
        return bfs(graph, a, b);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int graphLength = Integer.parseInt(scanner.nextLine());
        List<List<Entry<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i < graphLength; i++) {
            String s = scanner.nextLine();
            List<String> l = splitWords(s);
            List<Entry<Integer, Integer>> lm = new ArrayList<>();
            for (int j = 0; j < l.size(); j += 2) {
                lm.add(Map.entry(
                        Integer.parseInt(l.get(j)),
                        Integer.parseInt(l.get(j + 1))));
            }
            graph.add(lm);
        }
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = shortestPath(graph, a, b);
        System.out.println(res);
    }
}