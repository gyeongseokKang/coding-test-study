function solution(orders, course) {
  var answer = [];

  course.forEach((courseLength) => {
    let bestMenu = new Map();
    orders.forEach((item) => {
      item = [...item].sort();
      let cousreItem = combination([...item], courseLength);
      for (let i = 0; i < cousreItem.length; i++) {
        let key = cousreItem[i].join("");

        if (bestMenu.has(key)) {
          bestMenu.set(key, bestMenu.get(key) + 1);
        } else {
          bestMenu.set(key, 1);
        }
      }
    });
    let max = 2;
    for (let [key, value] of bestMenu) {
      if (max < value) max = value;
    }

    for (let [key, value] of bestMenu) {
      if (value === max) {
        answer.push(key);
      }
    }
  });

  answer = answer.sort();
  return answer;
}

function combination(arr, selectNum) {
  const result = [];
  if (selectNum === 1) return arr.map((v) => [v]);
  arr.forEach((v, idx, arr) => {
    const fixed = v;
    const restArr = arr.slice(idx + 1);
    const combinationArr = combination(restArr, selectNum - 1);
    const combineFix = combinationArr.map((v) => [fixed, ...v]);
    result.push(...combineFix);
  });
  return result;
}

console.log(solution(["XYZ", "XWY", "WXA"], [2, 3, 4]));
