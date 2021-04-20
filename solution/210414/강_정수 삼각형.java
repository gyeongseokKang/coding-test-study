class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int answerList = new Array[];
        // triangle를 하나씩 내려오면서 (1)
        // 해당 층의 array의 값과 바로위와 -1 위치에 있는 값중 큰 값과 더한걸 정답 리스트에 넣음 (2)
        // answerList에서 가장 큰 값을 answer로 return (3)
        return answer;
    }
}


class Solution2 {
    public int solution(int[][] triangle) {
        int[][] copyTriangle = new int[triangle.length][triangle.length];

        copyTriangle[0][0] = triangle[0][0];
        for (int i = 1; i < triangle.length; i++) {
            copyTriangle[i][0] = copyTriangle[i - 1][0] + triangle[i][0];
            copyTriangle[i][i] = copyTriangle[i - 1][i - 1] + triangle[i][i];
        }

        // 1,2번 단계
        for (int i = 2; i < triangle.length; i++) {
            for (int j = 1; j < i; j++) {
                copyTriangle[i][j] = Math.max(copyTriangle[i - 1][j - 1], copyTriangle[i - 1][j])
                    + triangle[i][j];
            }
        }

        // 3번 단계
        int maxNo = copyTriangle[copyTriangle.length - 1][0];
        for (int i = 1; i < triangle.length; i++) {
            int checkNo = copyTriangle[copyTriangle.length - 1][i];
            if (maxNo < checkNo) {
                maxNo = checkNo;
            }
        }
        return maxNo;
    }
}