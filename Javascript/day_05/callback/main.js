function doTask1(name, callback){
    console.log("bắt đầu công việc");
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function(){
        callback();
    },3000)
}

function doTask2(name, callback){
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function(){
        callback()
    },4000)
}

function doTask3(name, callback){
    console.log(`Thực hiện công việc ${name}`);
    setTimeout(function(){
        callback()
    },3000)
}

function finish(){
    console.log("Kết thúc công việc");
}

// doTask1("Làm bài tập", finish);

doTask1("làm bài tập", function(){
    doTask2("nấu cơm",function(){
        doTask3("Đá bóng", finish)
    })
})
// Thực hiện song song
doTask3("Chơi game", finish)