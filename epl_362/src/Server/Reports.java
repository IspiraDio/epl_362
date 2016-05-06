package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Reports {

	private String REPORT_ID;
	private String MONTH;
	private String RESULT;

	public Reports() {

	}

	public Reports(String REPORT_ID, String MONTH,String RESULT) {

		this.setREPORT_ID(REPORT_ID);
		this.setMONTH(MONTH);
		this.setRESULT(RESULT);

	}

	public String get_branch_month() {

		Reports[] result = null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_CLIENT_BRANCH_MONTH()}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();

			ArrayList<Reports> arrayList = new ArrayList<Reports>();

			while (rs.next()) {
				arrayList.add(new Reports(rs.getString(1), rs.getString(2), rs.getString(3)));
			}

			result = (Reports[]) arrayList.toArray(new Reports[arrayList.size()]);
			rs.close();

			for (int i = 0; i < result.length; i++) {
				jsonout = gson.toJson(result[i]);
				jsonfinal = jsonfinal.concat(jsonout + "\n");
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
	
	public String get_client_rec_month(String CLIENT_ID) {

		Reports[] result = null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_CLIENT_REC_MONTH(?)}");
			System.out.println(CLIENT_ID);
			cstmt.setString("CID",CLIENT_ID);
			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
			ArrayList<Reports> arrayList = new ArrayList<Reports>();

			while (rs.next()) {
		 
				arrayList.add(new Reports("1",rs.getString(1), rs.getString(2)));
				
			}

			result = (Reports[]) arrayList.toArray(new Reports[arrayList.size()]);
			rs.close();

			for (int i = 0; i < result.length; i++) {
				jsonout = gson.toJson(result[i]);
				jsonfinal = jsonfinal.concat(jsonout + "\n");
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

	public String getMONTH() {
		return MONTH;
	}

	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}

	 

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public String getREPORT_ID() {
		return REPORT_ID;
	}

	public void setREPORT_ID(String rEPORT_ID) {
		REPORT_ID = rEPORT_ID;
	}

}
