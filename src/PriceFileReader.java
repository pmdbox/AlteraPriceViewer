import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by maryan on 03.10.2016.
 */
public class PriceFileReader {

    private String filename;
    private String content;

    PriceFileReader(){
        filename = "";
        content = "";
    }

    PriceFileReader(String file){
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

            try {
                    inputstream = new FileInputStream(file);
                    reader = new Scanner(inputstream, "UTF-8");

                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        System.out.println(line);
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
