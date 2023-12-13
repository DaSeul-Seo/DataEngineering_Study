// 비동기 예제 함수
const asyncFnc = () => {
    alert("[비동기] 1st");
    
    setTimeout(() =>{
        alert("[비동기] 2nd");
    }, 2000);
    
    alert("[비동기] 3rd");
}

// asyncFnc();

// ======================================
// 동기 예제 함수
async function someAsyncTask() {
    // resolve : 성공, reject : 실패
    // promise : 동기적으로 코드를 실행시킨다.
    return new Promise((resolve, reject) => {
        setTimeout(() =>{
            resolve("[동기] 2nd");
        }, 2000);
    })
    // 첫 번째 ppromise 값을 받늗다.
    .then((result) => {
        alert(result); // [동기] 2nd
        return "[동기] 3rd";
    })
    // 두 번째 promise 값을 받는다.
    .then((result) => {
        alert(result);
        return "[동기] 4th";
    });
}

async function main() {
    alert("[동기] 1st");
    // await는 promise 함수가 실행될 떄만 적용됨
    const result = await someAsyncTask();
    // someAsyncTask의 함수 값을 기다려서 결과를 받으면 alert
    alert(result);
}

main();