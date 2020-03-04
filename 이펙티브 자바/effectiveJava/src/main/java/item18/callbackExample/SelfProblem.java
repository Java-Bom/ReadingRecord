package item18.callbackExample;

/**
 * Created by jyami on 2020/03/04
 */
public class SelfProblem {

    public static void main(String[] args) {

        SelfProblem globalObject = new SelfProblem();

        globalObject.hello(new CallBackHello() {
            @Override
            public void callback() {

                CallBackHello self = this;
                new SetTimeOut().setTimeout(new SetTimeOut.CallBackSetTime() {
                    @Override
                    public void callback() {

                        System.out.println("-------this--------");
                        System.out.println(this);

                        System.out.println("-------setTimeout self--------");
                        System.out.println(self);

                    }
                });
            }
        });

//        globalObject.hello(new CallBackHello() {
//            @Override
//            public void callback() {
//                globalObject.hello(new CallBackHello() {
//                    @Override
//                    public void callback() {
//                        System.out.println("=====hello callback =====");
//                        System.out.println(this);
//                    }
//                });
//            }
//        });
    }

    public void hello(CallBackHello callBackHello){
        System.out.println("======hello======");
        System.out.println(this);
        callBackHello.callback();
    }

    public interface CallBackHello {
        void callback();
    }

    static class SetTimeOut {

        public void setTimeout(CallBackSetTime callBackSetTime){
            callBackSetTime.callback();
        }

        public interface CallBackSetTime {
            void callback();
        }

    }
}


