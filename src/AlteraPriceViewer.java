/**
 * Created by maryan on 02.10.2016.
 */
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.stream.Stream;
//import java.io.File;
import java.io.*;

public class AlteraPriceViewer {
    public static void main(String[] args) {

        System.out.println("Print price file to console.");
        if (args.length>0) {
            File file=new File(args[0]);
            if(file.exists()) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
