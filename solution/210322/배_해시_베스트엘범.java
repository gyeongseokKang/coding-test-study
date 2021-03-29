import java.util.*;
import java.util.Map.Entry;

class Solution {
	
	//장르, [플레이 횟수, id] 해쉬맵
	static HashMap<String, ArrayList<Integer[]>> hashmap= new HashMap<>();
	//장르, 장르별 총 플레이 횟수 해쉬맵
	static HashMap<String, Integer> hashmap2= new HashMap<>();
	
	//해쉬맵 구성
	public static void setHash (String[] genres, int[] plays) {
		for(int i=0; i<genres.length; i++ ) {
			ArrayList<Integer[]> list = new ArrayList<Integer[]>();			
			if(hashmap.containsKey(genres[i])) {
				list = hashmap.get(genres[i]);
				Integer sum = hashmap2.get(genres[i]);
				Integer[] value = {plays[i],i};				
				sum = sum +plays[i];
				list.add(value);		
				hashmap2.put(genres[i], sum);					
			}else {
				Integer[] value = {plays[i],i};
				int sum = plays[i];
				list.add(value);
				hashmap.put(genres[i], list);
				hashmap2.put(genres[i], plays[i]);				
			}
		}
	}
	
	//장르별 총 플레이 횟수 기반으로 내림차순 정렬하여 리턴
	public static List<Entry<String, Integer>> descending () {
		// Map.Entry 리스트 작성
		List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(hashmap2.entrySet());
		
		// 비교함수 Comparator를 사용하여 내림 차순으로 정렬
		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>() {
			// compare로 값을 비교
			public int compare(Entry<String, Integer> obj1, Entry<String, Integer> obj2)
			{
				// 내림 차순으로 정렬
				return obj2.getValue().compareTo(obj1.getValue());
			}
		});
		return list_entries;
	}
	
	//장르별 플레이 횟수 2위 안에 드는 순서 리턴
	public static int[] arrsort(ArrayList<Integer[]> List) {
		
		// 장르에 곡이 하나일 경우 바로 리턴  
		if(List.size() ==1) {
			int[] ans =  {List.get(0)[1]};
			return ans;
		}
		
		//하나 이상일 경우 2차원 어레이 내림차순 정렬 실행하여 리턴
		Integer [][] ans= List.toArray(new Integer [List.size()][2]);		
		Arrays.sort(ans, new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				if(o1[0] == o2[0]) {
					return o2[1] - o1[1];
				}else {
					return o2[0] - o1[0];
				}
			} 
		});
		
		int[] result = {ans[0][1],ans[1][1]};
		return result;
	}
	
	public static void  main(String[]args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop","classic2", "classic2", "pop2"};
		int[] plays = {500, 600, 150, 800, 2500, 150, 800, 2500};
		
		//정답 갯수를 모르니 List로 생성
		List<Integer> ansList = new ArrayList<>();
		
		//해쉬맵 세팅
		setHash(genres,plays);
		
		
		//장르별 총 플레이 횟수 기반으로 내림차순 정렬하여 리턴
		List<Entry<String, Integer>> desList = descending();
		
		//ansList에 id 추가 
		for(Entry<String, Integer> entry : desList){
			int[] ans= arrsort(hashmap.get(entry.getKey()));
			for(int j : ans) {
				ansList.add(j);
			}			
		}
		
		//list array로 변환 
		int [] answer= 	ansList.stream().mapToInt(Integer::intValue).toArray();
		 
    }
}
