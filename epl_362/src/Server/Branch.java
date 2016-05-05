package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Branch {

	private String BRANCH_ID;
	private String CITY;
	private String STREET_NAME;
	private String ZIP_CODE;
	private String COUNTRY;

	public Branch() {

	}

	public Branch(String BRANCH_ID, String CITY, String STREET_NAME, String ZIP_CODE, String COUNTRY) {

		this.setBRANCH_ID(BRANCH_ID);
		this.setCITY(CITY);
		this.setSTREET_NAME(STREET_NAME);
		this.setZIP_CODE(ZIP_CODE);
		this.setCOUNTRY(COUNTRY);

	}

	public String insert_branch() {
 
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_INSERT_BRANCH(?,?,?,?,?)}");

			cstmt.setString("BID", getBRANCH_ID());
			cstmt.setString("CITY", getCITY());
			cstmt.setString("STREET", getSTREET_NAME());
			cstmt.setString("ZIP", getZIP_CODE());
			cstmt.setString("COUNTRY", getCOUNTRY());
			 
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

		return "Insert new branch succeeded";
	}

	public String get_branch(String BRANCH_ID) {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_BRANCH_INFO(?)}");

			cstmt.setString("BID", BRANCH_ID);

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();

			if (rs.next()) {

				
				
				this.setBRANCH_ID(rs.getString("BRANCH_ID"));
				this.setCITY(rs.getString("CITY"));
				this.setSTREET_NAME(rs.getString("STREET_NAME"));
				this.setZIP_CODE(rs.getString("ZIP_CODE"));
				this.setCOUNTRY(rs.getString("COUNTRY"));

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

		return "Get branch succeeded";
	}

	public String update_branch() {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_UPDATE_BRANCH(?,?,?,?,?)}");

			cstmt.setString("BID", getBRANCH_ID());
			cstmt.setString("CITY", getCITY());
			cstmt.setString("STREET", getSTREET_NAME());
			cstmt.setString("ZIP", getZIP_CODE());
			cstmt.setString("COUNTRY", getCOUNTRY());

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

		return "Update branch succeeded";
	}
	
	public String get_branchs() {

		Branch[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_ALL_BRANCHES()}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Branch> arrayList = new ArrayList<Branch>();
			
			while (rs.next()) {
				arrayList.add(new Branch(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
			result = (Branch[]) arrayList.toArray(new Branch[arrayList.size()]);
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
	
	public String del_branch(String BRANCH_ID) {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_DELETE_BRANCH(?)}");

			cstmt.setString("BID", BRANCH_ID);

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

		return "Delete branch succeeded";
	}

	public String getBRANCH_ID() {
		return BRANCH_ID;
	}

	public void setBRANCH_ID(String bRANCH_ID) {
		BRANCH_ID = bRANCH_ID;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getSTREET_NAME() {
		return STREET_NAME;
	}

	public void setSTREET_NAME(String sTREET_NAME) {
		STREET_NAME = sTREET_NAME;
	}

	public String getZIP_CODE() {
		return ZIP_CODE;
	}

	public void setZIP_CODE(String zIP_CODE) {
		ZIP_CODE = zIP_CODE;
	}

	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}

}
