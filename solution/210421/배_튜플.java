import java.util.*;

class Solution {
    
    public static int[] solution(String s) {
        Set<Integer> set = new LinkedHashSet<Integer>();
        
        String[] strArr  = s.substring(1, s.length()-2).split("},");
        
        Arrays.sort(strArr, Comparator.comparing(String::length));
        
        for(String ss: strArr) {
        	
        	String[] strArr2 = ss.substring(1).split(",");
        	
        	for(int i=0; i<strArr2.length; i++) {
        		set.add(Integer.parseInt(strArr2[i]));
        	}
        }
        
        int[] answer = new int [set.size()];
    	Iterator it=set.iterator();
    	int cnt = 0;
    	while(it.hasNext()){
    		answer[cnt] = (int) it.next();
    		cnt +=1;
    	}
         
        return answer;
    }
}
