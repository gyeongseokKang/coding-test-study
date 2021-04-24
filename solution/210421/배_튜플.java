import java.util.*;

class Solution {
	
	
    public static int[] solution(String s) {
        Set<Integer> set = new LinkedHashSet<Integer>();
        
        String str = s.substring(1, s.length()-2);
        
        String[] strArr = str.split("},");
        
        Arrays.sort(strArr, Comparator.comparing(String::length));
        
        for(String ss: strArr) {
        	String sss = ss.substring(1);
        	
        	String[] strArr2 = sss.split(",");
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
