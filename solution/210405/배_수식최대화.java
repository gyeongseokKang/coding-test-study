import java.util.*;

import java.util.regex.*;

 

class Solution {

	static String expression = "100-200*300-500+20";

	static Stack<Long> numList = new Stack<Long>();

	static ArrayList<String> operList = new ArrayList<String>();

	static ArrayList<Long> ansList = new ArrayList<Long>();

	

	public static void spliting (String expression) {

		Pattern p1 = Pattern.compile("([0-9])+");

		Pattern p2 = Pattern.compile("[^0-9]");

		Matcher m1 = p1.matcher(expression);	

		Matcher m2 = p2.matcher(expression);

		

		while(m1.find()) {

			long p = Long.valueOf(m1.group());

			numList.push(p);

		}

		while(m2.find()) {

			operList.add( m2.group());

		}

	}

	

	public static void makeAnswer (ArrayList<String> oList,  Stack<Long> nList,

			String order, int depth){

		if(depth == 3) {

			if(nList.get(0)<0) {

				ansList.add(-nList.get(0));

			}else {

				ansList.add(nList.get(0));

			}

			

		}

		else {

			ArrayList<String> oTempList = new ArrayList<String>();

			Stack<Long> nTempList = new Stack<Long>();

			String oper = order.substring(depth, depth+1);

			nTempList.push(nList.get(0));

			for(int i=0; i< oList.size(); i++) {

				if(oper.equals(oList.get(i))) {

					if(oper.contentEquals("+")) {

						long p = nTempList.peek()+nList.get(i+1);

						nTempList.pop();

						nTempList.push(p);

					}else if(oper.contentEquals("-")) {

						long p = nTempList.peek()-nList.get(i+1);

						nTempList.pop();

						nTempList.push(p);

					}else if(oper.contentEquals("*")) {

						long p = nTempList.peek()*nList.get(i+1);
                        
						nTempList.pop();

						nTempList.push(p);

					}

				}else {

					oTempList.add(oList.get(i));

					nTempList.add(nList.get(i+1));

				}

			}

 

//			System.out.println("depth:  "+depth);

//			for(String str : oTempList) {

//				System.out.print(str+",");

//			}

//			System.out.println();

//			for(Integer i : nTempList) {

//				System.out.print(i+",");

//			}

//			System.out.println();

//			System.out.println();

			

			makeAnswer(oTempList, nTempList, order,depth+1);

		}

		

	}

	

	

    public static long solution(String expression) {

    	

    	long answer = 0;

    	spliting(expression);

    	

    	makeAnswer(operList,numList,"+-*",0);

    	makeAnswer(operList,numList,"+*-",0);

    	makeAnswer(operList,numList,"-+*",0);

    	makeAnswer(operList,numList,"-*+",0);

    	makeAnswer(operList,numList,"*+-",0);

    	makeAnswer(operList,numList,"*-+",0);

    	

    	answer = Collections.max(ansList);

    	

        return answer;

    }

	

	// public static void  main(String[]args) {

	// 	long answer = solution(expression);

	// 	System.out.println(answer);

	// }

}
