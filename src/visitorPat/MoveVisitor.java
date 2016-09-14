package visitorPat;

import FileIO.FileWriter;
import designpat.BaseShape;

import java.awt.*;

/**
 * Created by HCH on 26-Aug-16.
 */
public class MoveVisitor implements ShapeVisitor {
    Point location;

    public MoveVisitor(Point p){
        location = p;
    }
    public void visit(BaseShape baseShape){
        baseShape.drag(location);
    }

    public void visit(FileWriter fileWriter){}
}
