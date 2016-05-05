package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Consultation {
	
	private String CONSULTATION_ID;
	private String DATE;
	private String CLIENT_ID;
	private String BRANCH_ID;
	private String RECOMMENTATION;
	private String ATTEND;
	private String STAFF_ID;
	
	public Consultation(){
		
	}
	
	public Consultation(String DATE,String CLIENT_ID,String	BRANCH_ID,String RECOMMENTATION,String	ATTEND,String STAFF_ID){
		
		this.setDATE(DATE);
		this.setCLIENT_ID(CLIENT_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setRECOMMENTATION(RECOMMENTATION);
		this.setATTEND(ATTEND);
		this.setSTAFF_ID(STAFF_ID);
		
	}
	
	public Consultation(String CONSULTATION_ID,String DATE,String CLIENT_ID,String	BRANCH_ID,String RECOMMENTATION,String	ATTEND,String STAFF_ID){
		
		this.setCONSULTATION_ID(CONSULTATION_ID);
		this.setDATE(DATE);
		this.setCLIENT_ID(CLIENT_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setRECOMMENTATION(RECOMMENTATION);
		this.setATTEND(ATTEND);
		this.setSTAFF_ID(STAFF_ID);
		
	}
	
	
	public String insert_consultation() {
		 
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_INSERT_CONSULTATION(?,?,?,?,?,?)}");
 
			cstmt.setString("DATE", getDATE());
			cstmt.setString("CID", getCLIENT_ID());
			cstmt.setString("BID", getBRANCH_ID());
			cstmt.setString("RECOM", getRECOMMENTATION());
			cstmt.setString("ATTEND", getATTEND());
			cstmt.setString("SID", getSTAFF_ID());
			 
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

		return "Insert new consultation succeeded";
	}

	public String get_consultation(String CONSULTATION_ID) {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_CONSULTATION_INFO(?)}");
		 
			cstmt.setString("CONSLID", CONSULTATION_ID);

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
 
			if (rs.next()) {				
				
				this.setCONSULTATION_ID(rs.getString("CONSULTATION_ID"));
				this.setDATE(rs.getString("DATE"));
				this.setCLIENT_ID(rs.getString("CLIENT_ID"));
				this.setBRANCH_ID(rs.getString("BRANCH_ID"));
				this.setRECOMMENTATION(rs.getString("RECOMMENTATION"));
				this.setSTAFF_ID(rs.getString("STAFF_ID"));
				this.setATTEND(rs.getString("ATTEND"));

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

		return "Get consultation succeeded";
	}

	public String update_consultation() {

		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_UPDATE_CONSULTATION(?,?,?)}");
 
			cstmt.setString("CONSLID", getCONSULTATION_ID());
			cstmt.setString("RECOM", getRECOMMENTATION());
			cstmt.setString("ATTEND", getATTEND());
 
			 
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

		return "Update consultation succeeded";
	}
	
	public String get_consultations() {


		Consultation[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_ALL_CONSULTATION()}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Consultation> arrayList = new ArrayList<Consultation>();
			
			while (rs.next()) {
				arrayList.add(new Consultation(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
			}
			
			result = (Consultation[]) arrayList.toArray(new Consultation[arrayList.size()]);
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

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getBRANCH_ID() {
		return BRANCH_ID;
	}

	public void setBRANCH_ID(String bRANCH_ID) {
		BRANCH_ID = bRANCH_ID;
	}

	public String getRECOMMENTATION() {
		return RECOMMENTATION;
	}

	public void setRECOMMENTATION(String rECOMMENTATION) {
		RECOMMENTATION = rECOMMENTATION;
	}

	public String getSTAFF_ID() {
		return STAFF_ID;
	}

	public void setSTAFF_ID(String sTAFF_ID) {
		STAFF_ID = sTAFF_ID;
	}

	public String getATTEND() {
		return ATTEND;
	}

	public void setATTEND(String aTTEND) {
		ATTEND = aTTEND;
	}

	public String getCONSULTATION_ID() {
		return CONSULTATION_ID;
	}

	public void setCONSULTATION_ID(String cONSULTATION_ID) {
		CONSULTATION_ID = cONSULTATION_ID;
	}

}
