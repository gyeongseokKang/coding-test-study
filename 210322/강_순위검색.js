let info = ["java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"];
let query = ["java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"];
let test = ["- and - and - and chicken 100", "- and - and - and - 150"];

console.log(solution(info, query));
function solution(info, query) {
  let answer = [];
  let userMap = new Map();

  //user 정보를 첫째 문자만 뽑아다가 Map으로 만드는 함수
  // key -> 조건4가지 , value -> 해당 조건에 맞는 사람들의 점수 배열
  for (let i = 0; i < info.length; i++) {
    let user = info[i].split(" ");
    let userkey = `${user[0][0]}${user[1][0]}${user[2][0].toUpperCase()}${user[3][0].toUpperCase()}`;
    if (userMap.has(userkey)) {
      userMap.set(userkey, [...userMap.get(userkey), Number(user[4])]);
    } else {
      userMap.set(userkey, [Number(user[4])]);
    }
  }
  // 효율성을 위해 오름차순 정렬
  for (let [key, value] of userMap) {
    userMap.set(
      key,
      value.sort((a, b) => a - b)
    );
  }

  // query를 수행하면서 query조건 key로 만든후 조회 시작
  // key에 value를 가져오고 효율성 통과를 위해 정렬된 배열을 대상으로 특정 값 조회
  for (let i = 0; i < query.length; i++) {
    let count = 0;
    let condition = query[i].split(" ");
    let conditionKey = `${condition[0][0]}${condition[2][0]}${condition[4][0].toUpperCase()}${condition[6][0].toUpperCase()}`;
    for (let [key, value] of userMap) {
      conditionKey = conditionKey.replace(/\-/g, "");
      let contain = true;
      for (let i = 0; i < conditionKey.length; i++) {
        if (!key.includes(conditionKey[i])) {
          contain = false;
          break;
        }
      }
      if (contain) {
        const index = binarySearch(value, Number(condition[7]));
        count += value.length - index;
      }
    }
    answer.push(count);
  }
  return answer;
}
function binarySearch(arr, target) {
  let left = 0;
  let right = arr.length;
  while (left < right) {
    let mid = Math.floor((left + right) / 2);
    if (arr[mid] < target) left = mid + 1;
    else right = mid;
  }
  return left;
}

function solution_2(info, query) {
  var answer = [];
  let user = [];

  for (let i = 0; i < info.length; i++) {
    user.push(info[i].split(" "));
  }
  for (let i = 0; i < query.length; i++) {
    let match = 0;
    let condition = query[i].split(" ");
    for (let j = 0; j < user.length; j++) {
      let targetUser = user[j];
      if (
        (condition[0] == "-" || targetUser[0][0] == condition[0][0]) &&
        (condition[2] == "-" || targetUser[1][0] == condition[2][0]) &&
        (condition[4] == "-" || targetUser[2][0] == condition[4][0]) &&
        (condition[6] == "-" || targetUser[3][0] == condition[6][0]) &&
        (condition[7] == "-" || Number(targetUser[4]) >= Number(condition[7]))
      ) {
        match++;
      }
    }
    answer.push(match);
  }
  return answer;
}

function solution_1(info, query) {
  var answer = [];
  let condition = [];
  let user = [];
  for (let i = 0; i < query.length; i++) {
    condition.push(query[i].split(" "));
  }
  for (let i = 0; i < info.length; i++) {
    user.push(info[i].split(" "));
  }
  for (let i = 0; i < condition.length; i++) {
    let match = 0;
    for (let j = 0; j < user.length; j++) {
      if (user[j][0] === condition[i][0] || condition[i][0] === "-") {
        if (user[j][1] === condition[i][2] || condition[i][2] === "-") {
          if (user[j][2] === condition[i][4] || condition[i][4] === "-") {
            if (user[j][3] === condition[i][6] || condition[i][6] === "-") {
              if (Number(user[j][4]) >= Number(condition[i][7]) || condition[i][7] === "-") {
                match++;
              }
            }
          }
        }
      }
    }
    answer.push(match);
  }
  return answer;
}
