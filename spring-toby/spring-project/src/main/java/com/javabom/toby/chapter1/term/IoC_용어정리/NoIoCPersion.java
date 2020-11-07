package com.javabom.toby.chapter1.term.IoC_용어정리;

public class NoIoCPersion {
    private final Pencil pencil;

    public NoIoCPersion(Pencil pencil) {
        this.pencil = pencil;
    }

    public void write() { // 자신이 생성의 책임을 가짐
        Pencil pencil = new BasicPencil();
        pencil.write();
    }

    public void writeByIoC() { // IoC 컨테이너에 의해 생성된 pencil 을 가지고 사용
        pencil.write();
    }

    public void writeByDi(Pencil pencil) { // 외부로부터 주입받은 pencil
        pencil.write();
    }
}
