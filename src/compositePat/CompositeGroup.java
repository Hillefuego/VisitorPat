package compositePat;

import designpat.BaseShape;
import designpat.ExtShape;

import java.awt.*;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HCH on 01-Jun-16.
 */
public class CompositeGroup implements BaseShape {
    private List<BaseShape> shapes = new ArrayList<>();
    private boolean isSelected = false;

    public CompositeGroup(){

    }

    public CompositeGroup(List<BaseShape> shapes){
        this.shapes = shapes;
    }

    public void add(BaseShape baseShape){
        shapes.add(baseShape);
    }

    public void remove(BaseShape baseShape){
        shapes.remove(baseShape);
    }

    @Override
    public void select() {
        if(shapes.size() > 0){
            shapes.stream().forEach(baseShape -> {
                baseShape.select();
            });
        }
        isSelected = true;
    }

    public List<BaseShape> getShapes(){
        return this.shapes;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean contains(Point currentPoint) {
        if(shapes.size() > 0){
            shapes.stream().forEach(baseShape -> {
                if(baseShape.contains(currentPoint)) {
                    baseShape.select();
                }
            });
        }
        return false;
    }


    public String toString(int indent) {
        StringBuilder builder = new StringBuilder();
        builder.append("group "+shapes.size()+"\n");
        //if(shapes.size() > 0){
        String whitespace ="";
        for(int i = 0; i< indent; i++){
            whitespace+=" ";
        }

        for (BaseShape baseShape:shapes){
            builder.append(whitespace+baseShape.toString(indent+2));
        }
/*            shapes.stream().forEach(baseShape -> {
                builder.append(whitespace+baseShape.toString());
            });*/
        //}
        return builder.toString();
    }

    @Override
    public void resize(Point currentPoint) {
        if(shapes.size() > 0){
            shapes.stream().forEach(baseShape -> {
                baseShape.resize(currentPoint);
            });
        }
    }

    @Override
    public void drag(Point currentPoint) {
        if(shapes.size() > 0){
            shapes.stream().forEach(baseShape -> {
                baseShape.drag(currentPoint);
            });
        }
    }

    @Override
    public void unselect() {
        if(shapes.size() > 0){
            shapes.stream().forEach(baseShape -> {
                baseShape.unselect();
            });
        }
        isSelected = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(shapes.size() > 0){
            shapes.stream().forEach(baseShape -> {
                baseShape.draw(g2d);
            });
        }
    }
}
