/*
* This problem is similar to Task Scheduling. The primary difference is now we assign times to tasks and we ask for the minimum amount of time to complete all tasks. There will be an extra times array such that times[i] indicates the time required to complete task[i]. You have also invited all your friends to help complete your tasks so you can work on any amount of tasks at a given time. Remember that task a must be completed before completing task b (but the starting times don't need to be in order).

There is guaranteed to be a solution.
*
*
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

class TaskScheduling2 {
    public static HashMap<String,Integer> getIndegree(HashMap<String,List<String>> map){
        HashMap<String,Integer> indegree=new HashMap<>();
        for(String node:map.keySet()){
            indegree.put(node,0);
        }
        
        for(String node:map.keySet()){
            for(String neigh:map.get(node)){
                indegree.put(neigh,indegree.get(neigh)+1);
            }
        }
        return indegree;
    }
    
    public static int minimumTime(List<String> tasks, List<Integer> times,HashMap<String,List<String>> map,HashMap<String,Integer> indegree,HashMap<String,Integer> timeMap){
        
        int ans=0;
        HashMap<String,Integer> dist=new HashMap<>();
        for(String s:tasks){
            dist.put(s,0);
        }
        ArrayDeque<String> bfs=new ArrayDeque<>();
        for(String task:tasks){
            if(indegree.get(task)==0){
                bfs.offerLast(task);
                dist.put(task,timeMap.get(task));
                ans=Math.max(ans,dist.get(task));
            }
        }
        
        while(!bfs.isEmpty()){
                String node=bfs.pollFirst();
                for(String neigh:map.get(node)){
                    dist.put(neigh,Math.max(dist.get(neigh),dist.get(node)+timeMap.get(neigh)));
                    indegree.put(neigh,indegree.get(neigh)-1);
                    ans=Math.max(ans,dist.get(neigh));
                    if(indegree.get(neigh)==0){
                        bfs.offerLast(neigh);
                    }
                    
                }
            
        }
        
        return ans;
    }
    public static int taskScheduling2(List<String> tasks, List<Integer> times, List<List<String>> requirements) {
        // WRITE YOUR BRILLIANT CODE HERE
        HashMap<String,List<String>> map=new HashMap<>();
        for(String s:tasks){
            map.put(s,new ArrayList<String>());
        }
        
        for(List<String> req:requirements){
            map.get(req.get(0)).add(req.get(1));
        }
        HashMap<String,Integer> timeMap=new HashMap<>();
        for(int i=0;i<tasks.size();i++){
            timeMap.put(tasks.get(i),times.get(i));
        }
        
        HashMap<String,Integer> indegree=getIndegree(map);
        
        return minimumTime(tasks,times,map,indegree,timeMap);
        
        
        
        
        //return 0;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = splitWords(scanner.nextLine());
        List<Integer> times = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        int requirementsLength = Integer.parseInt(scanner.nextLine());
        List<List<String>> requirements = new ArrayList<>();
        for (int i = 0; i < requirementsLength; i++) {
            requirements.add(splitWords(scanner.nextLine()));
        }
        scanner.close();
        int res = taskScheduling2(tasks, times, requirements);
        System.out.println(res);
    }
}
