
import java.util.*;

class Solution {
	//gem 종류를 파악하기 위한 hashSet
	static HashSet<String> gemSet = new HashSet<>();

	//gem 종류 별로 탐색된 개수를 파악하기 위해 hashMap 사용 
	static HashMap<String, Integer> gemMap = new HashMap<>();
	
	//선입선출을 위해 que 사용
    static Queue <String> que = new LinkedList<>();

    static int[] answer = new int[2];
    
  //hashSet을 이용하여 gem의 종류 파악
	public static void gem_list (String[] gems) {
		for(String data : gems){
			gemSet.add(data);
		}
		return ;
	}
	
	public int[] makeAnswer (String[] gems) {
		int diff=Integer.MAX_VALUE;
		int start=0;
		//gem을 처음부터 탐색
		for(int i=0; i<gems.length; i++) {		
            
			//hashmap에 해당 gem이 포함되어 있으면 개수만 한개 증가
			if(gemMap.containsKey(gems[i])){
                gemMap.put(gems[i], gemMap.get(gems[i])+1);
            }
			//hashmap에 해당 gem이 없으면 (gem, 1) add
            else{
                gemMap.put(gems[i], 1);
            }
            
			//q에도 추가 
            que.add(gems[i]);
            
            //que의 첫벗째 gem과 동일한 gem이 있으면 poll하는 로직 
            //첫번째 gem과 동일한 gem이 없어질 때까지 poll 반복
            while(true){
                String que0 = que.peek();
                if(gemMap.get(que0)>1){
                    start =start+ 1;
                    gemMap.put(que0, gemMap.get(que0)-1);
                    que.poll();
                }
                else{break;}
            }
            -
            //모든 종류의 gem이 있고 길이가 diff보다 작으면 answer 업데이트
            if(gemSet.size() == gemMap.size() && que.size() < diff){
                diff = que.size();
                answer[0] = start+1;
		        answer[1] = start+diff;
            }
            
		}
		
		return answer;
	}
		
    public int[] solution(String[] gems) {
    	gem_list(gems);
        return makeAnswer(gems);
    }
}


