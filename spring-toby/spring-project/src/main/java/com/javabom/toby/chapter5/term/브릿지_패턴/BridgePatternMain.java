package com.javabom.toby.chapter5.term.브릿지_패턴;

import com.javabom.toby.chapter5.term.브릿지_패턴.draw.Draw;
import com.javabom.toby.chapter5.term.브릿지_패턴.draw.PencilDraw;
import com.javabom.toby.chapter5.term.브릿지_패턴.draw.BrushDraw;
import com.javabom.toby.chapter5.term.브릿지_패턴.shape.Ellipse;
import com.javabom.toby.chapter5.term.브릿지_패턴.shape.Square;

public class BridgePatternMain {
    public static void main(String[] args) {
        Draw pencilDraw = new PencilDraw();
        Draw brushDraw = new BrushDraw();

        Ellipse ellipse = new Ellipse(pencilDraw);
        Square square = new Square(brushDraw);

        ellipse.drawShape();
        square.drawShape();
    }
}
