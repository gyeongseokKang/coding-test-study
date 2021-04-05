const key = [[0, 0, 0], [0, 0, 1], [0, 1, 1]]
const lock = [[1, 1, 1], [1, 1, 0], [1, 0, 1]];

const solution = (key, lock) => {
    const lockLength = lock.length;
    const arr = Array.from(Array(lockLength * 3), () => Array(lockLength * 3).fill(null)); //3배크기 lock 복사
    const keyLength = key.length;
    for (let i = lockLength; i < lockLength * 2; i++) {
        for (let j = lockLength; j < lockLength * 2; j++) {
            arr[i][j] = lock[i - lockLength][j - lockLength]; // 정중앙에 기본 lock 박고 시작
        }
    }
    for (let i = 0; i < 4; i++) {
        key = rotation90Array(key, i); // 회전하면서
        for (let j = 0; j <= arr.length - keyLength; j++) {
            for (let k = 0; k <= arr.length - keyLength; k++) {
                const newLock = arr.map(function (arr) { // 2차원 배열 깊은 복사
                    return arr.slice();
                });
                for (let m = 0; m < keyLength; m++) {
                    for (let n = 0; n < keyLength; n++) {
                        newLock[j + m][k + n] += key[m][n];
                    }
                }
                if (isAllOne(newLock, lockLength)) { // 정중앙 모두 1인지 확인
                    return true;
                }
            }
        }
    }
    return false;
};

const rotation90Array = (key) => {
    const len = key.length;
    const ret = Array.from(Array(len), () => Array(len).fill(null));
    for (let i = 0; i < len; ++i) {
        for (let j = 0; j < len; ++j) {
            ret[i][j] = key[len - j - 1][i];
        }
    }
    return ret;
};

const isAllOne = (newLock, len) => {
    for (let i = len; i < len * 2; i++) {
        for (let j = len; j < len * 2; j++) {
            if (newLock[i][j] !== 1) {
                return false;
            }
        }
    }
    return true;
};



function solution_2(key, lock) {
    let tempLength = key.length * 2 + lock.length - 2;
    let templock = lock.map(v => v.slice().fill(0)); // 2차원 배열 깊은 복사 및 0 초기화
    let temp = Array.from(Array(tempLength), () => Array(tempLength).fill(0));

    for (let k = 0; k < key.length * 2 - 1; k++) {
        for (let i = 0; i < key.length; i++) {
            for (let j = 0; j < key.length; j++) {
                temp[i + k][j + k] = key[i][j];
            }
        }
        for (let i = 0; i < tempLength; i++) {
            for (let j = 0; j < tempLength; j++) {
                if (i >= key.length && i < 2 * key.length) {
                    if (j >= key.length && j < 2 * key.length) {
                        templock[i - key.length][j - key.length] = lock[i - key.length][j - key.length] + temp[i][j]
                    }
                }

            }
        }
        if (allOne(templock)) return true;
    }
    return false;;
}
const allOne = (lock) => {
    let size = lock.length;
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            if (lock[i][j] != 1) return false;
        }
    }
    console.log(lock)
    return true
}

function solution_1(key, lock) {
    var answer = false;
    while (lock[0].every((item) => item === 1)) {
        lock.shift();
    }
    while (lock[lock.length - 1].every((item) => item === 1)) {
        lock.pop();
    }
    while (lock.map((item) => item[0]).every((item) => item === 1)) {
        lock.map((item) => item.shift())
    }
    while (lock.map((item) => item[lock.length - 1]).every((item) => item === 1)) {
        lock.map((item) => item.pop());
    }

    for (let i = 0; i < key.length; i++) {
        for (let j = 0; j < key.length; j++) {
            let partialKey = key.map((item) => item.slice(i, lock.length)).slice(j, lock.length)
            answer = getMatch(partialKey, lock);
            if (answer) return answer;
        }
    }
    return answer;
}

const getMatch = (key, lock) => {
    let answer = false;
    answer = matchDefault(key, lock);
    if (answer) return answer;
    answer = matchDefault90(key, lock);
    if (answer) return answer;
    answer = matchDefault180(key, lock);
    if (answer) return answer;
    answer = matchDefault270(key, lock);
    if (answer) return answer;
    return answer;
}

const matchDefault = (key, lock) => {
    let size = key.length;
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            if (key[i][j] + lock[i][j] != 1) return false;
        }
    }
    return true;
}

const matchDefault90 = (key, lock) => {
    let size = key.length;
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            if (key[i][j] + lock[i][size - 1 - j] != 1) return false;
        }
    }
    return true;
}

const matchDefault180 = (key, lock) => {
    let size = key.length;
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            if (key[i][j] + lock[size - 1 - i][j] != 1) return false;
        }
    }
    return true;
}

const matchDefault270 = (key, lock) => {
    let size = key.length;
    for (let i = 0; i < size; i++) {
        for (let j = 0; j < size; j++) {
            if (key[i][j] + lock[size - 1 - i][size - 1 - j] != 1) return false;
        }
    }
    return true;
}

console.log(solution(key, lock))