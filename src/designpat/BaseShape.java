package designpat;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by HCH on 03-Jul-16.
 */
public interface BaseShape {
    default void drawHighlightSquares(Graphics2D g2D, Rectangle2D r) {
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
    void resize(Point currentPoint);
    void drag(Point currentPoint);
    void select();
    void unselect();
    void draw(Graphics2D g2d);
    boolean isSelected();
    boolean contains(Point currentPoint);
    String toString(int indent);
}
