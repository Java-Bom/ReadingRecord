function add(x, callback) {
    let sum = x + x;
    callback(sum);
}

function minus(x, callback) {
    let sum = x - x;
    callback(sum);
}

<<<<<<< HEAD
const Rectangle = class {
    constructor(height, width) {
        this.height = height;
        this.width = width;
    }
    area() {
        console.log("rectangle========");
        console.log(this);
        return this.height * this.width;
    }
};

add(3, function(result){
    console.log("=========add1");
    console.log(this);
    var self = this;
    setTimeout(function(){
=======
add(3, function (result) {
    console.log("=========add1");
    console.log(this);
    var self = this;

    setTimeout(function () {
>>>>>>> 5dcb59de08ac20fa64163ef9c8ad4b6c3d687e1b

        console.log("---------------this"+this);
        console.log();

        console.log("---------------setTimeout self");
        console.log(self);

<<<<<<< HEAD
    },200);
=======
    }, 200);

>>>>>>> 5dcb59de08ac20fa64163ef9c8ad4b6c3d687e1b
});


minus(3, function () {
    console.log("minus3:========");
    console.log(this);
    new Rectangle(5,8).area();
    function hello(callback){
        callback();
    }
    hello(function () {
        console.log("hello:=====");
        console.log(this);
    })
});


var obj = {
    name: "minjeong",
    function(){
        console.log("hello");
        console.log(this);
    },
    helloFunction : function(){
        console.log("hello function");
        console.log(this);
    }
};

console.log("obj=====");
obj.function();
obj.helloFunction();
console.log(obj.name);
