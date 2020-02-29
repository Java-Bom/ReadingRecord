function add(x, callback){
    let sum = x + x;
    callback(sum);
}

function minus(x, callback){
    let sum = x - x;
    callback(sum);
}

add(3, function(result){
    console.log("=========add1");
    console.log(this);
    var self = this;

    setTimeout(function(){

        console.log("---------------this");
        console.log(this);

        console.log("---------------setTimeout self");
        console.log(self);

    },200);

});

minus(3, function (result) {
    console.log("---------------minus");
    console.log(this);
    console.log(self);
});