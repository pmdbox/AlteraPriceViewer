import org.jopendocument.model.OpenDocument;
import org.jopendocument.print.DefaultDocumentPrinter;
import java.io.File;

/**
 * Created by maryan on 10/5/16.
 */
public class PriceOdsReader {
    private String filename;
    private String content;

    PriceOdsReader(){
        filename = "";
        content = "";
    }

    PriceOdsReader(String file){
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

    private void ReadFile() {
        File file = new File(filename);
        if (file.exists()) {
            System.out.println();
            System.getProperty("java.classpath");

            final OpenDocument doc = new OpenDocument();
            doc.loadFrom(file);
            doc.getBody();
//            doc.getMasterStyles();

//            DefaultDocumentPrinter printer = new DefaultDocumentPrinter();
//            printer.print(doc);

        } else {
            System.out.println("Wrong filename!");
        }
    }

}
