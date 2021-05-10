function solution(arr) {
  for (let i = 1; i < arr.length; i++) {
    arr[i] = ICM(arr[i - 1], arr[i]);
  }
  return arr[arr.length - 1];
}

function GCD(a, b) {
  while (b > 0) {
    [a, b] = [b, a % b];
  }
  return a;
}

function ICM(a, b) {
  return (a * b) / GCD(a, b);
}

//test 케이스만 통과
function solution2(arr) {
  var answer = 1;
  for (let i = 0; i < arr.length; i++) {
    let divide = 1;
    arr = arr.map((item) => {
      if (item % arr[i] === 0) {
        divide = arr[i];
        return item / arr[i];
      }
      return item;
    });
    if (divide > 1) {
      answer *= divide;
    }
  }
  return answer;
}

console.log(solution([2, 6, 8, 14]));
