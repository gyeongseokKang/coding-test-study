import java.util.*;

class Solution {
	// 유클리드 (a를 b로 나눈 나머지가 0보다 클 때 까지 반복)
	static int gcd(int a, int b){ // 최대공약수
		while(b!=0){
			int r = a%b;
			a= b;
			b= r;
		}
		return a;
	}
	
	static int lcm(int a, int b){ // 최소 공배수
		// 0이 아닌 두 수의 곱 / 두 수의 최대공약수
	    return a * b / gcd(a,b);
	}

    public static int solution(int[] arr) {
        int answer = 0;
        int _lcm = 0;
        if(arr.length ==1)
        	return arr[0];
        else
        	_lcm= lcm(arr[0],arr[1]);
        
        for(int i=2;  i<arr.length; i++) {
        	_lcm= lcm(_lcm,arr[i]);
        }
        
        return _lcm;
    }

}

