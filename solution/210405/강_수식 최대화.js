// 연산자 우선순위를 정하고
// 우선순위에 따라 하나씩 값 처리해가며 
// 절대값으로 대소비교
function solution(expression) {
  let answer = 0;
  let operator = expression.replace(/[0-9]/g,""); //계산해야할 연산자들만 가져옴
  let uniqueOperator = Array.from(new Set(operator)); // 우선순위를 계산하기 위해 유니크한 연산자만 가져옴
  let permuList = permutator(uniqueOperator); // 순열조합으로 우선순위별로 가능한 조합 가져옴
  let numberList = expression.split(/[\+\-\*\/]/); // 연산자를 제외한 숫자들만 들고옴

  permuList.forEach((item)=> { // 조합가능한 우선순위별로 하나씩 가져와서
    let tempOperator = operator; // 재사용하기 위해 복사하고
    let tempNumberList = [...numberList];
      for(let i =0; i< item.length ; i++){
        while(tempOperator.includes(item[i])){ //우선순위가 앞에 있는 연산자가 더 있나 확인하고 없으면 해당 연산자 끝
          let currentCalIndex = tempOperator.indexOf(item[i]); //계산해야할 연산자의 인덱스 위치앚고
          let test = tempNumberList[currentCalIndex] + item[i] + tempNumberList[currentCalIndex+1]; //해당 앞뒤로 number을 가져와서
          tempNumberList.splice(currentCalIndex,2,eval(test)); //계산된 숫자들을 없애고 새롭게 계산된 값을 넣고
          tempOperator =tempOperator.replace(item[i],"") // 계산왼료된 연산자 1개는 없앤다
        }
      }
      if(answer < Math.abs(tempNumberList[0])) answer = Math.abs(tempNumberList[0]); // 절대값 비교해서 큰수처리
  })
  return answer;
}

function permutator(inputArr) {
  var results = [];
  function permute(arr, memo) {
    var cur, memo = memo || [];
    for (var i = 0; i < arr.length; i++) {
      cur = arr.splice(i, 1);
      if (arr.length === 0) {
        results.push(memo.concat(cur));
      }
      permute(arr.slice(), memo.concat(cur));
      arr.splice(i, 0, cur[0]);
    }
    return results;
  }

  return permute(inputArr);
}
const expression = "100-200*300-500+20";
console.log(solution(expression)); // 60420
