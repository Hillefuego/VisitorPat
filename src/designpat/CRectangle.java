package designpat;

import visitorPat.ShapeVisitor;
import visitorPat.Visitable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by HCH on 20-Aug-16.
 */
public class CRectangle implements BaseShape, Visitable {
    private boolean isSelected = false;
    private Shape shape;

    public CRectangle(Rectangle2D rectangle2D){
        if(rectangle2D instanceof Rectangle2D)
            this.shape = rectangle2D;
    }
    public boolean isSelected(){
        return this.isSelected;
    }

    public void resize(Point currentPoint){
        if(isSelected) {
            ((Rectangle) shape).setSize(currentPoint.x - ((Rectangle) shape).x, currentPoint.y - ((Rectangle) shape).y);
        }
    }

    public void drag(Point currentPoint){
        Point centerPoint = new Point(currentPoint.x - (shape.getBounds().width / 2), currentPoint.y - (shape.getBounds().height / 2));
        if (isSelected && shape.contains(currentPoint.x, currentPoint.y)) {
            shape = new Rectangle(centerPoint.x, centerPoint.y, shape.getBounds().width, shape.getBounds().height);
        }
    }
    public void select(){
        this.isSelected = !isSelected;
    }
    public void unselect(){
        this.isSelected = false;
    }
    public void draw(Graphics2D g2d) {
        g2d.draw(shape);
        if (isSelected) {
            g2d.fill(shape);
            drawHighlightSquares(g2d, shape.getBounds2D());
        }
    }

    public boolean contains(Point currentPoint){
        if(this.shape.contains(currentPoint))
            return true;
        else return false;
    }

    @Override
    public String toString(int indent){
        StringBuilder builder = new StringBuilder();
        builder.append("rectangle");
        builder.append(" "+(int)((Rectangle) shape).getX());
        builder.append(" "+(int)((Rectangle) shape).getY());
        builder.append(" "+(int)((Rectangle) shape).getWidth());
        builder.append(" "+(int)((Rectangle) shape).getHeight()+"\n");
        return builder.toString();
    }

    public void accept(ShapeVisitor visitor){
        visitor.visit(this);
    }
}
