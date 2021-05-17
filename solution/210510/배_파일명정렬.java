import java.util.*;

class Solution {
	
	
    public static String[] solution(String[] files) { 
    	List<String> list = new ArrayList<String>();
    	
        Arrays.asList(files).stream().sorted((a,b) -> {
        	
        	String aHead = "";
        	String bHead = "";
        	String aNum = "";
        	String bNum = "";
        	int aBool=0;
        	int bBool=0;
        	for(int i=0;  i< a.length(); i++) {
    			char tmp = a.charAt(i);
    			if(!('0'<= tmp && tmp<= '9') && aBool ==0) {
    				aHead = aHead+tmp;
    			}
    			else if(('0'<= tmp && tmp<= '9'&& aNum.length()<5)) {
				
					aNum =  aNum+tmp;
					aBool =1;
				
    			}
    			else
    				break;
        	}
        	
        	for(int i=0;  i< b.length(); i++) {
    			char tmp = b.charAt(i);
    			if(!('0'<= tmp && tmp<= '9') && bBool ==0) {
    				bHead = bHead+tmp;
    			}
    			else if(('0'<= tmp && tmp<= '9')&& bNum.length()<5) {
				
					bNum =  bNum+tmp;
					bBool =1;
    			}
    			else
    				break;
        	}
        	
        	
            if (aHead.toLowerCase().compareTo(bHead.toLowerCase())>0)
                return 1;
            else if (aHead.toLowerCase().compareTo(bHead.toLowerCase())<0)
                return -1;
            else{
            	return Integer.parseInt(aNum) - Integer.parseInt(bNum);
            		
            }
        }).forEach(file->list.add(file));;

     
        return list.toArray(new String[0]);
    }

	public static void  main(String[] args) {
		String[] files = {"img2.pn23g", "img00005.p4ng", "i2mg02.png66", "img0001.pn23g", "IMG01.G23IF", "img2.JPG"};
		
		String [] answer = solution(files);
		for(String ans: answer) {
			System.out.print(ans+", ");			
		}
    }
}

