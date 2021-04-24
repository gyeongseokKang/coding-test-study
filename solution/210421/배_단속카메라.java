import java.util.*;

class Solution {
    
	public static int[][] sort(int[][] routes){
		
		Arrays.sort(routes, new Comparator<int[]>() {
		    @Override
			public int compare(int[] o1, int[] o2) {
		    	 if(o1[0] == o2[0]) {
	                 return o1[1] - o2[1];
		    	 }else {
		    		 return o1[0] - o2[0]; 
		    	 }
	           }
	        });

		return routes;
	} 
	
	
	public static int solution(int[][] routes) {
        int answer = routes.length;
        
        routes = sort(routes);
        
        int start = routes[0][0];
        int end = routes[0][1];
        for(int i=1; i< routes.length; i++) {
        	
        	if(start<= routes[i][0] &&  end>= routes[i][0] ) {
        		answer -=1;
        		
        		start = routes[i][0];
            	
        		if(end>= routes[i][1]) {
        			end = routes[i][1];
        		}
        	}else {
        		start = routes[i][0];
        		end = routes[i][1];
        	}
        }
       
        return answer;
    }

}
