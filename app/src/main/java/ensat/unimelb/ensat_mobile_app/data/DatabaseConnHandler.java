package ensat.unimelb.ensat_mobile_app.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * Created by Purathani on 26/09/2015.
 */
public class DatabaseConnHandler {
    private static final String url = "jdbc:mysql://<server>:<port>/<database>";
    private static final String user = "<username>";
    private static final String pass = "<password>";


    public void connectDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            String result = "Database connection success\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from table_name");
            ResultSetMetaData rsmd = rs.getMetaData();

            while(rs.next()) {
                result += rsmd.getColumnName(1) + ": " + rs.getInt(1) + "\n";
                result += rsmd.getColumnName(2) + ": " + rs.getString(2) + "\n";
                result += rsmd.getColumnName(3) + ": " + rs.getString(3) + "\n";
            }
            //tv.setText(result);
        }
        catch(Exception e) {
            e.printStackTrace();
            //tv.setText(e.toString());
        }

    }
}
