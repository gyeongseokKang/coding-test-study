import java.util.*;
import java.util.stream.*;

class Solution {
  static ArrayList<int[]> lockIndexing1 = new ArrayList<int[]>();
    static ArrayList<int[]> lockIndexing0 = new ArrayList<int[]>();
     static ArrayList<int[]> keyList = new ArrayList<int[]>();
     
    public static void lockIndexing(int[][] lock, int keyLen){ 
       for(int i= 0; i< lock.length; i++) {
          for(int j=0; j< lock[i].length; j++) {
             int[] index = {j+1+keyLen-1, lock.length-i+keyLen-1};
             if(lock[i][j] == 1)
                lockIndexing1.add(index);
             else
                lockIndexing0.add(index);
             
          }
       }
    }

    public static void indexing(int[][] key){
       for(int i= 0; i< key.length; i++) {
          for(int j=0; j< key[i].length; j++) {
             if(key[i][j] == 1) {
                int[] index = {j+1, key.length-i};
                keyList.add(index);
             }
          }
       }
    }
    
    public static ArrayList<int[]> rotating(ArrayList<int[]> indexing, int len){
       ArrayList<int[]> list = new ArrayList<int[]>();
       for(int[] index : indexing ) {
          int[] rot = {index[1],len+1-index[0]};
          list.add(rot);
       }
       return list;
    }
    
        public static boolean checkAnswer (ArrayList<int[]> key){
    	
    	int cnt = 0;
    	
    	for(int[] k : key) {
    		for(int[] index :lockIndexing1) {
    			if(k[0] == index[0] && k[1] == index[1])
    				return false;
    		}
    		for(int[] index :lockIndexing0) {
    			if(k[0] == index[0] && k[1] == index[1])
    				cnt = cnt +1;
    		}
    	}
    	    	
    	    	if(cnt == lockIndexing0.size())
    		return true;
    	else
    		return false;
    }
    
    public static boolean makeAnswer (ArrayList<int[]> key, int len){

        for(int i =0 ; i<len; i++){
        	int ii=i;
            key.stream().map(x -> {
            	if(ii != 0) {
                	x[0]=x[0]+1;
            		x[1]=x[1]-len+1;
            	}
            	return x;}).collect(Collectors.toList());
            
            for(int j=0; j<len; j++){
            	int jj=j;
                key.stream().map(x -> {
                	if(jj != 0)x[1]=x[1]+1;
                	return x;}).collect(Collectors.toList());
            	          
                if(checkAnswer(key)){
                	
                    return true;
                }
                
            }
        }    
        key.stream().map(x -> {
            	x[0]=x[0]-len+1;
        		x[1]=x[1]-len+1;
        	return x;}).collect(Collectors.toList());
        
        
        return false;
    }
    
    
    public static boolean solution(int[][] key, int[][] lock) {
        lockIndexing(lock, key.length);
        indexing(key);
        
        
        for(int i=0; i<4; i++){
            if (i==0){
            	if(makeAnswer(keyList, key.length+lock.length-1))
            		return true;
            }
            else{
                keyList = rotating(keyList, key.length);
            	if(makeAnswer(keyList, key.length+lock.length-1))
            		return true;
            }
        }
       
        return false;
    }
    
}
