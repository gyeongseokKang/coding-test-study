// 일단 경로를 출발지 기준 오름차순으로 정렬( 앞에서부터 비교해가며 범위를 줄여가기 위함)
// 카메라 위치 범위를 담은 배열 camaraArray를 만들고
// 경로를 하나씩 받으며 겹치는 부분이 있으면 겹치는 부분으로 해당 배열 item 업데이트
// 겹치는 부분이 없으면 새로운 카메라로 간주하여 camaraArray에 추가
// 경로가 끝날때까지 반복후에 camaraArray의 갯수가 최소 카메라 갯수

function solution(routes) {
  routes = routes.sort((a, b) => (a[0] > b[0] ? 1 : -1));
  let camaraArray = [];
  routes.forEach((item) => {
    let inFlag = false;
    for (let i = 0; i < camaraArray.length; i++) {
      if (!inFlag) {
        if (camaraArray[i][0] > item[1] || camaraArray[i][1] < item[0])
          continue;
        camaraArray[i][0] = Math.max(camaraArray[i][0], item[0]);
        camaraArray[i][1] = Math.min(camaraArray[i][1], item[1]);
        inFlag = true;
      }
    }
    if (!inFlag) {
      camaraArray.push(item);
    }
  });
  var answer = camaraArray.length;
  return answer;
}

console.log(
  solution([
    [-20, 15],
    [-14, -5],
    [-18, -13],
    [-5, -3],
  ])
); //2
