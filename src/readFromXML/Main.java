package readFromXML;

import java.sql.SQLException;

public class Main {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DBConnect db = new DBConnect();
	
		db.selectData();

	}

}
