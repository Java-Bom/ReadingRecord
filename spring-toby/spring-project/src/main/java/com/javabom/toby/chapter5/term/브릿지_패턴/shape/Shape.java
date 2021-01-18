package com.javabom.toby.chapter5.term.브릿지_패턴.shape;

import com.javabom.toby.chapter5.term.브릿지_패턴.draw.Draw;

public abstract class Shape {
    protected Draw draw;

    public Shape(final Draw draw) {
        this.draw = draw;
    }

    abstract public void drawShape();

}
