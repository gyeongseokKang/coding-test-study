import java.util.*;
import java.util.Map.Entry;

class Solution {
	static HashMap<String, ArrayList<Integer[]>> hashmap= new HashMap<>();
	static HashMap<String, Integer> hashmap2= new HashMap<>();
	
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
	
	public static int[] arrsort(ArrayList<Integer[]> List) {
		
		if(List.size() ==1) {
			int[] ans =  {List.get(0)[1]};
			return ans;
		}
		
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
		List<Integer> ansList = new ArrayList<>();
		
		setHash(genres,plays);
		 List<Entry<String, Integer>> desList = descending();
		
		for(Entry<String, Integer> entry : desList){
			int[] ans= arrsort(hashmap.get(entry.getKey()));
			for(int j : ans) {
				ansList.add(j);
			}			
		}
		
		int [] answer= 	ansList.stream().mapToInt(Integer::intValue).toArray();
		 
    }
}
