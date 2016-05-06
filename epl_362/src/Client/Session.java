package Client;

public class Session {
	public String STAFF_ID;
	public String STAFF_ROLE_ID;
	public String USERNAME;
	
	public Session(){
		
	}
	public Session(String usename, String staff_id, String role){
		this.setSTAFF_ID(staff_id);
		this.setSTAFF_ROLE_ID(role);
		this.setUSERNAME(usename);
	}
 
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
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
