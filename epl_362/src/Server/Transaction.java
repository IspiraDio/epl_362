package Server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import Dada_Base_Connection.DBConnection;

public class Transaction {
	
	private String CLIENT_ID;
	private String BRANCH_ID;
	private String DATE;
	private String AMOUTN;
	
	public Transaction(){
		
	}

	public Transaction(String CLIENT_ID,String BRANCH_ID,String DATE,String AMOUTN){
		this.setCLIENT_ID(CLIENT_ID);
		this.setBRANCH_ID(BRANCH_ID);
		this.setDATE(DATE);
		this.setAMOUTN(AMOUTN);
	}
	
	public String insert_transaction(){
		
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_INSERT_TRANSACTON(?,?,?,?)}");
 
			cstmt.setString("CID", getCLIENT_ID());
			cstmt.setString("BID", getBRANCH_ID());
			cstmt.setString("DATE", getDATE());
			cstmt.setString("AMOUNT", getAMOUTN());

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
		
		return "Insert new Transaction succeeded";
	}
	
	public String get_transactions(String CLIENT_ID){
		
		Transaction[] result= null;
		String jsonout = null;
		Gson gson = new Gson();
		String jsonfinal = "";
		Connection conn = null;
		CallableStatement cstmt = null;
		
		try {
			conn = DBConnection.getDBConnection();
			cstmt = conn.prepareCall("{call SP_VIEW_TRANSACTIONS(?)}");
			cstmt.setString("CID", getCLIENT_ID());
			cstmt.execute();

			ResultSet rs;
			rs = (ResultSet) cstmt.getResultSet();
			
		 
			ArrayList<Transaction> arrayList = new ArrayList<Transaction>();
			
			while (rs.next()) {
				arrayList.add(new Transaction(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			
			result = (Transaction[]) arrayList.toArray(new Transaction[arrayList.size()]);
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

	public String getBRANCH_ID() {
		return BRANCH_ID;
	}

	public void setBRANCH_ID(String bRANCH_ID) {
		BRANCH_ID = bRANCH_ID;
	}

	public String getDATE() {
		return DATE;
	}

	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getAMOUTN() {
		return AMOUTN;
	}

	public void setAMOUTN(String aMOUTN) {
		AMOUTN = aMOUTN;
	}
}
