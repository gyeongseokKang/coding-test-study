import java.util.*;

class Solution {
		static int start[];
		static int startB[];
		static int startA[];
		
		static HashMap<String,Integer> maps = new HashMap<String,Integer>(); 
		
    static public void makeMaps(  int[][] fares) {
    	
    	for(int[] fare: fares) {
    		
    		String a = fare[0]+"to"+fare[1];
    		String b = fare[1]+"to"+fare[0];

    		maps.put(a, fare[2]);
    		maps.put(b, fare[2]);
    	}
    	
    	 
    } 
    
    public static int[] dijkstra(int n, int s ){

        int newFare[] = new int[n+1];          //최단 거리를 저장할 변수
        boolean[] check = new boolean[n+1];     //해당 노드를 방문했는지 체크할 변수
        
        
	    //newFare값 초기화.
	    for(int i=1;i<n+1;i++){
	    	newFare[i] = 10000001;
	    }

         
        //시작노드값 초기화.
	    newFare[s] =0;
        check[s] =true;
         
        //연결노드 newFare갱신
        for(int i=1;i<n+1;i++){
        	String map = s+"to"+i;
            if(!check[i] && maps.containsKey(map)){
            	newFare[i] = maps.get(map);
            }

        }
         
         
        for(int a=0;a<n-1;a++){
            //원래는 모든 노드가 true될때까지 인데
            //노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
            //원하지 않으면 각각의 노드가 모두 true인지 확인하는 식으로 구현해도 된다.
            int min=10000001;
            int min_index=-1;
             
            //최소값 찾기
            for(int i=1;i<n+1;i++){
                if(!check[i] ){
                    if(newFare[i]<=min ){
                        min=newFare[i];
                        min_index = i;
                    }
                }
            }
            

            check[min_index] = true;
            
            
            for(int i=1;i<n+1;i++){
            	if(min== 10000001)
            		break;

            	String map = min_index+"to"+i; 
                if(!check[i] && maps.containsKey(map)){
                	int m=maps.get(map);
                    if(newFare[i]>newFare[min_index]+m){
                        newFare[i] = newFare[min_index]+m;
                    }
                }
            }
        }
        return newFare;
    }
 
    public static int solution(int n, int s, int a, int b, int[][] fares) {
    	makeMaps( fares);
    	start = dijkstra(n,s);
    	startA = dijkstra(n,a);
    	startB = dijkstra(n,b);
        List<Integer> ansList = new ArrayList<Integer>();

         
        for(int i=1; i<n+1; i++) {
           	ansList.add(start[i]+startA[i]+startB[i]);
        }
        

        return Collections.min(ansList);
    }
    
}
