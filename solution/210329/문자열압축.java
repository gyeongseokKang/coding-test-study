import java.util.*;

class Solution {
	static String s = "aaaaaaaaaabbbbbbbbbb";
	static ArrayList<Integer> ansList =  new ArrayList<Integer>();
	static int maxLen;
	
	public static ArrayList<String> arrBuild (String s, int interval) {
		ArrayList<String> strArr = new ArrayList<String>();
		int start = 0;
		while(true) {
			
			int end = start + interval;
			
			if(end>s.length()) {
				strArr.add(s.substring(start, s.length()));
				break;				
			}
			strArr.add(s.substring(start, end));
			

			if(end == s.length()) {
				break;
			}
			start = end;
		}
		
		return strArr;		
	}
	
	public static void strZip(ArrayList<String> strArr, int interval) {
//		System.out.println("zip:  "+  strArr.size());
		int len =0;
		String str = null;
		int cnt = 0;
		int cnt_size =1;
		for (int i=0; i< strArr.size(); i++) {
			
			if(strArr.get(i).equals(str) && cnt ==0 ){				
				len += 1;
				cnt = 2;
			}else if(strArr.get(i).equals(str) && cnt !=0){
				cnt = cnt +1;
				if((int)(Math.log10(cnt)+1) != cnt_size) {
					len +=1;
					cnt_size +=1;
				}
				
			}else {
				len += strArr.get(i).length();
				str = strArr.get(i);
				cnt = 0;
				cnt_size = 1;
			}
//			System.out.println(len);
		}
		ansList.add(len);
	}
	
	public static void ansList(String s){
		
		for(int i=0; i< maxLen; i++) {
			ArrayList<String> strArr = arrBuild(s ,i+1);
			strZip(strArr,i+1);
		}
	}
	
	
	
    public static int solution(String s) {
    	maxLen = (s.length()+1)/2;
        ansList(s);
        int answer = ansList.isEmpty() ? -1 : Collections.min(ansList);
        return answer;
    }
	
	public static void  main(String[]args) {
		int answer = solution(s);
		System.out.println(answer);
    }
}
