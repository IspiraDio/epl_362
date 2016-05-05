package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Dada_Base_Connection.DBConnection;

public class Login {

	private String USERNAME;
	private String PASSWORD;
	private String STAFF_ID;
	private String STAFF_ROLE_ID;

	public Login() {

	}

	public Login(String USERNAME, String PASSWORD) {
		this.setUSERNAME(USERNAME);
		this.setPASSWORD(PASSWORD);

	}

	public String checkLogin() {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{?=call SP_LOGIN(?,?)}");
			
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setString("USERNAME", getUSERNAME());
			cstmt.setString("PASSWORD", getPASSWORD());
 

			cstmt.execute(); 

				if(cstmt.getInt(1)==1){
					 
					try {
						conn = DBConnection.getDBConnection();
						cstmt.close();
						cstmt = conn.prepareCall("{call SP_LOGIN_INFO(?)}");
					 
						cstmt.setString("USERNAME",getUSERNAME());

						cstmt.execute();

						ResultSet rs;
						rs = (ResultSet) cstmt.getResultSet();
			 
						if (rs.next()) {				
							
							this.setSTAFF_ID(rs.getString("STAFF_ID"));
							this.setSTAFF_ROLE_ID(rs.getString("STAFF_ROLE_ID"));
							this.setUSERNAME(rs.getString("USERNAME"));
							
						}
						rs.close();

						cstmt.execute();

					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						try {
							cstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					return "ok";
				}
 
 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return "err";
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

	public String getSTAFF_ROLE_ID() {
		return STAFF_ROLE_ID;
	}

	public void setSTAFF_ROLE_ID(String sTAFF_ROLE_ID) {
		STAFF_ROLE_ID = sTAFF_ROLE_ID;
	}

}
