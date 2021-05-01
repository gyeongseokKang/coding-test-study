import java.util.*;
import java.util.Map.Entry;

class Solution {

	static List<String[]> orderList = new ArrayList<>();
	static List<String> ansList = new ArrayList<>();

	public static void comb(String[] order, int n, int r, int start, String str, HashMap<String, Integer> map ) {
		if(r ==0) {
			if(map.containsKey(str)) {
				map.put(str, map.get(str)+1);
			}
			else {
				map.put(str, 1);
			}
			return;
		}
		
		for(int i = start; i<n; i++) { 
			comb(order,n, r-1, i+1, str+order[i], map);
		}
		
		
	}
	
	
	public static void makeAns(int course) {

		HashMap<String, Integer> map = new HashMap<String, Integer>();	
		
		Iterator<String[]> iter = orderList.iterator();
		while ( iter.hasNext() ) {
			String[] order = iter.next();
			if(order.length >= course){
				comb(order, order.length, course,0, "",map);
			}
		}
		
		if(!map.isEmpty()) {
			int maxValueInMap=(Collections.max(map.values())); 
				if(maxValueInMap>=2) {
				for (Entry<String, Integer> entry : map.entrySet()) {  
				    if (entry.getValue()==maxValueInMap) {
					ansList.add(entry.getKey());    
				    }
				}
			}
		}
	}
	
	public static void orderArr(String[] orders){
		for(String order: orders) {
			String[] orderArr = order.split("");
			Arrays.sort(orderArr);
			orderList.add(orderArr);
		}
		
	}
	
	
	public static String[] solution(String[] orders, int[] course) {
		orderArr(orders);
		for(int cour: course) {
		    makeAns(cour);
		}

		Collections.sort(ansList);

		String [] answer = ansList.toArray(new String[ansList.size()]);

		return answer;
	}
    
}    
