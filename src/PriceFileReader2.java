import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by maryan on 03.10.2016.
 */
public class PriceFileReader2 {

    private String filename;
    private String content;

    PriceFileReader2(){
        filename = "";
        content = "";
    }

    PriceFileReader2(String file){
        filename = file;
        content = "";
        ReadFile();
    }

    public void setFilename(String file){
        filename = file;
        content = "";
        ReadFile();
    }

    public String  getFilename(){
        return filename;
    }

    private void ReadFile(){

        File file=new File(filename);
        if(file.exists()) {
            FileInputStream inputstream = null;
            Scanner reader=null;
            int stringcounter=0;

            try {
                inputstream = new FileInputStream(file);
                reader = new Scanner(inputstream, "UTF-8");

                while (reader.hasNextLine()) {

                    String line = reader.nextLine();
                    String values[] = line.split("\t");
                    stringcounter++;
                    //System.out.println(line);
                    for (int i=0;i<values.length;i++) {
                        System.out.println(values[i]);
                    }
                    System.out.println(stringcounter+": "+values.length);
                }
            }
            catch (FileNotFoundException exc)
            {
                System.out.println(exc.getMessage());
            }
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

        }
        else
        {
            System.out.println("Wrong filename!");
        }
    }

    public String getContent(){
        return content;
    }

}
