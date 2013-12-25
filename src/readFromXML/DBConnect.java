package readFromXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author Puneeth
 */
public class DBConnect {

	//MySQL connection string
	Connection conn = null;
	String dbUrl = "jdbc:mysql://localhost:3306/";
	String dbName = "chitrakavya?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes";
	String driver = "com.mysql.jdbc.Driver";
	String userName = "root";
	String password = "12345";

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	// Constructor: connects to database
	public DBConnect() throws SQLException {
		connectDatabase();
		
	}

	// Connects to the database
	private void connectDatabase() {

		try {
			Class.forName(getDriver()).newInstance();
			conn = DriverManager.getConnection(getDbUrl() + getDbName(),
					getUserName(), getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// Closing the database.
	public void closeDatabase(Connection conn) throws SQLException {
		conn.close();
	}

	
	// Inserts the seed(URL)'s into database
	public void insertData(String word_devanagiri, String word, String meaning, String pos) throws SQLException {
		try {
			
			String sql = "INSERT INTO monier_dict(word_devanagiri, word, meaning, pos) VALUES(?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, word_devanagiri);
			pstmt.setString(2, word);
			pstmt.setString(3, meaning);
			pstmt.setString(4, pos);
								
			pstmt.executeUpdate();
								
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	// Inserts the seed(URL)'s into database
	public void selectData() throws SQLException {
		try {
			
			String sql1 = "SELECT * from monier_dict where word_devanagiri='अट्या'";
			Statement stat = conn.createStatement();
			
		      ResultSet rs = stat.executeQuery(sql1);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         String word_devanagiri  = rs.getString("word_devanagiri");
		         String word = rs.getString("word");
		         String meaning = rs.getString("meaning");
		         String pos = rs.getString("pos");

		         //Display values
		         System.out.print("Word Devanagiri " + word_devanagiri);
		         System.out.print(", Word " + word);
		         System.out.print(", Meaning " + meaning);
		         System.out.println(", POS: " + pos);
		      }
		      rs.close();
			
					
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
}
