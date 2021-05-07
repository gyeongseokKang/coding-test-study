function solution(n, s, a, b, fares) {
  //지점 크기만큼 2차원 배열 및 자기자신 0으로 변경
  let pathMap = Array.from(Array(n), () => new Array(n).fill(Infinity));
  pathMap = pathMap.map((item, index) => {
    let temp = [...item];
    temp[index] = 0;
    return temp;
  });
  /*
  [
    [ 0, Infinity, Infinity, Infinity, Infinity, Infinity ],
    [ Infinity, 0, Infinity, Infinity, Infinity, Infinity ],
    [ Infinity, Infinity, 0, Infinity, Infinity, Infinity ],
    [ Infinity, Infinity, Infinity, 0, Infinity, Infinity ],
    [ Infinity, Infinity, Infinity, Infinity, 0, Infinity ],
    [ Infinity, Infinity, Infinity, Infinity, Infinity, 0 ]
  ]
  */

  // 요금 채우기
  fares.forEach(([from, to, fee]) => {
    pathMap[from - 1][to - 1] = fee;
    pathMap[to - 1][from - 1] = fee;
  });

  /*
  [
    [ 0, Infinity, 41, 10, 24, 25 ],
    [ Infinity, 0, 22, 66, Infinity, Infinity ],
    [ 41, 22, 0, Infinity, 24, Infinity ],
    [ 10, 66, Infinity, 0, Infinity, 50 ],
    [ 24, Infinity, 24, Infinity, 0, 2 ],
    [ 25, Infinity, Infinity, 50, 2, 0 ]
  ]
  */

  //플루이드 와셜 알고리즘, 모든 경로 최소값 찾기
  for (let k = 0; k < n; k++) {
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        pathMap[i][j] = Math.min(pathMap[i][j], pathMap[i][k] + pathMap[k][j]);
      }
    }
  }

  /*
  [
    [ 0, 63, 41, 10, 24, 25 ],
    [ 63, 0, 22, 66, 46, 48 ],
    [ 41, 22, 0, 51, 24, 26 ],
    [ 10, 66, 51, 0, 34, 35 ],
    [ 24, 46, 24, 34, 0, 2 ],
    [ 25, 48, 26, 35, 2, 0 ]
  ]
  */
  //합승 최소 요금 찾기
  let answer = Infinity;
  for (let i = 0; i < n; i++) {
    answer = Math.min(answer, pathMap[s - 1][i] + pathMap[a - 1][i] + pathMap[b - 1][i]);
    console.log(pathMap[s - 1][i] + pathMap[a - 1][i] + pathMap[b - 1][i]);
  }

  return answer;
}

console.log(
  solution(6, 4, 6, 2, [
    [4, 1, 10],
    [3, 5, 24],
    [5, 6, 2],
    [3, 1, 41],
    [5, 1, 24],
    [4, 6, 50],
    [2, 4, 66],
    [2, 3, 22],
    [1, 6, 25],
  ])
);
