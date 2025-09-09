//Demonstration based on Java JDBC Tutorial by Simplilearn (learning resource material).
package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class University {
    public static void main(String[] args) throws SQLException{
        //My own creation of the username, password, URL
        String username = "ValenciaPuma";
        String password = "Oceanviews";
        String query = "SELECT * FROM EngineeringStudents";
        String url = "jdbc:sqlite:C:/sqlite/University.db";


        try{
            //Establishing a connection from the driver to the database
            Connection con = DriverManager.getConnection(url, username, password);
            //Creating a statement to execute
            Statement statement = con.createStatement();
            //Hold the results of the statement request
            ResultSet result = statement.executeQuery(query);

            //A loop that access each entry of the database and prints the data
            while(result.next()){
                String UniversityData = "";
                for(int i = 1; i <= 6; i++){
                    UniversityData += result.getString(i) + ": ";
                }
                System.out.println(UniversityData);
            }
        }
        //Print a message when an SQL Exception occurs
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}

