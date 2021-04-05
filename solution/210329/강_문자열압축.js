function solution(s) {
    let answer = s.length;
    let splitMap = new Map();

    //하나씩 자르는 크기를 증가시켜가며 문자열 분해
    for (let i = 1; i < s.length; i++) {
        let target = s;
        let tempAnswer = "";
        while (target.length > 0) {
            let splitedStr = target.substring(0, i); // 자르게 되는 문자열
            target = target.substring(i, target.length); // 나머지 문자열 할당
            if (splitMap.has(splitedStr)) { //Map에 해당 문자열을 key로 값 확인
                splitMap.set(splitedStr, splitMap.get(splitedStr) + 1) // 있으면 +1
            } else {
                let entry = splitMap.entries().next().value; // 없으면 새로운 문자열이라고 판단하여 임시답변수에 할당
                if (entry) { // 단 처음 들어오는 경우 아무런 문자가 없을수 있음으로 체크
                    if (entry[1] == 1) { // 1이 나올 경우 삭제한다는 조건
                        tempAnswer += entry[0];
                    } else {
                        tempAnswer += entry[1] + entry[0];
                    }
                }
                splitMap.clear(); // 기존 key, value 날리고
                splitMap.set(splitedStr, 1); // 새롭게 들어온 str으로 key로 삼고 set value 1
            }

        }
        let lastEntry = splitMap.entries().next().value; // 마지막 map에 있는 값을 가져옴
        if (lastEntry) {
            if (lastEntry[1] == 1) {
                tempAnswer += lastEntry[0];
            } else {
                tempAnswer += lastEntry[1] + lastEntry[0];
            }
        }
        splitMap.clear(); // 다음 길이의 map를 위해 초기화

        if (tempAnswer !== undefined && answer > tempAnswer.length) {
            answer = tempAnswer.length; // 길이가 작을 경우 정답으로 치환
        }
    }
    return answer;
}

let input = "aabbaccc";

console.log(solution(input))
