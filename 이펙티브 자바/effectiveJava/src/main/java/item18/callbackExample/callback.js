function add(x, callback){
    let sum = x + x;
    console.log(sum);
    callback(sum);
}

let out_string = "hello";
add(3, function(result){
    add(result, function (result2) {
        console.log(result);
        console.log(result2);
        console.log(out_string);
        add(result2, function (result3) {
            console.log(result);
            console.log(result2);
            console.log(result3);
            console.log("hi" + result3);
        })
    })
});