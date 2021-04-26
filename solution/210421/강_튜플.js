// 들어온 문자열 파싱
// 파싱된 문자열 길이로 정렬
// 앞에서부터 추가된 값을 정답배열에 추가

function solution(s) {
  var answer = [];
  let spiltedStr = s.slice(2, s.length - 2).split("},{");
  let orderStr = spiltedStr.sort((a, b) =>
    a.split(",").length < b.split(",").length ? -1 : 1
  );
  orderStr.forEach((item) => {
    let unique = item.split(",").filter((item) => !answer.includes(item));
    answer.push(...unique);
  });
  answer = answer.map((item) => Number(item));
  return answer;
}

console.log(solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")); //[ 2, 1, 3, 4 ]
