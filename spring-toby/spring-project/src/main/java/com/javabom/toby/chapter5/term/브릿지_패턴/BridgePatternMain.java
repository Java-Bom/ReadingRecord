package com.javabom.toby.chapter5.term.브릿지_패턴;

import com.javabom.toby.chapter5.term.브릿지_패턴.draw.Draw;
import com.javabom.toby.chapter5.term.브릿지_패턴.draw.DrawCircle;
import com.javabom.toby.chapter5.term.브릿지_패턴.draw.DrawRectangle;
import com.javabom.toby.chapter5.term.브릿지_패턴.shape.Ellipse;
import com.javabom.toby.chapter5.term.브릿지_패턴.shape.Square;

public class BridgePatternMain {
    public static void main(String[] args) {
        Draw circleDraw = new DrawCircle();
        Draw rectangleDraw = new DrawRectangle();

        Ellipse ellipse = new Ellipse(circleDraw);
        Square square = new Square(rectangleDraw);

        ellipse.drawShape();
        square.drawShape();
    }
}
