package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Customer {
	
	private	String CLIENT_ID;
	private String LAST_NAME;
	private String FIRST_NAME;
	private String DATE_OF_BIRTH;
	private String PHONE_NUMBER;
	private String EMAIL;
	
	public Customer(){
		
	}
	
	public Customer(String CLIENT_ID,String LAST_NAME,String FIRST_NAME,String DATE_OF_BIRTH,String PHONE_NUMBER,String EMAIL){
		
		this.setCLIENT_ID(CLIENT_ID);
		this.setLAST_NAME(LAST_NAME);
		this.setFIRST_NAME(FIRST_NAME);
		this.setDATE_OF_BIRTH(DATE_OF_BIRTH);
		this.setPHONE_NUMBER(PHONE_NUMBER);
		this.setEMAIL(EMAIL);
	}
	


	
	public String insert_client(){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_INSERT_CLIENT(?,?,?,?,?,?)}");

			cstmt.setString("CID", getCLIENT_ID());
			cstmt.setString("LNAME", getLAST_NAME());
			cstmt.setString("FNAME", getFIRST_NAME());
			cstmt.setString("BDAY", getDATE_OF_BIRTH() );
			cstmt.setString("PHONE", getPHONE_NUMBER());
			cstmt.setString("EMAIL", getEMAIL());

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
		
		return "Insert new client succeeded";
	}
	
	public String get_client(String CLIENT_ID){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_CLIENT_INFO(?)}");

			cstmt.setString("CID",CLIENT_ID);

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
			if (rs.next()) {
				
				this.setCLIENT_ID(rs.getString(1));
				this.setLAST_NAME(rs.getString(2));
				this.setFIRST_NAME(rs.getString(3));
				this.setDATE_OF_BIRTH(rs.getString(4));
				this.setPHONE_NUMBER(rs.getString(5));
				this.setEMAIL(rs.getString(6));
				
			 
			}
			rs.close();

		

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return "Get client succeeded";
	}
	
	public String update_client(){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_UPDATE_CLIENT(?,?,?,?,?,?)}");

			cstmt.setString("CID", getCLIENT_ID());
			cstmt.setString("LNAME", getLAST_NAME());
			cstmt.setString("FNAME", getFIRST_NAME());
			cstmt.setString("BDAY", getDATE_OF_BIRTH() );
			cstmt.setString("PHONE", getPHONE_NUMBER());
			cstmt.setString("EMAIL", getEMAIL());

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
		
		return "Update client succeeded";
	}
	
	public String get_clients(){
		
		Customer[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_ALL_CLIENT_INFO()}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Customer> arrayList = new ArrayList<Customer>();
			while (rs.next()) {
				arrayList.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
			}
			
			result = (Customer[]) arrayList.toArray(new Customer[arrayList.size()]);
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
	
	public String del_client(String CLIENT_ID){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_DELETE_CLIENT(?)}");

			cstmt.setString("CID",CLIENT_ID);

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
		
		return "Delete client succeeded";
	}
	
	public String downdoled_clients(String STAFF_ID){
		
		Customer[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_DOWNLOAD_CLIENT_DATA(?)}");

			cstmt.setString("SID",STAFF_ID);
			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Customer> arrayList = new ArrayList<Customer>();
			while (rs.next()) {
				arrayList.add(new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
			}
			
			result = (Customer[]) arrayList.toArray(new Customer[arrayList.size()]);
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


	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getLAST_NAME() {
		return LAST_NAME;
	}

	public void setLAST_NAME(String lAST_NAME) {
		LAST_NAME = lAST_NAME;
	}

	public String getFIRST_NAME() {
		return FIRST_NAME;
	}

	public void setFIRST_NAME(String fIRST_NAME) {
		FIRST_NAME = fIRST_NAME;
	}

	public String getDATE_OF_BIRTH() {
		return DATE_OF_BIRTH;
	}

	public void setDATE_OF_BIRTH(String dATE_OF_BIRTH) {
		DATE_OF_BIRTH = dATE_OF_BIRTH;
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

}
