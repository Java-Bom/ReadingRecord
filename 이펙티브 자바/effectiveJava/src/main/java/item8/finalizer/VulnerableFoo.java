package item8.finalizer;

public class VulnerableFoo extends SuperVulnerableFoo{
    static SuperVulnerableFoo superVulnerableFoo;

    public VulnerableFoo(int value) {
        super(value);
    }

    @Override
    protected void finalize() throws Throwable {
        superVulnerableFoo = this;
    }

    public static void main(String[] args) throws InterruptedException {
        try {
            new VulnerableFoo(-1);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        System.gc();
        System.runFinalization();
        Thread.sleep(1000);
        if(superVulnerableFoo != null) {
            // 객체가 생성되면 안됨에도 value = 0 인 Super 인스턴스가 생성됨.
            System.out.println("value = "+superVulnerableFoo.getValue()+" 인 객체 생성");
        }
    }

}
