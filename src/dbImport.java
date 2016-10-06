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
            String sql = "create table if not exists price (partnum text, currency text, addinfo text, id text, uktzed text, description text, value real, onstorage real, onreserve real)";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Database created.");
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
            if(i<rowArray.length){
                cell[i] = rowArray[i];
            } else if (i>=rowArray.length && i<6){
                cell[i] = "";
            } else if (i>=rowArray.length && i>=6){
                cell[i] = "0";
            }
        }
        System.out.println(cell);
    }

}