import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

class Element{
    int val,r,c;
    public Element(int val,int r,int c){
        this.val=val;
        this.r=r;
        this.c=c;
    }
}
class KthLargestMatrix {
    public static int kthSmallest(List<List<Integer>> matrix, int k) {
        // WRITE YOUR BRILLIANT CODE HERE
        PriorityQueue<Element> pq=new PriorityQueue<>((a,b)->a.val-b.val);
        for(int i=0;i<matrix.size();i++){
            pq.add(new Element(matrix.get(i).get(0),i,0));
        }
        int index=1;
        while(!pq.isEmpty()){
            Element e=pq.poll();
            if(k==index){
                return e.val;
            }
            
            int row=e.r;
            int col=e.c;
            
            if(col+1<matrix.get(0).size()){
                pq.add(new Element(matrix.get(row).get(col+1),row,col+1));
            }
            index++;
            
        }
        return 0;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < matrixLength; i++) {
            matrix.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }
        int k = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = kthSmallest(matrix, k);
        System.out.println(res);
    }
}
