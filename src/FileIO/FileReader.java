package FileIO;

import compositePat.CompositeGroup;
import designpat.BaseShape;
import designpat.CEllipse;
import designpat.CRectangle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by HCH on 18-Jun-16.
 */

public class FileReader {

    public CompositeGroup readFile() throws IOException {
        List<String> readList = new ArrayList<>();
        CompositeGroup fileGroup;

        try (Stream<String> stream = Files.lines(Paths.get("shapesFile"))) {

            readList = stream.collect(Collectors.toList());
            if(readList.size()>0);
                readList.remove(0);
            //System.out.print(stream.findFirst().filter(line->line.contains("group")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileGroup = parseToGroup(readList, new CompositeGroup(),2);
        return fileGroup;
    }


    /**
     * ParseToGroup recursively parses a String list into the BaseShape file structure.
     * While it reads the list until its empty it determines if the current line is still in
     * the same group by counting the whitespace. If it is, it type-tests the BaseShape in the current line
     * and adds them accordingly until done. If it isn't, the current group is done and can be returned.
     * Ellipses and rectangles are added normally but groups are called with another parseToGroup.
     **/
    public CompositeGroup parseToGroup(List<String> readList, CompositeGroup group,int depth){

        while(!readList.isEmpty()){
            String currentLine = readList.get(0);
            //System.out.print(Arrays.asList(currentLine.trim().split(" ")));
            if(whiteSpaceCounter(currentLine)==depth){
                if(currentLine.contains("group")) {
                    readList.remove(currentLine);
                    group.add(parseToGroup(readList, new CompositeGroup(),depth+2));
                }
                if(currentLine.contains("rectangle")) {
                    List<String> shapeCoords = Arrays.asList(currentLine.trim().split(" "));
                    Rectangle2D rect = new Rectangle(Integer.parseInt(shapeCoords.get(1)),
                            Integer.parseInt(shapeCoords.get(2)),
                            Integer.parseInt(shapeCoords.get(3)),
                            Integer.parseInt(shapeCoords.get(4)));
                    group.add(new CRectangle(rect));
                    readList.remove(currentLine);
                }
                if(currentLine.contains("ellipse")) {
                    List<String> shapeCoords = Arrays.asList(currentLine.trim().split(" "));
                    Ellipse2D ellipse = new Ellipse2D.Float(Integer.parseInt(shapeCoords.get(1)),
                            Integer.parseInt(shapeCoords.get(2)),
                            Integer.parseInt(shapeCoords.get(3)),
                            Integer.parseInt(shapeCoords.get(4)));
                    //Ellipse2D ellipse = new Ellipse2D.Float((int)shapeCoords.get(1),(int)shapeCoords.get(2),(int)shapeCoords.get(3),(int)shapeCoords.get(4));
                    group.add(new CEllipse(ellipse));
                    readList.remove(currentLine);
                }
            }else{
                return group;
            }
        }

        return group;
    }

    private int whiteSpaceCounter(String line){
        int spaceCount = 0;
        for (int i = 0; i <= line.length() && line.charAt(i) == ' '; i++) {
            spaceCount ++;
        }

        return spaceCount;
    }
}
