//Comment explanation of usage for each method that extends DBHelper
//From Professor Evans - Database Connectivity with SQLite and IntelliJ video
package DBHelper;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EngineeringStudents extends DBHelper {
	private final String TABLE_NAME = "EngineeringStudents";
	public static final String Student_ID = "Student_ID";
	public static final String Department = "Department";
	public static final String First_Name = "First_Name";
	public static final String Last_Name = "Last_Name";
	public static final String PassOutYear = "PassOutYear";
	public static final String UniversityRank = "UniversityRank";

	/*prepareSQL
	 * Purpose: prepares the text of a SQL "select" command.
	 * return type: String
	 * Arguments:
	 * 		fields: the fields to be displayed in the output
	 * 		whatField: field to search for
	 * 		whatValue: value to search for within whatField
	 * 		sort: use ASC or DESC to specify the sorting order
	 * 		softField: the field that the data will be sorted by
	 */
	private String prepareSQL(String fields, String whatField, String whatValue, String sortField, String sort) {
		String query = "SELECT ";
		query += fields == null ? " * FROM " + TABLE_NAME : fields + " FROM " + TABLE_NAME;
		query += whatField != null && whatValue != null ? " WHERE " + whatField + " = \"" + whatValue + "\"" : "";
		query += sort != null && sortField != null ? " order by " + sortField + " " + sort : "";
		return query;
	}

	/*insert
	 * Purpose: insert a new record into the database
	 * return type: void
	 * Arguments: each field listed into the table from the database, in order
	 * Note: Due to inheritance, this executes the execute method found in the parent class
	 */
	public void insert(Integer Student_ID, String Department, String First_Name, String Last_Name, Integer PassOutYear, Integer UniversityRank) {
		Department = Department != null ? "\"" + Department + "\"" : null;
		First_Name = First_Name != null ? "\"" + First_Name + "\"" : null;
		Last_Name = Last_Name != null ? "\"" + Last_Name + "\"" : null;
		
		Object[] values_ar = {Student_ID, Department, First_Name, Last_Name, PassOutYear, UniversityRank};
		String[] fields_ar = {EngineeringStudents.Student_ID, EngineeringStudents.Department, EngineeringStudents.First_Name, EngineeringStudents.Last_Name, EngineeringStudents.PassOutYear, EngineeringStudents.UniversityRank};
		String values = "", fields = "";
		for (int i = 0; i < values_ar.length; i++) {
			if (values_ar[i] != null) {
				values += values_ar[i] + ", ";
				fields += fields_ar[i] + ", ";
			}
		}
		if (!values.isEmpty()) {
			values = values.substring(0, values.length() - 2);
			fields = fields.substring(0, fields.length() - 2);
			super.execute("INSERT INTO " + TABLE_NAME + "(" + fields + ") values(" + values + ");");
		}
	}

	/*delete
	 * Purpose: delete a new record from the database
	 * return type: void
	 * Arguments: the field (key) used to determine if a row should be deleted,
	 * and the value that should removed
	 *
	 * Note: Due to inheritance, this executes the execute method found in the parent class
	 */
	public void delete(String whatField, String whatValue) {
		super.execute("DELETE from " + TABLE_NAME + " where " + whatField + " = " + whatValue + ";");
	}

	/*update
	 * Purpose: update a record in the database
	 * return type: void
	 * Arguments: the field (key) used to determine if a row should be updated and the value
	 * that should be updated
	 * Note: Due to inheritance, this executes the execute method found in the parent class
	 */
	public void update(String whatField, String whatValue, String whereField, String whereValue) {
		super.execute("UPDATE " + TABLE_NAME + " set " + whatField + " = \"" + whatValue + "\" where " + whereField + " = \"" + whereValue + "\";");
	}

	/*Select
	 * Purpose: completes a SQL "select" command
	 * return type: ArrayList<ArrayList<object>> - this means it returns a 2D array of objects,
	 * so the data returned can be any types.
	 * Arguments:
	 * 		fields: the fields to be displayed in the output
	 * 		whatField: field to search within.
	 * 		whatValue: value to search for within whatField
	 * 		sort: use ASC or DESC to specify the sorting order
	 * 		softField: the field that the data will be sorted by
	 * Note: Due to inheritance, this executes the execute method found in the parent class
	 */
	public ArrayList<ArrayList<Object>> select(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQuery(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

	/*getExecuteResult
	 * Purpose: performs a search of the database where String "query"
	 * is the SQL command that would be entered on the command line
	 * return type: ArrayList<ArrayList<object>> - this means it returns a 2D array of objects,
	 * so the data returned can be any types.
	 * Arguments: query - this is the SQL command that would be entered at the command line for a SQL query
	 * Note: Due to inheritance, this executes the executeQuery method found in the parent class
	 */
	public ArrayList<ArrayList<Object>> getExecuteResult(String query) {
		return super.executeQuery(query);
	}

	/*execute
	 * Purpose: performs a SQL command, where String query is the SQL command that would be entered on the command line
	 * Return type: void
	 * Arguments: query- this is the SQL command that would be entered at the command line form
	 * Note: Due to inheritance, this executes the executeQuery method found in the parent class
	 */
	public void execute(String query) {
		super.execute(query);
	}

	/*DefaultTableModel
	 *Purpose: performs a search of the database, where string query is the SQL command
	 * that would be entered on the command line
	 * Return type: DefaultTableModel - uses a vector of vectors (i.e. a table) to store data
	 * Arguments:
	 * 		fields: the fields to be displayed in the output
	 * 		whatField: field to search within.
	 * 		whatValue: value to search for within whatField
	 * 		sort: use ASC or DESC to specify the sorting order
	 * 		softField: the field that the data will be sorted by
	 * Note: Due to inheritance, this method class the method "executeQueryToTable method from"
	 */
	public DefaultTableModel selectToTable(String fields, String whatField, String whatValue, String sortField, String sort) {
		return super.executeQueryToTable(prepareSQL(fields, whatField, whatValue, sortField, sort));
	}

}