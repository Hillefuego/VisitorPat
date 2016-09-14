package designpat;

import visitorPat.ShapeVisitor;
import visitorPat.Visitable;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by HCH on 20-Aug-16.
 */
public class CEllipse implements BaseShape, Visitable {
    private boolean isSelected = false;
    private Shape shape;

    public CEllipse(Ellipse2D ellipse2D){
        if(ellipse2D instanceof Ellipse2D)
            this.shape = ellipse2D;
    }
    public boolean isSelected(){
        return this.isSelected;
    }

    public void resize(Point currentPoint){
        if(isSelected)
            ((Ellipse2D) shape).setFrame(((Ellipse2D) shape).getX(), ((Ellipse2D) shape).getY(), currentPoint.x - ((Ellipse2D) shape).getX(), currentPoint.y - ((Ellipse2D) shape).getY());
    }

    public void drag(Point currentPoint){
        Point centerPoint = new Point(currentPoint.x - (shape.getBounds().width / 2), currentPoint.y - (shape.getBounds().height / 2));
        if (isSelected && shape.contains(currentPoint.x, currentPoint.y)) {
            shape = new Ellipse2D.Double(centerPoint.x, centerPoint.y, shape.getBounds().width, shape.getBounds().height);
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
        builder.append("ellipse");
        builder.append(" "+(int)((Ellipse2D) shape).getX());
        builder.append(" "+(int)((Ellipse2D) shape).getY());
        builder.append(" "+(int)((Ellipse2D) shape).getWidth());
        builder.append(" "+(int)((Ellipse2D) shape).getHeight()+"\n");
        return builder.toString();
    }

    public void accept(ShapeVisitor visitor){
        visitor.visit(this);
    }
}
