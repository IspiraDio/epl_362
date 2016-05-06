package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Recommendation {
	
	private String CLIENT_ID;
	private String RECOMMENDATION;
	private String MONTH;
	
	public Recommendation(){
		
	}
	
	public Recommendation(String RECOMMENDATION,String CLIENT_ID){
		
		this.setRECOMMENDATION(RECOMMENDATION);
		this.setCLIENT_ID(CLIENT_ID);
		
	}
	
	public String get_recommendations(String CLIENT_ID){
		
		Recommendation[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_VIEW_RECOMMENDATION(?)}");

			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Recommendation> arrayList = new ArrayList<Recommendation>();
			while (rs.next()) {
				arrayList.add(new Recommendation(rs.getString(1),rs.getString(2)));
			}
			
			result = (Recommendation[]) arrayList.toArray(new Recommendation[arrayList.size()]);
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
	
	

	public String getRECOMMENDATION() {
		return RECOMMENDATION;
	}

	public void setRECOMMENDATION(String rECOMMENDATION) {
		RECOMMENDATION = rECOMMENDATION;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getMONTH() {
		return MONTH;
	}

	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}

}
