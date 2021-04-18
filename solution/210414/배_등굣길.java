import java.util.*;

class Solution {
	
    public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] arrAns = new int[m][n];
        arrAns[0][0] = 1;
        
        for(int[] pud : puddles) {
        	arrAns[pud[0]-1][pud[1]-1]=-1;
        }
        
        
        for(int i =0; i< m; i++) {
        	for(int j=0; j<n; j++) {
        		if(i==0 && j==0) {
        			continue;     			
        		}else if(arrAns[i][j] == -1) {
        			arrAns[i][j] = 0;
        		}else if(i==0 && j!=0) {
        			arrAns[i][j] = arrAns[i][j-1];
        		}else if(i!=0 && j==0) {
        			arrAns[i][j] = arrAns[i-1][j];
        		}else {
        			arrAns[i][j] = (arrAns[i][j-1]+arrAns[i-1][j])% 1000000007; 
        		}
        		
        	}
        }
        
        return answer = arrAns[m-1][n-1];        
    }


}
