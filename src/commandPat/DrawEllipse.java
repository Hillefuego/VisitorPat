package commandPat;

import designpat.BaseShape;
import designpat.CEllipse;
import designpat.ExtShape;
import designpat.ShapeManager;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by HCH on 01-Jun-16.
 */
public class DrawEllipse implements Command{
    private ShapeManager shapeManager;
    private BaseShape baseShape;


    public DrawEllipse(Point lastPoint, Point currentPoint, ShapeManager shapeManager){
        this.shapeManager = shapeManager;
        this.baseShape = new CEllipse(new Ellipse2D.Double(lastPoint.x, lastPoint.y, Math.abs(currentPoint.x - lastPoint.x), Math.abs(currentPoint.y - lastPoint.y)));
    }

    @Override
    public void execute() {
        shapeManager.addShape(baseShape);
    }

    @Override
    public void undo() {
        shapeManager.removeShape();
    }
}
