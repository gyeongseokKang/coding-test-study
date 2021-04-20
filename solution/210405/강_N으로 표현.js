function solution(N, number) {
    if (N == number) return 1;
    const indiSet = new Array(8).fill().map(() => new Set());

    for (let i = 0; i < 8; i++) {
        indiSet[i].add(Number(N.toString().repeat(i + 1))); // 해당 갯수로 가능한 가장 큰 수
        for (let j = 0; j < i; j++) {
            for (const arg1 of indiSet[j]) {
                for (const arg2 of indiSet[i - j - 1]) {
                    indiSet[i].add(arg1 + arg2);
                    indiSet[i].add(arg1 - arg2);
                    indiSet[i].add(arg1 * arg2);
                    indiSet[i].add(Math.floor(arg1 / arg2)); // 소수점 버림
                }
            }
        }
        if (indiSet[i].has(number)) return i + 1;
    }
    return -1;
}


console.log(solution(5, 12)); // 4
