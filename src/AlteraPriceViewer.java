/**
 * Created by maryan on 02.10.2016.
 */
public class AlteraPriceViewer {
    public static void main(String[] args) {
        if (args.length>0) {
            PriceFileReader2 price2=new PriceFileReader2(args[0]);
            PriceFileReader price=new PriceFileReader(args[0]);
            System.out.println(price.getContent());
        }
        else{
            System.out.println("Filename is missing.");
        }
    }
}
