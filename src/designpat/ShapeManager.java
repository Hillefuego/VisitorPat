package designpat;

import compositePat.CompositeGroup;

import javax.swing.plaf.basic.BasicHTML;
import java.awt.*;
import java.io.*;
import java.util.List;

/**
 * Created by HCH on 01-Jun-16.
 */
public class ShapeManager {
    private CompositeGroup shapes = new CompositeGroup();
    private CompositeGroup selectedGroup = null;
    private CompositeGroup parentGroup = null;
    private CompositeGroup childGroup = null;

    public CompositeGroup getChildGroup(){
        return childGroup;
    }

    public void setChildGroup(CompositeGroup group){
        childGroup = group;
    }

    public CompositeGroup getParentGroup(){
        return parentGroup;
    }

    public void setParentGroup(CompositeGroup group){
        parentGroup = group;
    }

    public void addShape(BaseShape shape){
        shapes.add(shape);
    }

    public void setShapes(CompositeGroup shapes){
        this.shapes = shapes;
    }

    public void removeShape() {
        shapes.getShapes().remove(shapes.getShapes().size()-1);
    }

    public void addNewGroup(List<BaseShape> selectedShapes){
        CompositeGroup newGroup = new CompositeGroup(selectedShapes);
        shapes.add(newGroup);
        selectedShapes.stream().forEach((baseShape -> shapes.remove(baseShape)));
    }

    public void removeGroup() {
        shapes.getShapes().remove(shapes.getShapes().size()-1);
    }

    public void addChildGroup(CompositeGroup parentGroup, CompositeGroup childGroup){
        parentGroup.add(childGroup);
    }

    public void removeChildGroup(CompositeGroup parentGroup, CompositeGroup childGroup){
        parentGroup.remove(childGroup);
    }

    public void selectShapes(Point currentPoint) {
        //unselectShapes();
        if(toolboxControls.select == true) {
            shapes.getShapes().stream().forEach(baseShape -> {
                if (baseShape.contains(currentPoint)) {
                    baseShape.select();
                }
            });
        }
/*        if(toolboxControls.selectGroup == true){
            selectedGroup = setNextGroup();
            unselectShapes();
            if(selectedGroup != null){
                selectedGroup.select();
            }
        }else if(toolboxControls.select == true) {
        }*/
    }

    public void selectNextGroup(){
        selectedGroup = getNextGroup();
        if(selectedGroup != null)
            selectedGroup.select();
    }

    public CompositeGroup getNextGroup(){
        if(shapes.getShapes().size() > 0) {
            if (selectedGroup != null) {
                int currentIndex = shapes.getShapes().indexOf(selectedGroup);
                if (shapes.getShapes().indexOf(selectedGroup) == shapes.getShapes().size() - 1) {
                    if (shapes.getShapes().get(0) instanceof CompositeGroup) {
                        return (CompositeGroup)shapes.getShapes().get(0);
                    }
                    return null;
                }
                if (shapes.getShapes().get(0) instanceof CompositeGroup) {
                    return (CompositeGroup) shapes.getShapes().get(currentIndex + 1);
                }
            } else if (selectedGroup == null) {
                CompositeGroup firstGroup = null;
                for(BaseShape baseShape : shapes.getShapes()){
                    if(baseShape instanceof CompositeGroup){
                        return (CompositeGroup)baseShape;
                    }
                }
            }
            return null;
        }
        return null;
    }

    public void unselectShapes (){
        shapes.getShapes().stream().forEach((baseShape ->{
            baseShape.unselect();
        }));
    }

    public CompositeGroup getSelectedGroup(){
        return selectedGroup;
    }

    public List<BaseShape> getShapes(){
        return this.shapes.getShapes();
    }

    public void drawShapes(Graphics2D g2d) {
        shapes.getShapes().stream().forEach((baseShape -> {
            baseShape.draw(g2d);
        }));
    }


    public void clear(){
        shapes.getShapes().clear();
    }

    public void save() {
        try {
            File file = new File("output");
            FileOutputStream fos = new FileOutputStream(file);
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                unselectShapes();
                oos.writeObject(shapes);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream fis = new FileInputStream("output");
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                shapes = (CompositeGroup) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void dragShapes(Point currentPoint){
        for (BaseShape baseShape : shapes.getShapes()) {
            baseShape.drag(currentPoint);
        }
    }

/*    public boolean resizeSquaresCheck(Shape shape, int oldX, int oldY) {
        double x = shape.getBounds().getX();
        double y = shape.getBounds().getY();
        double w = shape.getBounds().getWidth();
        double h = shape.getBounds().getHeight();
        CRectangle.Double rectNW = new CRectangle.Double(x - 6.0, y - 6.0, 6.0, 6.0);
        CRectangle.Double rectNE = new CRectangle.Double(x + w + 1.0, y - 6.0, 6.0, 6.0);
        CRectangle.Double rectSW = new CRectangle.Double(x - 6.0, y + h + 1.0, 6.0, 6.0);
        CRectangle.Double rectSE = new CRectangle.Double(x + w + 1.0, y + h + 1.0, 6.0, 6.0);

        if (cursorInBounds(rectNW, oldX, oldX)) {
            return true;
        } else if (cursorInBounds(rectNE, oldX, oldY)) {
            return true;
        } else if (cursorInBounds(rectSW, oldX, oldY)) {
            return true;
        } else if (cursorInBounds(rectSE, oldX, oldY)) {
            return true;
        } else return false;
    }*/

    public void resizeShape(Point currentPoint, Point lastPoint){
        shapes.getShapes().stream().forEach((baseShape -> baseShape.resize(currentPoint)));
    }
    public String printAll(){
        StringBuilder builder = new StringBuilder();
        /*shapes.getShapes().stream().forEach((baseShape -> {
            builder.append(baseShape.toString());
        }));*/
        builder.append(shapes.toString(2));
        return builder.toString();
    }

}
