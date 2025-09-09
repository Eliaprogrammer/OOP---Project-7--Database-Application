//Elia Schwartz, COP 3330 - 33777, June 1, 2025.
/*Understand and get comfortable creating a database and accessing pieces of data.
Students were tasked with creating a University database with Engineering students' inputs using the SQLite system.
Executing queries to display, add, remove, and sort students in the database,
in the program, and in the SQLite helper GUI, showing on the command line and/or console.

I used the video from a Valencia College Professor, Evans - Database Connectivity with SQLite and IntelliJ video as a template that was
provided to me by my COP 3330C professor Leon Silas, as resource material.
I used and created my own data for all the entries in the EngineeringStudents table
*/

package DBHelper;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
public class UniversityDatabase {
    public static void main(String[] args) {
        //Create an instance of our database class, EngineeringStudents
        EngineeringStudents db1 = new EngineeringStudents();

        /*Create a 2D arraylist to hold the result of a query
        (This can hold columns and rows of any type)
         */
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

        //perform a query to get all content of the database
        System.out.println("The original version");
        data = db1.getExecuteResult("SELECT * FROM EngineeringStudents;");

        //Print the result of the query, by printing what is stored in ArrayList data
        for (List<Object> record : data) {
            System.out.println(record.toString());
        }

        //add a new entry to the database
        System.out.println("\nAdding two more entry");
        db1.execute("INSERT INTO EngineeringStudents VALUES ('30278', 'AAE', 'Nancy', 'Wilson', 1991, 3043);");
        db1.insert(74508, "CSE", "Jason", "Schwartz", 2025, 45);
        data = db1.getExecuteResult("SELECT * FROM EngineeringStudents;");
        printDatabase(data);

        //Delete a row of data from the database on student_ID
        System.out.println("\nTwo student_ID data was deleted.");
        db1.delete("UniversityRank", "567");
        db1.delete("PassOutYear", "2002");

        data = db1.getExecuteResult("SELECT * FROM EngineeringStudents;");
        System.out.println("Here are the contents of the database:");
        printDatabase(data);

        //Creating and printing as DefaultTableMode
        DefaultTableModel table1 = new DefaultTableModel();
        table1 = db1.selectToTable("Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank",
                "Department", "CE", "First_Name", "ASC;");
        printDefaultTableModel(table1);

        //perform a query on the database
        data = db1.getExecuteResult("SELECT Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank, " +
                "Student_ID FROM EngineeringStudents where Department = 'ME';");
        System.out.println("\nHere are the results from the 1st query: \n");
        printDatabase(data);

        data = db1.select("Student_ID, Department, First_Name, Last_Name, PassOutYear",
                "Department", "CE", "Student_ID", "DESC;");
        System.out.println("\nHere are the results from the 2nd query: \n");
        printDatabase(data);

        //Update a row of data from the database
        System.out.println("\nUpdated version.");
        db1.update(db1.First_Name, "Tom", db1.PassOutYear, "2000");
        db1.update(db1.Last_Name, "Philips", db1.UniversityRank, "467");
        db1.update(db1.UniversityRank, "12", db1.Student_ID, "71357");

        //Pulls all the records from the database and print
        data = db1.getExecuteResult("SELECT * FROM EngineeringStudents;");
        System.out.println("Here are the contents of the database:");
        printDatabase(data);

       //Creating a DefaultTableMode
       DefaultTableModel table2 = new DefaultTableModel();
       table2 = db1.selectToTable("Student_ID, Department, First_Name, Last_Name, PassOutYear",
                "Last_Name", "Smith", "PassOutYear", "ASC");
       printDefaultTableModel(table2);

    }

    //Call print to display output multiple times
    public static void printDatabase(ArrayList<ArrayList<Object>> data){
        //An enhance for loop to iterate through the list and prints the record.
        for(List<Object> record : data){
            System.out.println(record.toString());
        }
    }

    //Print the database as a table format
    public static void printDefaultTableModel(DefaultTableModel table){
        System.out.println("\nHere is the table: ");

        //A for loop to iterate and display each colum name.
        for (int i = 0; i < table.getColumnCount(); i++) {
            System.out.print(table.getColumnName(i) + " | ");
        }
        System.out.println();

        //A nested for loop to first iterate through the all rows then columns.
        for (int i = 0; i < table.getRowCount(); i++) {
            //Iterate through the columns in ith row
            for (int j = 0; j < table.getColumnCount(); j++) {
                //Print out the specific data at ith row and jth column
                System.out.print(table.getValueAt(i, j) + " | ");
            }
            System.out.println();
        }
    }
}
