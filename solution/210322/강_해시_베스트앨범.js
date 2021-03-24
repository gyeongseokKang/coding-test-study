// test("current test", () => {
//   expect(solution(clothes)).toEqual(output);
// });

const genres = ["classic", "pop", "classic", "classic", "pop"];
const plays = [500, 600, 150, 800, 2500];
const output = [4, 1, 3, 0];
function solution(genres, plays) {
  var answer = [];
  //genres의 들어온 순서가 map key, plays는 value에 더할 값
  //value순으로 정렬하고 해당하는 값의 key를 모아 리턴
  let genreCountMap = new Map();
  let genreMap = new Map();
  genres.forEach((item, index) => {
    if (genreCountMap.has(item)) {
      genreCountMap.set(item, genreCountMap.get(item) + plays[index]);
      genreMap.set(item, [...genreMap.get(item), { index: index, play: plays[index] }]);
    } else {
      genreCountMap.set(item, plays[index]);
      genreMap.set(item, [{ index: index, play: plays[index] }]);
    }
  });

  let orderGenre = [];
  for (let [key, value] of genreCountMap) {
    orderGenre.push({ key: key, value: value });
  }
  orderGenre.sort((a, b) => {
    return a.value > b.value ? -1 : 1;
  });

  orderGenre.forEach((item) => {
    let targetIndex = genreMap
      .get(item.key)
      .sort((a, b) => {
        return a.play > b.play ? -1 : 1;
      })
      .map((item) => {
        return item.index;
      })
      .slice(0, 2);
    answer.push(...targetIndex);
  });
  return answer;
}

console.log(solution(genres, plays));
