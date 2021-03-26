import java.util.*;

class Solution {
	
	static HashSet<String> gemSet = new HashSet<>();

    int[] answer = new int[2];
    
    //hashSet을 이용하여 gem의 종류 파악
	public static void gem_list (String[] gems) {
		for(String data : gems){
			gemSet.add(data);
		}
		return ;
	}
	
	public int[] makeAnswer (String[] gems) {
		int diff=gems.length;
		int start=0;
		//gem을 처음부터 탐색
		for(int i=0; i<gems.length; i++) {
			//속도를 위해 hashSet 사용 (list와 같은 것보다 빠름)
			HashSet<String> temp = new HashSet<>();

			//temp에 길이가 gem의 hashSet의 길이와 동일 할 때 까지 반복
			//이렇게 하면 모든 종류에 gem이 생길 때까지 반복됨
			//그 이후에는 반복횟 수를 diff에 저장하고 diff 보다 적을 때까지 다음 i부터 반복
			for(int j=0; j<diff; j++) {
				temp.add(gems[i+j]);
				if(temp.size()== gemSet.size()) {
                    diff = j;
                    start = i;
                    break;
				}
			}
			//뒤에 남은게 diff보다 적으면 break;
			if( gems.length-i<=diff)break;
		}
		answer[0] = start+1;
		answer[1] = start+1+diff;
		return answer;
	}
		
    public int[] solution(String[] gems) {
    	gem_list(gems);
    	makeAnswer(gems);
        return answer;
    }
}



