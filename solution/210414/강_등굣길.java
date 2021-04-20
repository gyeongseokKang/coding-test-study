class Solution {
  public int solution(int m, int n, int[][] puddles) {
      int answer = 0;
      int[][] dpArray = new int[100][100];
      
      for(int i =0; i < 100; i++){
          for(int j =0; j < 100;j++){
              dpArray[i][j] = 0; // 모두 -1로 초기화
          }
      }
      
      for(int i =0; i < 100; i++){
          dpArray[i][0] = 1; // 12시 방향으로 가는 최소거리
          dpArray[0][i] = 1; // 9시 방향으로 가는 최소거리
      }
      
      
      for(int i =0; i < puddles.length();i++){
          for(int j=0; j < puddles[0].length();j++){
              //이떄 들어온 값에서 -1해서 값계산해야함 (좌표 통일)
               // 들어온 2차원 배열을 읽어서 해당하는 위치에 0 넣기
          }
      }
      
      for(int i =0; i < 100; i++){
          for(int j =0; j < 100;j++){
              //12시 9시 둘다 올수없음
              if(dpArray[i][j-1] === -1 && dpArray[i-1][j] === -1)  dpArray[i][j] = 0; continue;
              //12시 9시에 하나만 가능, 갈수있는 곳으로 이동
              if(dpArray[i][j-1] === -1)  dpArray[i][j] = dpArray[i-1][j];
              if(dpArray[i-1][j] === -1)  dpArray[i][j] = dpArray[i][j-1];
              //12시, 9시 둘다 가능. 비교해서 이동
              dpArray[i][j] = dpArray[i-1][j] + dpArray[i][j-1]; 

          }
      }
      
      
      return dpArray[m-1][n-1];
  }
}

public class Solution2 {
  public int solution(int m, int n, int[][] puddles) {
    int[][] street = new int[n][m];

    // 웅덩이는 -1
    for (int[] puddle : puddles)
      street[puddle[1] - 1][puddle[0] - 1] = -1;

    street[0][0] = 1;

    for (int i = 0; i < n; i++) { // 시작점은 1로 저장
      for (int j = 0; j < m; j++) {

        if(street[i][j] == -1) { // 웅덩이면 0으로 바꾸고 continue
          street[i][j] = 0;
          continue;
        }

        if(i != 0)
          street[i][j] += street[i - 1][j] % 1000000007;

        if(j != 0)
          street[i][j] += street[i][j - 1] % 1000000007; 
      }
    }

    return street[n - 1][m - 1] % 1000000007;
  }
}