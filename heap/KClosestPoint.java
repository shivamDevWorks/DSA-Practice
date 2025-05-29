import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

class Pair implements Comparable<Pair>{
    int dist;
    List<Integer> p;
    
    public Pair(int dist,List<Integer> p){
        this.dist=dist;
        this.p=p;
    }
    
    public int compareTo(Pair p1){
        return Integer.compare(this.dist,p1.dist);
    }
}
class KClosestPoint {
    public static List<List<Integer>> kClosestPoints(List<List<Integer>> points, int k) {
        // WRITE YOUR BRILLIANT CODE HERE
        PriorityQueue<Pair> pq=new PriorityQueue<>();
        for(List<Integer> pair:points){
            int x=pair.get(0);
            int y=pair.get(1);
            int d=x*x+y*y;
            pq.add(new Pair(d,pair));
        }
        
        List<List<Integer>> ans=new ArrayList<>();
        while(k-->0){
            ans.add(pq.poll().p);
        }
        return ans;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pointsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> points = new ArrayList<>();
        for (int i = 0; i < pointsLength; i++) {
            points.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<List<Integer>> res = kClosestPoints(points, k);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
