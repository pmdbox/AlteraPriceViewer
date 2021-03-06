import java.sql.*;
/**
 * Created by maryan on 10/6/16.
 */
public class dbSelect {

    private Connection c = null;

    dbSelect(){
        dbConnect();
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

                    result += "<td  class=\"mdl-data-table__cell--non-numeric full-width\">\n";
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
