
import java.util.*;

class Solution {
	static int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
	
	
	static int depth =0;

	
	
    public static int solution(int[][] triangle) {
    	depth = triangle.length;
        
		for(int i =depth-2; i >= 0 ; i--) {
			for(int j = 0; j< triangle[i].length ; j++) {
				triangle[i][j] = triangle[i+1][j]>triangle[i+1][j+1] ? triangle[i+1][j]+triangle[i][j] : triangle[i+1][j+1]+triangle[i][j];   
			}
		}

        return triangle[0][0];
    }
	
	public static void  main(String[] args) {
		int answer = solution(triangle);
		System.out.println(answer);
    }
}
