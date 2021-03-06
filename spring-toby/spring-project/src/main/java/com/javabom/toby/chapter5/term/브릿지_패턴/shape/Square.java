package com.javabom.toby.chapter5.term.브릿지_패턴.shape;

import com.javabom.toby.chapter5.term.브릿지_패턴.draw.Draw;

public class Square extends Shape {

    public Square(final Draw draw) {
        super(draw);
    }

    @Override
    public void drawShape() {
        this.draw.draw();
        System.out.println("This is Square");
    }
}
