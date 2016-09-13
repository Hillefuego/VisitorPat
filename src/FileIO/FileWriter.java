package FileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by HCH on 18-Jun-16.
 */
public class FileWriter {
    File saveFile = new File("shapesFile");

    public void write(String fileString)throws IOException{
        try {
            saveFile.createNewFile();
            FileOutputStream oFile = new FileOutputStream(saveFile, false);
            oFile.write(fileString.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
