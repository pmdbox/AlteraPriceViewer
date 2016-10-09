import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
//import com.sun.org.apache.xpath.internal.operations.String;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maryan on 02.10.2016.
 */

public class AlteraPriceViewer {
    public static void main(String[] args) {

        int serverHttpPort=8000;

//****************************************************
//**********   Import PRICE from TXT file.

        if (args.length>0) {
            dbImport db = new dbImport();
            db.clearTable();

            String filename = args[0];
            String ext = "";

            int i = filename.lastIndexOf('.');
            int p = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));

            if (i > p) {
                ext = filename.substring(i+1);
            }
            if (ext.equals("txt")){
                PriceFileReader2 price=new PriceFileReader2(filename);

                while(price.hasNextRow()){
                    System.out.print("Added "+price.getCurrentLineNum()+" lines.\r");
                    if(price.getCurrentLineNum()>6){
                        db.addRow(price.getNextRow());
                    }
                    else{
                        price.getNextRow();
                    }
                }
            }
        }
        else{
            System.out.println("Filename for price import is missing.");
        }

//**********   End of import PRICE.


//****************************************************
//**********   Start Web server.
        try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(serverHttpPort), 0);
            server.createContext("/", new StaticFileServer());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Web-server was started succesfully at port number "+serverHttpPort);
        }
        catch (IOException e)
        {
            System.err.println("Exception in class : " + e.getMessage());
        }

    }

    @SuppressWarnings("restriction")
    static class StaticFileServer implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String fileId = exchange.getRequestURI().getPath();

            if(exchange.getRequestURI().getQuery()==null){
//                System.out.println("Query parameters are absent.");
            }
            else{
                Map <String,String>params=StaticFileServer.queryToMap(exchange.getRequestURI().getQuery());
/*
                System.out.println(params.get("text"));
                System.out.println(params.get("sort"));
                System.out.println(params.get("sortdirection"));
*/
            }

            fileId=fileId.substring(1);
            fileId=fileId.replace("/",File.separator);
            //System.out.println(fileId);
            if (fileId.isEmpty()){
                fileId = "index.html";
            }
            System.out.println(fileId);
            File file = getFile(fileId);

            if (file == null) {
                String response = "Error 404 File not found.";
                exchange.sendResponseHeaders(404, response.length());
                OutputStream output = exchange.getResponseBody();
                output.write(response.getBytes());
                output.flush();
                output.close();
                System.out.println("404");
            } else {
                System.out.println("200");
                exchange.sendResponseHeaders(200, 0);
                OutputStream output = exchange.getResponseBody();
                FileInputStream fs = new FileInputStream(file);
                final byte[] buffer = new byte[0x10000];
                int count = 0;
                while ((count = fs.read(buffer)) >= 0) {
                    output.write(buffer, 0, count);
                }
                output.flush();
                output.close();
                fs.close();
            }
        }

        private File getFile(String fileId) {
            fileId=fileId.replace("/","");
            File file=new File(fileId);
            if(file.exists()){
                return file;
            }
            else {
                return null;
            }

        }

        public static Map<String, String> queryToMap(String query){
            Map<String, String> result = new HashMap<String, String>();
            for (String param : query.split("&")) {
                String pair[] = param.split("=");
                if (pair.length>1) {
                    result.put(pair[0], pair[1]);
                }else{
                    result.put(pair[0], "");
                }
            }
            return result;
        }
    }
}
