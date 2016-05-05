package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Appointment {

	private String APPOINTMENT_ID;
	private String CLIENT_ID;
	private String STAFF_ID;
	private String BRANCH_ID;
	private String DATE;
	private String TIME;
	private String ATTEND;

	public Appointment() {

	}

	public Appointment(String CLIENT_ID, String STAFF_ID, String BRANCH_ID, String DATE, String TIME, String ATTEND) {
				 
		this.setCLIENT_ID(CLIENT_ID);
		this.setSTAFF_ID(STAFF_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setTIME(TIME);
		this.setATTEND(ATTEND);
	}
	
	public Appointment(String APPOINTMENT_ID,String CLIENT_ID, String STAFF_ID, String BRANCH_ID, String DATE, String TIME, String ATTEND) {
		 
		this.setAPPOINTMENT_ID(APPOINTMENT_ID);
		this.setCLIENT_ID(CLIENT_ID);
		this.setSTAFF_ID(STAFF_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setTIME(TIME);
		this.setATTEND(ATTEND);
	}


	public String insert_appointment() {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_INSERT_APPOINTMENT(?,?,?,?,?,?)}");

			cstmt.setString("CID", getCLIENT_ID());
			cstmt.setString("SID", getSTAFF_ID());
			cstmt.setString("BID", getBRANCH_ID());
			cstmt.setString("DAY", getDATE());
			cstmt.setString("TIME", getTIME());
			cstmt.setBoolean("ATTEND", Boolean.parseBoolean(getATTEND()));

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

		return "Insert new appointment succeeded";
	}

	public String get_appointment(String APPOINTMENT_ID) {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_APPOINTMENT_INFO(?)}");

			cstmt.setString("AID", APPOINTMENT_ID);

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();

			if (rs.next()) {
                this.setAPPOINTMENT_ID(rs.getString(1));
				this.setCLIENT_ID(rs.getString(2));
				this.setSTAFF_ID(rs.getString(3));
				this.setBRANCH_ID(rs.getString(4));
				this.setDATE(rs.getString(5));
				this.setTIME(rs.getString(6));
				this.setATTEND(rs.getString(7));

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

		return "Get appointment succeeded";
	}
	public String get_appointment_by_customer(String CLIENT_ID) {

		Appointment[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_APPOINTMENT_INFO_BY_CLIENT(?)}");
			cstmt.setString("CID", CLIENT_ID);
			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Appointment> arrayList = new ArrayList<Appointment>();
			while (rs.next()) {
				arrayList.add(new Appointment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			
			result = (Appointment[]) arrayList.toArray(new Appointment[arrayList.size()]);
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

	public String update_appointment() {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_UPDATE_APPOINTMENT(?,?,?,?)}");
			
			cstmt.setString("DAY", getDATE());
			cstmt.setString("TIME", getTIME());
			cstmt.setBoolean("ATTEND", Boolean.parseBoolean(getATTEND()));
			cstmt.setString("AID", getAPPOINTMENT_ID()); 

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

		return "Update appointment succeeded";
	}
	
	public String get_appointments() {

		Appointment[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_ALL_APPOINTMENT()}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Appointment> arrayList = new ArrayList<Appointment>();
			
			while (rs.next()) {
				arrayList.add(new Appointment(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			
			result = (Appointment[]) arrayList.toArray(new Appointment[arrayList.size()]);
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
	
	public String del_appointment(String APPOINTMENT_ID) {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_DELETE_APPOINTMENT(?)}");

			cstmt.setString("AID", APPOINTMENT_ID);

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

		return "Delete appointment succeeded";
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getBRANCH_ID() {
		return BRANCH_ID;
	}

	public void setBRANCH_ID(String bRANCH_ID) {
		BRANCH_ID = bRANCH_ID;
	}

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

	public String getTIME() {
		return TIME;
	}

	public void setTIME(String tIME) {
		TIME = tIME;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getATTEND() {
		return ATTEND;
	}

	public void setATTEND(String aTTEND) {
		ATTEND = aTTEND;
	}

	public String getAPPOINTMENT_ID() {
		return APPOINTMENT_ID;
	}

	public void setAPPOINTMENT_ID(String aPPOINTMENT_ID) {
		APPOINTMENT_ID = aPPOINTMENT_ID;
	}

	 

}
