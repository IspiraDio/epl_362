package JUnit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Dada_Base_Connection.DBConnection;

public class TestDB {

	Connection connection;

	@Before
	public void before() {
		connection = DBConnection.getDBConnection();
	}

	@After
	public void after() {
		DBConnection.closeConnection(connection);
	}

	@Test
	public void closeStatementShouldCloseStatement() {
		Statement statement;
		try {
			statement = connection.createStatement();
			DBConnection.closeStatement(statement);
			assertTrue(statement.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void closeStatementWithNullShouldNotThrow() {
		DBConnection.closeStatement(null);
	}

}
