import java.sql.*;
/**
 * Created by maryan on 10/6/16.
 */
public class dbImport {

    private Connection c = null;

    dbImport(){
        dbConnect();
        createTable();
    }

    private void dbConnect(){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");

            Statement stmt = null;
            stmt=c.createStatement();
            String sql = "PRAGMA journal_mode = OFF";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    private void dbDisconnect(){
        try {
            if (!c.isClosed() || !(c==null)){
                c.close();
                System.out.println("Database disconnected.");

            }

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    private void createTable()
    {
        try {
            Statement stmt = null;
            stmt=c.createStatement();
            String sql = "create table if not exists price (partnum text collate nocase, currency text, addinfo text, id text, uktzed text, description text collate nocase, value real, onstorage real, onreserve real) ";
            stmt.executeUpdate(sql);
            stmt.close();
            //System.out.println("Database created.");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void clearTable(){
        try {
            Statement stmt = null;
            stmt=c.createStatement();
            String sql = "delete from price";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Database cleared.");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void addRow (String[] rowArray){

        String[] cell=new String[9];
        for(int i=0;i<9;i++){

            if(i<rowArray.length && i<6) {
                rowArray[i]=rowArray[i].replace("\"","\'");
                cell[i] = rowArray[i];
            } else if (i<rowArray.length && i>=6){
                rowArray[i]=rowArray[i].replace(",",".");
                rowArray[i]=rowArray[i].replaceAll("[^0-9.]","");
                if(rowArray[i].length()==0){
                    cell[i] = "0";
                }
                else{
                    cell[i] = rowArray[i];
                }

            } else if (i>=rowArray.length && i<6){
                cell[i] = "";
            } else if (i>=rowArray.length && i>=6){
                cell[i] = "0";
            }

        }
        try {
            Statement stmt = null;
            stmt=c.createStatement();
            String sql = "insert into price values ("+
                    "\""+cell[0]+"\","+
                    "\""+cell[1]+"\","+
                    "\""+cell[2]+"\","+
                    "\""+cell[3]+"\","+
                    "\""+cell[4]+"\","+
                    "\""+cell[5]+"\","+
                    cell[6]+","+
                    cell[7]+","+
                    cell[8]+");";
            //System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public String getSelection(String text,String sort,String sortdirection){
        String result=null;
        int counter=0;

        try {
            Statement stmt = null;
            stmt=c.createStatement();
            String sql=null;
            if(text.length()>2) {
                if (!sort.equals("asis")) {
                    sql = "select * from price where (partnum like \'%" + text + "%\' or description like \'%" + text + "%\') collate nocase order by " + sort + " " + sortdirection;
                } else {
                    sql = "select * from price where (partnum like \'%" + text + "%\' or description like \'%" + text + "%\') collate nocase";
                }

                //System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);

                result = "\n";
                while (rs.next()) {
                    result += "<tr>\n";

                    result += "<td  class=\"mdl-data-table__cell--non-numeric\">\n";
                    result += rs.getString("partnum");
                    result += "</td>\n";

                    result += "<td  class=\"mdl-data-table__cell--non-numeric\">\n";
                    result += rs.getString("description");
                    result += "</td>\n";

                    result += "<td  class=\"mdl-data-table__cell--non-numeric\">\n";
                    result += rs.getString("uktzed");
                    result += "</td>\n";

                    result += "<td>\n";
                    result += rs.getString("value");
                    result += "</td>\n";

                    result += "<td  class=\"mdl-data-table__cell--non-numeric\">\n";
                    result += rs.getString("currency");
                    result += "</td>\n";

                    result += "<td>\n";
                    result += rs.getString("onstorage");
                    result += "</td>\n";

                    result += "<td>\n";
                    result += rs.getString("onreserve");
                    result += "</td>\n";

                    result += "</tr>\n";
                    counter++;
                }

                rs.close();
            }
            stmt.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return result;
    }

}
