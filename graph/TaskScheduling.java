import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.*;

class TaskScheduling {
    
     public static List<String> topologicalSort(HashMap<String,List<String>> map,List<String> tasks){
        HashMap<String,Integer> inDegree=new HashMap<>();
        for(String task:tasks){
            inDegree.put(task,0);
        }

        for(String key:map.keySet()){
            for(String task:map.get(key)){
                inDegree.put(task,inDegree.get(task)+1);
            }
        }
    
        ArrayDeque<String> bfs=new ArrayDeque<>();
        for(String task:tasks){
            if(inDegree.get(task)==0){
                bfs.offerLast(task);
            }
        }

        List<String> res=new ArrayList<>();

        while(!bfs.isEmpty()){
            String node=bfs.pollFirst();
            res.add(node);

            for(String task:map.get(node)){
                inDegree.put(task,inDegree.get(task)-1);
                if(inDegree.get(task)==0){
                    bfs.offerLast(task);
                }
            }
        }

        return res;
    }
    public static List<String> taskScheduling(List<String> tasks, List<List<String>> requirements) {
        // WRITE YOUR BRILLIANT CODE HERE
        HashMap<String,List<String>> map=new HashMap<>();
        for(String task:tasks){
            map.put(task,new ArrayList<String>());
        }
        
        for(List<String> list:requirements){
            map.get(list.get(0)).add(list.get(1));
        }
        return topologicalSort(map,tasks);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = splitWords(scanner.nextLine());
        int requirementsLength = Integer.parseInt(scanner.nextLine());
        List<List<String>> requirements = new ArrayList<>();
        for (int i = 0; i < requirementsLength; i++) {
            requirements.add(splitWords(scanner.nextLine()));
        }
        scanner.close();
        List<String> res = taskScheduling(tasks, requirements);
        if (res.size() != tasks.size()) {
            System.out.println("output size " + res.size() + " does not match input size " + tasks.size());
            return;
        }
        HashMap<String, Integer> indices = new HashMap<>();
        for (int i = 0; i < res.size(); i++) {
            indices.put(res.get(i), i);
        }
        for (List<String> req : requirements) {
            for (String task : req) {
                if (!indices.containsKey(task)) {
                    System.out.println("'" + task + "' is not in output");
                    return;
                }
            }
            String a = req.get(0);
            String b = req.get(1);
            if (indices.get(a) >= indices.get(b)) {
                System.out.println("'" + a + "' is not before '" + b + "'");
                return;
            }
        }
        System.out.println("ok");
    }
}
