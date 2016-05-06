package Dada_Base_Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {

	
	private static boolean dbDriverLoaded = false;
	private static Connection conn = null;
//	
//	public  Connection getConnection() throws Exception
//	{
//	try
//	{
//	String connectionURL = "jdbc:sqlserver://APOLLO.IN.CS.UCY.AC.CY;databaseName=gkalai01;user=gkalai01;password=haB8esEg;";
//	Connection connection = null;
//	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//	connection = DriverManager.getConnection(connectionURL);
//	return connection;
//	} catch (Exception e)
//	{
//	throw e;
//	}
//	 
//	}
	
	public static Connection getDBConnection() {
		
		String dbConnString = "jdbc:sqlserver://APOLLO.IN.CS.UCY.AC.CY;databaseName=gkalai01;user=gkalai01;password=haB8esEg;";
		if (!dbDriverLoaded)
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dbDriverLoaded = true;
				System.out.println("Connection to DB succeded");
			} catch (ClassNotFoundException e) {
				System.out.println("Cannot load DB driver!");
				return null;
			}

		try {
			if (conn == null)
				conn = DriverManager.getConnection(dbConnString);
			else if (conn.isClosed())
				conn = DriverManager.getConnection(dbConnString);
		} catch (SQLException e) {
			System.out.print("Cannot connect to the DB!\nGot error: ");
			System.out.print(e.getErrorCode());
			System.out.print("\nSQL State: ");
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
        try {
            if (null != conn) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
	

    public static void closeStatement(Statement stmt) {
        try {
            if (null != stmt) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
	
//	public static void main(String[] args) throws SQLException{
//		
//		Connection con = getDBConnection();
//		System.out.println(con.getMetaData().getURL());
//		
//		
//	}
	
}
