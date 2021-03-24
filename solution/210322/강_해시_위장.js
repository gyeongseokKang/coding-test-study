// test("current test", () => {
//   expect(solution(clothes)).toEqual(output);
// });

const clothes = [
  ["yellowhat", "headgear"],
  ["bluesunglasses", "eyewear"],
  ["green_turban", "headgear"],
];
const output = 5;
function solution(clothes) {
  //map를 이용해서 구현, key는 종류, value는 이름
  var answer = 1;
  let clothMap = new Map();
  clothes.forEach((item) => {
    if (clothMap.has(item[1])) {
      clothMap.set(item[1], [...clothMap.get(item[1]), item[0]]);
    } else {
      clothMap.set(item[1], [item[0]]);
    }
  });
  for (let key of clothMap.keys()) {
    answer *= clothMap.get(key).length + 1;
  }
  return answer - 1;
}

console.log(solution(clothes));
