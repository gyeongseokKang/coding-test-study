import java.util.*;

class Solution {
	static HashMap<String, ArrayList<String>> hashmap= new HashMap<>();
	
	public static void  main(String[]args) {
    	String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
    	
    	
    	for(int i=0; i< clothes.length ; i++) {
			ArrayList<String> list = new ArrayList<String>();
			String key = clothes[i][1];
			String value = clothes[i][0];
			
    		if(hashmap.containsKey(key)){
                System.out.println("containsKey");
    			list = hashmap.get(key);
    			list.add(value);    			
    		}else {
    			list.add(value);
    			hashmap.put(key,list);    			
    		}    		
    	}

    	
        int answer = 1;
    	
    	Iterator<String> keys = hashmap.keySet().iterator();
        while( keys.hasNext() ){
            String key = keys.next();
			ArrayList<String> list = hashmap.get(key);
			answer = answer * (list.size()+1);
        }
        
        System.out.println(answer-1);
    }
}
