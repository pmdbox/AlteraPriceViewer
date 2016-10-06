/**
 * Created by maryan on 02.10.2016.
 */

public class AlteraPriceViewer {
    public static void main(String[] args) {

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





        if (args.length>0) {
/*
            String filename = args[0];
            String ext = "";

            int i = filename.lastIndexOf('.');
            int p = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));

            if (i > p) {
                ext = filename.substring(i+1);
            }
            if (ext.equals("ods")){
                PriceOdsReader priceods=new PriceOdsReader(filename);
            }
            else{
                PriceFileReader price=new PriceFileReader(filename);
                System.out.println(price.getContent());
            }
*/
        }
        else{
            System.out.println("Filename is missing.");
        }

    }
}
