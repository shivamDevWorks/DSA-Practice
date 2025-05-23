import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

class Solution {
    
    public static HashMap<Integer,Integer> getIndegree(HashMap<Integer,List<Integer>> map){
        
        HashMap<Integer,Integer> indegree=new HashMap<>();
        
        for(int i=1;i<=map.size();i++){
            indegree.put(i,0);
        }
        
        for(int key:map.keySet()){
            
            for(int i:map.get(key)){
                indegree.put(i,indegree.get(i)+1);
            }
        }
        
        return indegree;
    }
    
    
    public static boolean topoSort(HashMap<Integer,List<Integer>> map,HashMap<Integer,Integer> indegree){
        
        Queue<Integer> bfs=new LinkedList<>();
        for(int i=1;i<=map.size();i++){
            if(indegree.get(i)==0){
                indegreeCount++;
                bfs.offer(i);
            }
        }
        
        if(bfs.size()>1){
            return false;
        }
        
        List<Integer> res=new ArrayList<>();
        
        while(!bfs.isEmpty()){
            int node=bfs.poll();
            res.add(node);
            for(int neigh:map.get(node)){
                indegree.put(neigh,indegree.get(neigh)-1);
                if(indegree.get(neigh)==0){
                    bfs.offer(neigh);
                    if(bfs.size()>1){
                        return false;
                    }
                }
                
            }    
        }
        
        return res.size()==map.size();
        
        
        
        
    }
    public static boolean sequenceReconstruction(List<Integer> original, List<List<Integer>> seqs) {
        // WRITE YOUR BRILLIANT CODE HERE
        HashMap<Integer,List<Integer>> map=new HashMap<>();
        for(int i:original){
            map.put(i,new ArrayList<Integer>());
        }
        for(int i=0;i<seqs.size();i++){
            if(seqs.get(i).size()>1){
                for(int j=1;j<seqs.get(i).size();j++){
                    map.get(seqs.get(i).get(j-1)).add(seqs.get(i).get(j));
                }
            }
        }
        
        HashMap<Integer,Integer> indegree=getIndegree(map);
        
        
        
        return topoSort(map,indegree);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> original = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int seqsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> seqs = new ArrayList<>();
        for (int i = 0; i < seqsLength; i++) {
            seqs.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        scanner.close();
        boolean res = sequenceReconstruction(original, seqs);
        System.out.println(res);
    }
}
