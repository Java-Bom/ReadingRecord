function hello(callback) {
    console.log("======hello======\n");
    console.log(this);
    callback();
}

hello(function(){
    const self = this;
    setTimeout(function(){

        console.log("-------this--------\n");
        console.log(this);

        console.log("-------setTimeout self--------\n");
        console.log(self);

    },200);

});
//
// hello(function(){
//
//     hello(function () {
//         console.log("=====hello callback =====\n");
//         console.log(this);
//     });
//
// });