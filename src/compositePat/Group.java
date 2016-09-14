package compositePat;

import designpat.BaseShape;
import designpat.ExtShape;

import java.util.List;

/**
 * Created by HCH on 01-Jun-16.
 */
public interface Group extends BaseShape {
    void add(BaseShape baseShape);

    void remove(BaseShape baseShape);

    List<BaseShape> getShapes();
}
