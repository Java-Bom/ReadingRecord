var java_bom = {
    data: "JAVA BOM",
};

var object = {
    data : 'dummy',
    time_callback : function(){
        setTimeout(function(){
            console.log("==== this.data when using bind() ====");
            console.log(this);
            console.log(this.data);
        }.bind(java_bom), 1000)}
    // this.data의 값은 bind() 안에넣은 객체 값으로 저장
    // bind()는 apply(), call()과는 다르게 바로 실행되지 않는다.
};

var object_not_bind = {
    data : 'dummy',
    time_callback : function(){
        setTimeout(function(){
            console.log("==== this.data when not using bind() ====");
            console.log(this);
            console.log(this.data);
        }, 1000)}
        // setTimeout는 Timeout이라는 객체에 있는 함수이며, 여기에는 this.data라는 값이 없다.
};

// bind() 메소드는 바로 실행되는 것이 아니라 설정을 저장하는 것
// 주로 callback 함수에서 많이 쓰인다.

object.time_callback();
object_not_bind.time_callback();



