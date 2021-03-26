class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        int[] info_score = new int[info.length];
        String[][] info_array = new String[info.length][4];
        
        //info를 스페이스바 기준으로 짤라서 점수는 info_score에, 단어들은 info_array에 저장
        for(int i=0; i<info.length; i++){
            String[] arStr = info[i].split("\\s");
            info_score[i] = Integer.parseInt(arStr[4]);
            info_array[i][0] = arStr[0];
            info_array[i][1] = arStr[1];
            info_array[i][2] = arStr[2];
            info_array[i][3] = arStr[3];
        }
        

        //query를  " and " 기준으로 짤라서 점수는 info_score와 비교하고 단어는 "info_array"과 비교하여 조건에 맞으면
        //cnt 증가시켜 최종 결과 answer[i]에 저장. 이과정을 쿼리 개수 만큼 반복하면 정답 나옴
        for(int i=0; i<query.length; i++){
            String Sint = query[i].replaceAll("[^0-9]", "");
            int qint = Integer.parseInt(Sint);
            String str = query[i].replace(Sint, "");
            
            str = str.trim();
            String[] arStr = str.split("\\s"+"and"+"\\s");
            int cnt = 0;          
            for(int j =0; j<info.length; j++){
                if(qint > info_score[j]){
                }
                else if(!arStr[0].equals("-") && !arStr[0].equals(info_array[j][0])){
                }
                else if(!arStr[1].equals("-") && !arStr[1].equals(info_array[j][1])){
                }
                else if(!arStr[2].equals("-") && !arStr[2].equals(info_array[j][2])){
                }
                else if(!arStr[3].equals("-") && !arStr[3].equals(info_array[j][3])){
                }
                else{
                    cnt++;
                }
            }
            answer[i] = cnt;
        }                   
        return answer;
        
    }
                
}

