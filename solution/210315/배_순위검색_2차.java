
import java.util.*;

class Solution {
	static Map<String, ArrayList<Integer>> info_map = new HashMap<String, ArrayList<Integer>>(); 
	static int[] answer;

	//dfs 알고리즘을 이용하여 info에 대한 조회가능 모든 query와 기준 score를 HashMap에 저장
	public static void dfs(String str, int depth, String[] info_arr) {
		if(depth ==4) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int e =  Integer.parseInt(info_arr[4]);
			if(info_map.containsKey(str)) {
				list= info_map.get(str);
				list.add(e);
			}
			else {
				list.add(e);
				info_map.put(str,list);
			}
		}
		else {
			dfs(str+info_arr[depth],depth+1,info_arr);
			dfs(str+"-",depth+1,info_arr);			
		}		
	}
	
	public static void SetInfo(String[] info) {
		//info를 array로 변환하고 info에 대한 hashmap을 구성
		for(int i=0; i< info.length; i++){
			String[] info_arr = info[i].split("\\s");
			dfs("",0,info_arr);
		}

		//그런다음 각 key별로 score 정렬
		Iterator<String> keys = info_map.keySet().iterator();
        while(keys.hasNext() ){
            String key = keys.next();
            ArrayList<Integer> list = new ArrayList<Integer>();
            list= info_map.get(key);
            Collections.sort(list);
        }
	}
	

public static int[] MakeAnswer( String[] query){
		//query 스페이스바 기준으로 나눠서 score 추출
		for(int i =0; i< query.length; i++) {
			String[] que = query[i].split("\\s");
			int score = Integer.parseInt(que[que.length-1]);
			String str = "";
			
			//and는 제거하며 string만 "str"에 합침 
			for(int j =0 ; j<que.length-1; j++ ) {
				if(que[j].equals("and")) {
					continue;
				}
				else{
					str += que[j];
				}
			}			
			
			//info의 hashmap이 str을 key로 갔고 있으면 쿼리의 기준 score 이상인 값의 개수 추출
			if(info_map.containsKey(str)) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list= info_map.get(str);
				int start =0;
				int end = list.size()-1;
				
				//속도를 위해 이진 탐색 사용
				while(start<=end){
                    int mid=(start+end)/2;
                    if(list.get(mid)<score){
                        start=mid+1;
                    }else{
                       end=mid-1;
                    }
                }

				answer[i] = list.size()-start;
			}
			else {
				answer[i] = 0;
			}
			
		}
		
		return answer;
	}
	
	
    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];
        SetInfo(info);
        MakeAnswer(query);
        return answer;
        
    }
}

