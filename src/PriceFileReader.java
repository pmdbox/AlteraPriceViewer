import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

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
            InputStreamReader reader=null;
            try{
                inputstream = new FileInputStream(file);
                reader = new InputStreamReader(inputstream,"UTF-8");

                int bytecontent;
                while ((bytecontent = reader.read()) != -1) {
                    content+=(char) bytecontent;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if(reader !=null){
                    try{
                        reader.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
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
