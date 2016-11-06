import java.io.*;
import java.util.Scanner;

/**
 * Created by maryan on 03.10.2016.
 */
public class PriceFileReader2 {

    private String filename;
    private File file=null;
//    private FileInputStream inputstream = null;
    private BufferedInputStream inputstream = null;
    private Scanner reader=null;
    private int currentLineNum;

    PriceFileReader2(String filename){
        currentLineNum = 0;
        file=new File(filename);
        if(file.exists()) {
            try {
                inputstream = new BufferedInputStream(new FileInputStream(file));
                reader = new Scanner(inputstream, "UTF-8");
/*
                while (reader.hasNextLine()) {

                    String line = reader.nextLine();
                    String values[] = line.split("\t");
                    //System.out.println(line);
                    for (int i=0;i<values.length;i++) {
                        System.out.println(values[i]);
                    }
                    System.out.println(values.length);
                }
*/
            }
            catch (FileNotFoundException exc)
            {
                System.out.println(exc.getMessage());
            }
            /*
            finally {
                if (inputstream != null) {
                    try{
                        inputstream.close();
                    }
                    catch (IOException exc){
                        System.out.println(exc.getMessage());
                    }
                }
                if (reader != null) {
                    reader.close();
                }
            }
            */

        }
        else
        {
            System.out.println("Wrong filename!");
        }

    }

    public boolean hasNextRow(){
        if(reader==null) {
            return false;
        }
        else
        {
            return reader.hasNextLine();
        }
    }

    public String[] getNextRow(){
        String line = reader.nextLine();
        String values[] = line.split("\t");
        currentLineNum++;
        //System.out.println(line);
        //for (int i=0;i<values.length;i++) {
        //    System.out.println(values[i]);
        //}
        return values;
    }

    public int getCurrentLineNum(){
        return currentLineNum;
    }


}
