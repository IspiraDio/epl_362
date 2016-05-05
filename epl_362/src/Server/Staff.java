package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Staff {
	
	
	private String STAFF_ID;
	private String LAST_NAME;
	private String FIRST_NAME;
	private String DATE_OF_BIRTH;
	private String PHONE_NUMBER;
	private String EMAIL;
	private String ADDRESS;
	private String ROLE;
	
	
	public Staff(){
		
	}
	
	public Staff(String STAFF_ID,String FIRST_NAME,String LAST_NAME,String DATE_OF_BIRTH,String PHONE_NUMBER,String EMAIL,String ADDRESS,String ROLE){
		
		this.setSTAFF_ID(STAFF_ID);
		this.setLAST_NAME(LAST_NAME);
		this.setFIRST_NAME(FIRST_NAME);
		this.setDATE_OF_BIRTH(DATE_OF_BIRTH);
		this.setPHONE_NUMBER(PHONE_NUMBER);
		this.setEMAIL(EMAIL);
		this.setADDRESS(ADDRESS);
		this.setROLE(ROLE);
		 
	}
	
	public String insert_staff(){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_INSERT_STAFF(?,?,?,?,?,?,?,?)}");
 
			cstmt.setString("SID", getSTAFF_ID());
			cstmt.setString("LNAME", getLAST_NAME());
			cstmt.setString("FNAME", getFIRST_NAME());
			cstmt.setString("BDAY", getDATE_OF_BIRTH() );
			cstmt.setString("PHONE", getPHONE_NUMBER());
			cstmt.setString("EMAIL", getEMAIL());
			cstmt.setString("ADDRESS", getADDRESS());
			cstmt.setInt("ROLE", Integer.parseInt(getROLE())); 

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
		
		return "Insert new staff succeeded";
	}
	
	public String get_staff(String STAFF_ID){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_STAFF_INFO(?)}");

			cstmt.setString("SID",STAFF_ID);

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
			if (rs.next()) {
				
				this.setSTAFF_ID(rs.getString(1));
				this.setLAST_NAME(rs.getString(3));
				this.setFIRST_NAME(rs.getString(2));
				this.setDATE_OF_BIRTH(rs.getString(4));
				this.setPHONE_NUMBER(rs.getString(5));
				this.setEMAIL(rs.getString(6));
				this.setROLE(rs.getString(7));
				this.setADDRESS(rs.getString(8));
			 
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
		
		return "Get staff succeeded";
	}
	
	public String update_staff(){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_UPDATE_STAFF(?,?,?,?,?,?,?,?)}");

			cstmt.setString("SID", getSTAFF_ID());
			cstmt.setString("LNAME", getLAST_NAME());
			cstmt.setString("FNAME", getFIRST_NAME());
			cstmt.setString("BDAY", getDATE_OF_BIRTH() );
			cstmt.setString("PHONE", getPHONE_NUMBER());
			cstmt.setString("EMAIL", getEMAIL());
			cstmt.setString("ADDRESS", getADDRESS());
			cstmt.setInt("ROLE", Integer.parseInt(getROLE())); 


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
		
		return "Update Staff succeeded";
	}
	
	public String get_staffs(){
		
		Staff[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_ALL_STAFF_INFO()}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Staff> arrayList = new ArrayList<Staff>();
			
			while (rs.next()) {
				arrayList.add(new Staff(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
			}
			
			result = (Staff[]) arrayList.toArray(new Staff[arrayList.size()]);
			rs.close();

			for(int i=0;i<result.length;i++){
				jsonout = gson.toJson(result[i]);
				jsonfinal = jsonfinal.concat(jsonout+"\n");
			}
			

			return jsonfinal;

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
	
public String del_staff(String STAFF_ID){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_DELETE_STAFF(?)}");

			cstmt.setString("SID",STAFF_ID);

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
		
		return "Delete staff succeeded";
	}

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getDATE_OF_BIRTH() {
		return DATE_OF_BIRTH;
	}

	public void setDATE_OF_BIRTH(String dATE_OF_BIRTH) {
		DATE_OF_BIRTH = dATE_OF_BIRTH;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

}
