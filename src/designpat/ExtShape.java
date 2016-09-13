/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpat;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author HCH
 */
public class ExtShape implements BaseShape {

    public Shape shape;
    public boolean isSelected = false;

    @Override
    public void select(){
        isSelected = true;
    }

    @Override
    public void unselect(){
        isSelected = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.draw(shape);
        if (isSelected) {
            g2d.fill(shape);
            drawHighlightSquares(g2d, shape.getBounds2D());
        }
    }

    public boolean isSelected(){
        return isSelected;
    }

    @Override
    public boolean contains(Point currentPoint) {
        if(shape.contains(currentPoint)){
            return true;
        }
        return false;
    }

    public String toString(int indent) {
        StringBuilder builder = new StringBuilder();
        if(shape instanceof Ellipse2D){
            builder.append("ellipse");
            builder.append(" "+(int)((Ellipse2D) shape).getX());
            builder.append(" "+(int)((Ellipse2D) shape).getY());
            builder.append(" "+(int)((Ellipse2D) shape).getWidth());
            builder.append(" "+(int)((Ellipse2D) shape).getHeight()+"\n");
        }else if(shape instanceof Rectangle2D){
            builder.append("rectangle");
            builder.append(" "+(int)((Rectangle) shape).getX());
            builder.append(" "+(int)((Rectangle) shape).getY());
            builder.append(" "+(int)((Rectangle) shape).getWidth());
            builder.append(" "+(int)((Rectangle) shape).getHeight()+"\n");
        }
        return builder.toString();
    }


    @Override
    public void resize(Point currentPoint){
        if(isSelected) {
            if (shape instanceof CRectangle) {
                ((Rectangle) shape).setSize(currentPoint.x - ((Rectangle) shape).x, currentPoint.y - ((Rectangle) shape).y);
            } else if (shape instanceof Ellipse2D) {
                ((Ellipse2D) shape).setFrame(((Ellipse2D) shape).getX(), ((Ellipse2D) shape).getY(), currentPoint.x - ((Ellipse2D) shape).getX(), currentPoint.y - ((Ellipse2D) shape).getY());
            }
        }
    }

    @Override
    public void drag(Point currentPoint){
        Point centerPoint = new Point(currentPoint.x - (shape.getBounds().width / 2), currentPoint.y - (shape.getBounds().height / 2));
        if (isSelected && shape.contains(currentPoint.x, currentPoint.y)) {
            if (shape instanceof CRectangle) {
                shape = new Rectangle(centerPoint.x, centerPoint.y, shape.getBounds().width, shape.getBounds().height);
            } else if (shape instanceof Ellipse2D) {
                shape = new Ellipse2D.Double(centerPoint.x, centerPoint.y, shape.getBounds().width, shape.getBounds().height);
            }
        }
    }

    public Shape getShape(){
        return shape;
    }

    public void drawHighlightSquares(Graphics2D g2D, Rectangle2D r) {
        double x = r.getX();
        double y = r.getY();
        double w = r.getWidth();
        double h = r.getHeight();
        g2D.setPaint(Color.black);

        g2D.fill(new Rectangle.Double(x - 6.0, y - 6.0, 6.0, 6.0));
        g2D.fill(new Rectangle.Double(x + w + 1.0, y - 6.0, 6.0, 6.0));
        g2D.fill(new Rectangle.Double(x - 6.0, y + h + 1.0, 6.0, 6.0));
        g2D.fill(new Rectangle.Double(x + w + 1.0, y + h + 1.0, 6.0, 6.0));
    }
}
