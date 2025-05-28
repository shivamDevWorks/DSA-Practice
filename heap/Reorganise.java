class Pair{
    char c;
    int freq;
    public Pair(char c,int freq){
        this.c=c;
        this.freq=freq;
    }  
}

class Reorganise {
    public String reorganizeString(String s) {
        HashMap<Character,Integer> charCount=new HashMap<>();
        for(char c:s.toCharArray()){
            if(charCount.containsKey(c)){
                charCount.put(c,charCount.get(c)+1);
            }
            else{
                charCount.put(c,1);
            }
        }
        
        PriorityQueue<Pair> pq=new PriorityQueue<>((p1,p2)->p2.freq-p1.freq);
        for(Map.Entry<Character,Integer> entry:charCount.entrySet()){
            pq.add(new Pair(entry.getKey(),entry.getValue()));
        }
        if(pq.peek().freq>(s.length()+1)/2){
            return "";
        }
        
        char ans[]=new char[s.length()];
        int index=0;
        while(!pq.isEmpty()){
            Pair p=pq.poll();
            int f=p.freq;
            char c = p.c;
            
            while(f-->0){
                ans[index]=c;
                index+=2;
                if(index>=s.length()){
                    index=1;
                }
            }  
        }
        return new String(ans);
    }
}