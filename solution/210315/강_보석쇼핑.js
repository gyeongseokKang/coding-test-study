function solution(gems) {
  // 유니크한 보석 갯수
  let gemSet = new Set(gems);
  let count = gemSet.size;

  // 보석 종류 => 보석 자리를 저장하기 위한 맵
  let gemMap = new Map();

  // 보석을 모두 포함하는 구간을 저장할 배열
  let gemLength = [];
  gems.forEach((gem, index) => {
    gemMap.delete(gem);
    gemMap.set(gem, index);
    if (gemMap.size === count) {
      gemLength.push([gemMap.values().next().value + 1, index + 1]);
    }
  });

  gemLength.sort((a, b) => {
    if (a[1] - a[0] === b[1] - b[0]) {
      return a[1] - b[1];
    }
    return a[1] - a[0] - (b[1] - b[0]);
  });

  return gemLength[0];
}

let gems = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"];
let test = 1112111222341123341234;
console.log(solution(gems));
